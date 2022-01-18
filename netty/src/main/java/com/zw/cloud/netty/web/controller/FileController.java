package com.zw.cloud.netty.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/netty/file")
@Slf4j
public class FileController {


    @PostMapping("/uploadToLocal")
    public void uploadToLocal(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("文件为空");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            //String suffixName = fileName.substring(fileName.lastIndexOf("."));

            // 设置文件存储路径
            String filePath = "C:\\download\\";
            String path = filePath + fileName;

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
        } catch (Exception e) {
            log.error("[FileController][uploadToLocal]error is ",e);
        }
    }

    @GetMapping("/downloadFileFromLocal")
    public void downloadFileFromLocal(@RequestParam String fileName, HttpServletResponse response) {
        //String fileName = "lc.png";// 设置文件名，根据业务需要替换成要下载的文件名
        if (StringUtils.isBlank(fileName)) {
            throw new RuntimeException("文件不存在");
        }
        //设置文件路径
        String realPath = "C:/zj/";
        File file = new File(realPath, fileName);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }

        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("[FileController][downloadFileFromLocal]error is ", e);
        }

    }
}
