package com.zw.cloud.tools.controller.zing;

import com.zw.cloud.common.utils.zing.QrCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhengwei
 * @date 2022/4/15 21:52
 */
@RequestMapping("/zing/image")
@RestController
@Slf4j
public class ZingQrWithImgController {

    @PostMapping
    //http://localhost:9040/zing/image
    public void createImageQRCode(@RequestParam String content,@RequestParam String fileName, MultipartFile file) throws Exception {
        log.info("[ZingQrWithImgController][createImageQRCode]content is {},fileName is {}",content,fileName);

        if (StringUtils.isBlank(content)) {
            throw new RuntimeException("请输入内容");
        }
        String absolutePath = ResourceUtils.getFile("classpath:").getAbsolutePath();
        File qrCodeFile = new File(absolutePath + "/qr/" + fileName);
        if (!qrCodeFile.getParentFile().exists()) {
            qrCodeFile.getParentFile().mkdirs();
        }
        QrCodeUtils.encodeToDestPath(content,file,qrCodeFile);
    }

    @PostMapping("/createImageQRCodeForWeb")
    //http://localhost:9040/zing/image/createImageQRCodeForWeb
    public void createImageQRCodeForWeb(MultipartFile file,String content, HttpServletResponse response) throws Exception {
        log.info("[ZingQrWithImgController][createImageQRCodeForWeb]content is {}", content);

        if (StringUtils.isBlank(content)) {
            throw new RuntimeException("请输入内容");
        }
        BufferedImage bufferedImage = QrCodeUtils.encodeAndReturnStream(content, file);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-Type", "image/png");
        ImageIO.write(bufferedImage, "png", outputStream);
    }

    @GetMapping
    public void downLoadImageQRCode(@RequestParam String fileName, HttpServletResponse response) {
        log.info("[ZingQrWithImgController][downLoadImageQRCode]fileName is {}",fileName);
        try (OutputStream os = response.getOutputStream()) {
            //设置文件路径
            String absolutePath = ResourceUtils.getFile("classpath:").getAbsolutePath();
            File file = new File(absolutePath + "/qr/" + fileName);
            if (!file.exists()) {
                throw new RuntimeException("二维码文件不存在");
            }
            // 取得输出流
            String contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
            response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
            FileInputStream fileInputStream = new FileInputStream(file);
            WritableByteChannel writableByteChannel = Channels.newChannel(os);
            FileChannel fileChannel = fileInputStream.getChannel();
            fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
            fileChannel.close();
            os.flush();
            writableByteChannel.close();
        } catch (IOException e) {
            throw new RuntimeException("二维码文件下载失败");
        }
    }
}
