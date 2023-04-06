package com.zw.cloud.tools.controller.file;

import com.alibaba.fastjson.JSON;
import com.zw.cloud.tools.utils.ImageToPdfUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/image-to-pdf")
public class ImageToPdfController {

    @GetMapping
    //http://localhost:9040/image-to-pdf
    public void imageToPdf(HttpServletResponse response) {
        List<String> filePathList = new ArrayList<>();
        ImageToPdfUtils.getAllFile(new File("D:\\相册"), filePathList);
        System.out.println(JSON.toJSONString(filePathList));
        File file = ImageToPdfUtils.imageToPdf(filePathList, "testPdf.pdf");
        if (Objects.isNull(file)) {
            throw new RuntimeException("文件夹没有图片");
        }
        try (OutputStream os = response.getOutputStream()){
            // 取得输出流
            String contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
            response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(file.getName().getBytes(StandardCharsets.UTF_8)));
            FileInputStream fileInputStream = new FileInputStream(file);
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
}
