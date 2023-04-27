package com.zw.cloud.tools.handler.poi;

import com.alibaba.fastjson2.JSON;
import com.zw.cloud.tools.entity.User;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


/***
 * 自定义Sheet基于Sax的解析处理器
 */
public class SheetHandler implements XSSFSheetXMLHandler.SheetContentsHandler {

    private static Logger logger = LoggerFactory.getLogger(SheetHandler.class);
    //封装实体对象
    private User user;
    /**
     * 解析行开始
     */
    @Override
    public void startRow(int rowNum) {
        if (rowNum > 0) {
            user = new User();
        }
    }

    /**
     *  解析每一个单元格
     * @param cellReference 单元格名称
     * @param formattedValue 单元格value
     * @param comment 批注
     */
    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        if (user != null) {
            switch (cellReference.substring(0, 1)) {
                case "A":
                    user.setId(Long.valueOf(formattedValue));
                    break;
                case "B":
                    user.setName(formattedValue);
                    break;
                case "C":
                    user.setAge(Byte.valueOf(formattedValue));
                    break;
               /* case "D":
                    user.setDescription(formattedValue);
                    break;*/
                case "E":
                    LocalDateTime date = LocalDateTime.parse(formattedValue);
                    user.setBir(date);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 解析行结束
     */
    @Override
    public void endRow(int rowNum) {
        System.out.println(JSON.toJSONString(user));
        // 处理业务。。。
    }

    //处理头尾
    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
    }
}