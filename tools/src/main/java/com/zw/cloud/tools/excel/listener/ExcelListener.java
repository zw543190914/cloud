package com.zw.cloud.tools.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.dao.UserMapper;
import com.zw.cloud.tools.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * https://www.yuque.com/easyexcel/doc/read
 */
@Slf4j
public class ExcelListener extends AnalysisEventListener<User> {

    private final UserMapper userDao;

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 199;
    //存储待导入的数据列表
    private final List<User> userList = new LinkedList<>();

    public ExcelListener(UserMapper userDao) {
        this.userDao = userDao;
    }

    public List<User> getUserList() {
        return userList;
    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        userList.add(user);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (userList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            userList.clear();
        }

    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        log.info("[ExcelListener][doAfterAllAnalysed] {}条数据，开始存储数据库！", userList.size());
        if (CollectionUtils.isNotEmpty(userList)) {
            saveData();
            // 存储完成清理 list
            userList.clear();
        }
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("[ExcelListener][onException] 解析失败，但是继续解析下一行:{}", exception.getMessage());
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
        }
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("[ExcelListener][saveData] {}条数据，开始存储数据库！", userList.size());
        //userDao.save(userList);
        log.info("[ExcelListener][saveData] 存储数据库成功！");
    }
}