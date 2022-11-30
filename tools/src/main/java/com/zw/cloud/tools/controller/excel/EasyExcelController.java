package com.zw.cloud.tools.controller.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.dao.UserMapper;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.excel.listener.ExcelListener;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@RequestMapping("/easy/excel")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EasyExcelController {

    private final UserMapper userDao;

    @PostMapping
    //http://localhost:9040/easy/excel
    public void upload(MultipartFile file) throws Exception {
        ExcelListener excelListener = new ExcelListener(userDao);
        ExcelReader excelReader = EasyExcelFactory.read(file.getInputStream(), User.class,excelListener).build();
        try {
            // headRowNumber 读取开始行数
            ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(2).build();
            excelReader.read(readSheet);

        } catch (Exception e) {
            throw  new RuntimeException("excel格式错误，请联系管理员配置相关参数或者使用系统模板导入");
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    @GetMapping
    //http://localhost:9040/easy/excel
    public void excludeOrIncludeWrite(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(System.currentTimeMillis() + "测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        //新建ExcelWriter
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(),User.class).build();
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "sheet1").head(generateHead()).build();
        List<User> users = userDao.queryUserList(1588093790661382146L);
        excelWriter.write(users, sheet1);
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "sheet2").head(generateHead()).build();
        List<User> userList = userDao.queryUserList(1587799507834171400L);
        excelWriter.write(userList, sheet2);
        //关闭流
        excelWriter.finish();
    }

    public List<List<String>> generateHead() {

        List<List<String>> headList2D = new ArrayList<>();

        headList2D.add(Arrays.asList("id", "id")); // 一列
        headList2D.add(Arrays.asList("信息", "姓名"));
        headList2D.add(Arrays.asList("信息", "年龄"));
        headList2D.add(Arrays.asList("信息", "生日"));
        headList2D.add(Arrays.asList("创建时间", "创建时间"));

        return headList2D;
    }
}
