package com.zw.cloud.activiti.controller;

import com.zw.cloud.common.utils.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/activiti/file")
public class FileController {

    private Logger log = LoggerFactory.getLogger(FileController.class);

    // 单文件上传
    @RequestMapping(value = "/upload")
    @ResponseBody
    //http://localhost:9001/upload
    public WebResult upload(MultipartFile file, HttpServletRequest request) {

        try {
            if (file.isEmpty()) {
                return WebResult.failed().withErrorMsg("文件为空");
            }
            Map<String,String> result = new HashMap<>();
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);

            // 设置文件存储路径
            //String filePath = request.getSession().getServletContext().getRealPath("/");
            String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();//D:/spring/activiti/target/classes/
            System.out.println(filePath);
            String path = filePath +"/process/"+ fileName;

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            result.put("filePath",path );
            result.put("fileName",fileName );

            return WebResult.success().withData(result);
        } catch (Exception e) {
            e.printStackTrace();
            return WebResult.failed().withErrorMsg(e.getMessage());
        }
    }

    @RequestMapping("/download")
    public WebResult downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "ideaIU-2018.1.4.exe";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = "D://test//";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return WebResult.success();
    }
}
