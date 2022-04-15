package com.zw.cloud.tools.utils;

import cn.hutool.core.codec.Base64;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ZXingCodeSimpleUtils {
    /**
     * 生成Base64 二维码
     */
    public static String crateQRCode(String content) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            QRCodeWriter writer = new QRCodeWriter();
            Map map = new HashMap<>(4);
            map.put(EncodeHintType.CHARACTER_SET, "utf-8");

            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 200, 200,map);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "png", os);      //添加图片标识
            return "data:image/png;base64," + Base64.encode(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(crateQRCode("www.baidu.com"));
    }

}
    
