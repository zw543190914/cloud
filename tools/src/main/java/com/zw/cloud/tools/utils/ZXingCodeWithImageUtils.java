package com.zw.cloud.tools.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import javax.imageio.ImageIO;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import org.apache.commons.lang3.StringUtils;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZXingCodeWithImageUtils {
    private static final int QRCOLOR = 0xFF000000; // 默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF; // 背景颜色

    private static final int WIDTH = 400; // 二维码宽
    private static final int HEIGHT = 400; // 二维码高

    // 用于设置QR二维码参数
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;
        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置编码方式
            put(EncodeHintType.MARGIN, 0);
        }
    };

    // 生成带logo的二维码图片
    public static void drawLogoQRCode(File logoFile, File codeFile, String qrUrl, String note) {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }

            int width = image.getWidth();
            int height = image.getHeight();
            if (Objects.nonNull(logoFile) && logoFile.exists()) {
                // 构建绘图对象
                Graphics2D g = image.createGraphics();
                // 读取Logo图片
                BufferedImage logo = ImageIO.read(logoFile);
                // 开始绘制logo图片
                g.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 2 / 10, height * 2 / 10, null);
                g.dispose();
                logo.flush();
            }

            // 自定义文本描述
            if (StringUtils.isNotEmpty(note)) {
                // 新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                // 画二维码到新的面板
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                // 画文字到新的面板
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("楷体", Font.BOLD, 30)); // 字体、字型、字号
                int strWidth = outg.getFontMetrics().stringWidth(note);
                if (strWidth > 399) {
                    // //长度过长就截取前面部分
                    // 长度过长就换行
                    String note1 = note.substring(0, note.length() / 2);
                    String note2 = note.substring(note.length() / 2, note.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(note1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(note2);
                    outg.drawString(note1, 200 - strWidth1 / 2, height + (outImage.getHeight() - height) / 2 + 12);
                    BufferedImage outImage2 = new BufferedImage(400, 485, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont(new Font("宋体", Font.BOLD, 30)); // 字体、字型、字号
                    outg2.drawString(note2, 200 - strWidth2 / 2,outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                } else {
                    outg.drawString(note, 200 - strWidth / 2, height + (outImage.getHeight() - height) / 2 + 12); // 画文字
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            image.flush();
            ImageIO.write(image, "png", codeFile); // TODO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //生成二维码
    public static void main1(String[] args) throws WriterException {
        File logoFile = new File("D://QrCode/logo3.jpg");
        File QrCodeFile = new File("D://QrCode/05.png");
        String url = "https://www.jianshu.com/p/7ae3b7002530";
        String note = "访问java二维码";
        drawLogoQRCode(logoFile, QrCodeFile, url, note);
    }


    //解析 二维码
    public static void main(String[] args) {
        try {
            MultiFormatReader reader=new MultiFormatReader();
            File f=new File("D:\\QrCode\\05.png");
            BufferedImage image=ImageIO.read(f);
            BinaryBitmap bb=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            HashMap map =new HashMap();
            map.put(EncodeHintType.CHARACTER_SET, "utf-8");
            Result result = reader.decode(bb,map);
            System.out.println("解析结果："+result.toString());
            System.out.println("二维码格式类型："+result.getBarcodeFormat());
            System.out.println("二维码文本内容："+result.getText());
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    //生成二维码
   /* @RequestMapping("/drawLogoQRCode")
    //http://localhost:9001/zxing/drawLogoQRCode:
    public void test1(HttpServletResponse response) throws WriterException {
        File logoFile = new File("D://QrCode/logo3.jpg");
        //File QrCodeFile = new File("D://QrCode/05.png");
        //String url = "https://www.cnblogs.com/songyz/p/7490335.html";
        String content = "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "N:余顺钦\n" +
                "TEL:15937699668\n" +
                "ADR:江西\n" +
                "ORG:" +
                "江西\n" +
                "TITLE:软件工程师\n" +
                "NOTE:呼呼测试下吧。。。\n" +
                "END:VCARD";

        //String note = "访问java生成二维码";
        drawLogoQRCode(logoFile, response, content, null);
    }*/
