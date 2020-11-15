package com.zw.cloud.tools.controller;

import com.zw.cloud.tools.service.api.IEMailSendService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;


@RestController
@RequestMapping("/mail")
public class SendEmailController {


    @Autowired
    private IEMailSendService eMailSendService;

    /**
     * 测试发送文本邮件
     */
    @GetMapping("/sendSimpleMail")
    public void sendmail() {
        String[] to = new String[]{"543190914@qq.com", "1131419236@qq.com"};
        eMailSendService.sendSimpleMail(to, null, "主题：你好普通邮件", "内容：第一封邮件");
    }

    @GetMapping("/sendHtmlMail")
    public void sendHtmlMail() throws Exception {
        String[] to = new String[]{"543190914@qq.com", "1131419236@qq.com"};
        eMailSendService.sendHtmlMail(to, null, "主题：你好html邮件", "<h1>内容：第一封html邮件</h1>");
    }

    @GetMapping("/sendAttachmentsMail")
    //http://localhost:9040/mail/sendAttachmentsMail
    public void sendAttachmentsMail() throws Exception {
        String[] to = new String[]{"543190914@qq.com", "1131419236@qq.com"};
        eMailSendService.sendAttachmentsMail(to, null, "主题：你好html邮件",
                "<h1>内容：第一封html邮件</h1>",
                "导出数据.xlsx",createExcelFile(null));
    }

    @GetMapping("/createExcelFile")
    //http://localhost:9040/mail/createExcelFile
    public ByteArrayResource createExcelFile(HttpServletResponse response)throws Exception {
        // XSSFWork used for .xslx (>= 2007), HSSWorkbook for 03 .xsl
        Workbook workbook = new XSSFWorkbook();//HSSFWorkbook();//WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.createSheet("sheet");

        Row row0 = sheet.createRow(0);
        for (int i = 0; i < 11; i++) {
            row0.createCell(i).setCellValue("HELLO" + i + "Column");
            sheet.autoSizeColumn(i);
        }

        for (int rowNum = 1; rowNum < 200; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int i = 0; i < 11; i++) {
                row.createCell(i).setCellValue("cell" + String.valueOf(rowNum + 1) + String.valueOf(i + 1));;
            }
        }

       /* OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.reset();
            String fn = new String("导出数据".getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fn + ".xlsx");
            response.setContentType("application/binary;charset=ISO8859_1");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        ByteArrayResource byteArrayResource = new ByteArrayResource(byteArrayOutputStream.toByteArray());
        return byteArrayResource;

    }

}
