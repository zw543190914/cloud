package com.zw.cloud.tools.controller.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
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
    public void upload(MultipartFile file) throws Exception {
        ExcelListener excelListener = new ExcelListener(userDao);
        ExcelReader excelReader = EasyExcelFactory.read(file.getInputStream(), User.class,excelListener).build();
        try {
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
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
        /*String fileName = "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";

        // 根据用户传入字段 假设我们要忽略 date
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("date");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, User.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板")
                .doWrite(data());

        fileName = "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";
        // 根据用户传入字段 假设我们只要导出 date
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("date");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, User.class).includeColumnFiledNames(includeColumnFiledNames).sheet("模板")
                .doWrite(data());
*/

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), User.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(data());
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }


    }

    private List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(Long.valueOf(i));
            data.setName("张三"+ i);
            //data.setDescription("字符串" + i);
            data.setBir(new Date());
            data.setAge((byte)56);
            list.add(data);
        }
        return list;
    }
}
