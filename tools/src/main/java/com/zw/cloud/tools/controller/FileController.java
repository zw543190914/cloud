package com.zw.cloud.tools.controller;

import com.zw.cloud.common.utils.WebResult;
import com.zw.cloud.tools.modle.vo.MyPutRet;
import com.zw.cloud.tools.service.impl.FileServiceImpl;
import com.zw.cloud.tools.utils.CustomerExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/tools/file")
@Slf4j
public class FileController {

    @Autowired
    private FileServiceImpl fileService;

    @PostMapping("/upload")
    //http://localhost:9040/tools/file/upload
    public WebResult upload(String id, MultipartFile file) throws Exception{
        return WebResult.success().withData(fileService.upload(id, file));
    }

    @PostMapping("/continueUpload")
    //http://localhost:9040/tools/file/continueUpload
    public WebResult continueUpload(String id, MultipartFile file)throws Exception{
        return WebResult.success().withData(fileService.continueUpload(id, file));

    }

    @GetMapping("/download")
    //http://localhost:9040/tools/file/download?fileName=0.jpg
    public WebResult download(String fileName)throws Exception{
        return WebResult.success().withData(fileService.download(fileName));

    }

    @GetMapping("/delete")
    //http://localhost:9040/tools/file/delete?key=null_1593952612702用户数据 (1).xlsx
    public void delete(String key)throws Exception{
        fileService.delete(key);
    }

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
    //http://10.30.2.162:9040/tools/file/downloadFileFromLocal?fileName=ideaIU-2020.3.4.exe
    public void downloadFileFromLocal(@RequestParam String fileName,HttpServletResponse response) {
        //String fileName = "lc.png";// 设置文件名，根据业务需要替换成要下载的文件名
        /*if (StringUtils.isBlank(fileName)) {
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
        try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis);) {
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("[FileController][downloadFileFromLocal]error is ", e);
        }*/
        //设置文件路径
        String realPath = "C:/download/";
        File file = new File(realPath,fileName);
        try (OutputStream os = response.getOutputStream();FileInputStream fileInputStream = new FileInputStream(file)){

            // 取得输出流
            String contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
            response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(file.getName().getBytes(StandardCharsets.UTF_8),"ISO8859-1"));
            WritableByteChannel writableByteChannel = Channels.newChannel(os);
            FileChannel fileChannel = fileInputStream.getChannel();
            fileChannel.transferTo(0,fileChannel.size(),writableByteChannel);
            fileChannel.close();
            os.flush();
            writableByteChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/downloadFileFromNet")
    //http://10.30.2.162:9040/tools/file/downloadFileFromNet?url=
    public void downloadFileFromNet(@RequestParam String url) throws Exception {
        int i = url.lastIndexOf(".");
        String fileType = url.substring(i + 1);
        HttpURLConnection httpUrl = (HttpURLConnection) new URL(url).openConnection();
        httpUrl.connect();
        InputStream inputStream = httpUrl.getInputStream();
        ReadableByteChannel inChannel = Channels.newChannel(inputStream);

        FileChannel outChannel = new FileOutputStream("d:/test." + fileType).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();
    }

}
