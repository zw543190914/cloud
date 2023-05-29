package com.zw.cloud.common.utils.zing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Objects;

/**
 * @author zhengwei
 * @date 2022/4/17 21:58
 */
public class QrCodeUtils {

    private static final String CHARSET = "utf-8";
    public static final String FORMAT = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 470;
    // LOGO宽度
    private static final int LOGO_WIDTH = 80;
    // LOGO高度
    private static final int LOGO_HEIGHT = 80;

    /**
     * 生成二维码
     *
     * @param content      二维码内容
     * @param logoFile     logo地址
     * @return 图片
     * @throws Exception
     */
    public static BufferedImage createImage(String content, MultipartFile logoFile) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (Objects.isNull(logoFile)) {
            return image;
        }
        // 插入图片
        QrCodeUtils.insertImage(image, logoFile,true);
        return image;
    }

    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param logoFile     LOGO图片地址
     * @param needCompress 是否压缩
     */
    private static void insertImage(BufferedImage source, MultipartFile logoFile,
                                    boolean needCompress) throws Exception {
        Image src = ImageIO.read(logoFile.getInputStream());
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成二维码（指定路径保存）
     *
     * @param content 内容
     * @param logoFile logo图片地址（内嵌图片）
     * @param destPath 生成二维码存放地址
     * @throws Exception
     */
    public static void encodeToDestPath(String content, MultipartFile logoFile, File destPath) throws Exception {
        BufferedImage image = QrCodeUtils.createImage(content, logoFile);
        //mkdirs(destPath);
        // String file = new Random().nextInt(99999999)+".jpg";
        // ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
        ImageIO.write(image, FORMAT, destPath);
    }

    /**
     * 生成二维码(直接将二维码以图片输出流返回）
     *
     * @param content 内容
     * @param logoFile logo图片地址（内嵌图片）
     * @return
     * @throws Exception
     */
    public static BufferedImage encodeAndReturnStream(String content, MultipartFile logoFile) throws Exception {
        BufferedImage image = QrCodeUtils.createImage(content, logoFile);
        return image;
    }

    /*public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }*/

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content      内容
     * @param logoFile     LOGO地址
     * @param output       输出流
     * @throws Exception
     */
    public static void encodeAndReturnStream(String content, MultipartFile logoFile, OutputStream output)
            throws Exception {
        BufferedImage image = QrCodeUtils.createImage(content, logoFile);
        ImageIO.write(image, FORMAT, output);
    }

    /**
     * 获取指定文件的输入流，获取logo
     *
     * @param logoPath 文件的路径
     * @return
     */
    public static InputStream getResourceAsStream(String logoPath) {
        return QrCodeUtils.class.getResourceAsStream(logoPath);
    }

    /**
     * 解析二维码
     *
     * @param file 二维码图片
     * @return
     * @throws Exception
     */
    public static String decodeByFile(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 解析二维码
     *
     * @param path 二维码图片地址
     * @return
     * @throws Exception
     */
    public static String decodeByPath(String path) throws Exception {
        return QrCodeUtils.decodeByFile(new File(path));
    }

    //测试一：
    public static void main(String[] args) throws Exception {
        String text = "https://blog.csdn.net/weixin_43763430";
        //String logoPath = "C:\\Users\\镜中水月\\Desktop\\nick.jpg";

        /*BufferedImage bufferedImage = QrCodeUtils.encodeAndReturnStream(text, null, true);
        // 转为base64
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        System.out.println("data:image/png;base64," + Base64.encode(os.toByteArray()));*/

        String destPath = "C:\\Users\\镜中水月\\Desktop\\jian.jpg";
        StringBuilder builder = new StringBuilder("MECARD:");
        builder.append("N:").append("简相军")
                .append(";TEL:").append("18530069297")
                .append(";VOICE:").append("010-62100001")
                .append(";EMAIL:").append("543190914@qq.com")
                .append(";TITLE:").append("CEO")
                .append(";ORG:").append("河南灯饰有限公司")
                .append(";ADR:").append("北京市朝阳区北四环中路35号")
                .append(";NOTE:").append("备注信息")
                .append(";;");
//        String content = "BEGIN:VCARD\n" +
//                "VERSION:3.0\n" +
//                "N:简相军\n" +
//                "TEL:18530069297\n" +
//                "URL:www.baidu.com\n"+
//                "ADR:河南郑州\n" +
//                "ORG:河南灯饰有限公司\n" +
//                "TITLE:CEO\n" +
//                "NOTE:呼呼测试下吧。。。\n" +
//                "END:VCARD";
        QrCodeUtils.encodeToDestPath(builder.toString(),null,new File(destPath));
    }
}
