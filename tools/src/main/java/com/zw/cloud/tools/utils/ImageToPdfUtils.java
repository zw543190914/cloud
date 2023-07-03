package com.zw.cloud.tools.utils;

import com.alibaba.fastjson2.JSON;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 图片转成pdf工具类
 */
public class ImageToPdfUtils {

    public static void main(String[] args) {
        List<String> filePathList = new ArrayList<>();
        ImageToPdfUtils.getAllFile(new File("D:\\相册"), filePathList);
        System.out.println(JSON.toJSONString(filePathList));
        File file = ImageToPdfUtils.imageToPdf(filePathList, "testPdf.pdf");
        if (Objects.isNull(file)) {
            throw new RuntimeException("文件夹没有图片");
        }
        File out = new File("D:\\downLoad\\testPdf.pdf");
        FileInputStream fileInputStream = null;
        FileOutputStream outputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(out);
            WritableByteChannel writableByteChannel = Channels.newChannel(outputStream);
            FileChannel fileChannel = fileInputStream.getChannel();
            fileChannel.transferTo(0,fileChannel.size(),writableByteChannel);
            fileChannel.close();
            writableByteChannel.close();
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * imageUrlList：图片路径集合，
     * mOutputPdfFileName：pdf输出位置
     */
    public static File imageToPdf(List<String> imageUrlList, String mOutputPdfFileName) {

        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(mOutputPdfFileName));
            doc.open();
            for (String imageUrl : imageUrlList) {
                doc.newPage();
//                doc.add(new Paragraph("简单使用iText"));
                Image png1 = Image.getInstance(imageUrl);
                float heigth = png1.getHeight();
                float width = png1.getWidth();
                int percent = getPercent2(heigth, width);
                png1.setAlignment(Image.MIDDLE);
                png1.scalePercent(percent);// 表示是原来图像的比例;
                doc.add(png1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
        return new File(mOutputPdfFileName);
    }

    public static void getAllFile(File file, List<String> filePathList) {
        if (Objects.isNull(file)) {
            return;
        }
        if (file.isFile()) {
            filePathList.add(file.getAbsolutePath());
            return;
        }
        File[] files = file.listFiles();
        if (Objects.isNull(files) || files.length == 0) {
            return;
        }
        for (File listFile : files) {
            getAllFile(listFile, filePathList);
        }

    }

    /**
     * 第一种解决方案 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩
     *
     * @param h
     * @param w
     * @return
     */
    private static int getPercent(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        if (h > w) {
            p2 = 297 / h * 100;
        } else {
            p2 = 210 / w * 100;
        }
        p = Math.round(p2);
        return p;
    }

    /**
     * 第二种解决方案，统一按照宽度压缩 这样来的效果是，所有图片的宽度是相等的（推荐）
     *
     * @param
     */
    private static int getPercent2(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        p2 = 530 / w * 100;
        p = Math.round(p2);
        return p;
    }
}
