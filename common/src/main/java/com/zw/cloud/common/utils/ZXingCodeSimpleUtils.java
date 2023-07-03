package com.zw.cloud.common.utils;

import cn.hutool.core.codec.Base64;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ZXingCodeSimpleUtils {
    /**
     * 生成Base64 二维码
     */
    public static String crateQRCode(String content) throws Exception {

        try(ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            QRCodeWriter writer = new QRCodeWriter();
            Map map = new HashMap<>(4);
            map.put(EncodeHintType.CHARACTER_SET, "utf-8");

            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200,map);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "png", os);      //添加图片标识
            return "data:image/png;base64," + Base64.encode(os.toByteArray());
        } catch (IOException e) {
            log.error("[ZXingCodeSimpleUtils][crateQRCode] error is ",e);
            throw e;
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println(crateQRCode("www.baidu.com"));
    }

}
    
