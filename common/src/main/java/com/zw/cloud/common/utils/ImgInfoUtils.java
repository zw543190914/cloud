package com.zw.cloud.common.utils;

import com.alibaba.fastjson2.JSON;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class ImgInfoUtils {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\img\\微信图片_20221125165154.jpg");
        readImageInfo(file);
    }

    /**
     * 提取照片里面的信息
     *
     * @param file 照片文件
     */
    private static void readImageInfo(File file) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        System.out.println("---打印全部详情---");
        System.out.println("metadata:" + JSON.toJSONString(metadata));
        for (Directory directory : metadata.getDirectories()) {
            System.out.println("directory:" + JSON.toJSONString(directory));

            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s\n",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }

        System.out.println("--打印常用信息---");

        Double lat = null;
        Double lng = null;
        for (Directory directory : metadata.getDirectories()) {
            System.out.println("directory:" + JSON.toJSONString(directory));

            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();  //标签名
                String desc = tag.getDescription(); //标签信息
                if (tagName.equals("Image Height")) {
                    System.err.println("图片高度: " + desc);
                } else if (tagName.equals("Image Width")) {
                    System.err.println("图片宽度: " + desc);
                } else if (tagName.equals("Date/Time Original")) {
                    System.err.println("拍摄时间: " + desc);
                } else if (tagName.equals("GPS Latitude")) {
                    System.err.println("纬度 : " + desc);
                    System.err.println("纬度(度分秒格式) : " + pointToLatlong(desc));
                    lat = latLng2Decimal(desc);
                } else if (tagName.equals("GPS Longitude")) {
                    System.err.println("经度: " + desc);
                    System.err.println("经度(度分秒格式): " + pointToLatlong(desc));
                    lng = latLng2Decimal(desc);
                }
            }
        }
        System.err.println("--经纬度转地址--");
    }

    /**
     * 经纬度格式  转换为  度分秒格式 ,如果需要的话可以调用该方法进行转换
     *
     * @param point 坐标点
     * @return
     */
    public static String pointToLatlong(String point) {
        Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
        Double fen = Double.parseDouble(point.substring(point.indexOf("°") + 1, point.indexOf("'")).trim());
        Double miao = Double.parseDouble(point.substring(point.indexOf("'") + 1, point.indexOf("\"")).trim());
        Double duStr = du + fen / 60 + miao / 60 / 60;
        return duStr.toString();
    }

    /***
     * 经纬度坐标格式转换（* °转十进制格式）
     * @param gps
     */
    public static double latLng2Decimal(String gps) {
        String a = gps.split("°")[0].replace(" ", "");
        String b = gps.split("°")[1].split("'")[0].replace(" ", "");
        String c = gps.split("°")[1].split("'")[1].replace(" ", "").replace("\"", "");
        double gps_dou = Double.parseDouble(a) + Double.parseDouble(b) / 60 + Double.parseDouble(c) / 60 / 60;
        return gps_dou;
    }

}
