package com.zw.cloud.feignconsumer.controller;

import com.zw.cloud.db.dao.UserMapper;
import com.zw.cloud.db.entity.User;
import com.zw.cloud.db.entity.UserExample;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/consumer/excel")
public class ExcelController {

    @Autowired
    private UserMapper mapper;

    @PostMapping("/import")
    //http://localhost:9010/consumer/excel/import
    public void importRiskRegistrationDetail(MultipartFile file)throws Exception{
        String fileName = file.getOriginalFilename();
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")){
            hssfWorkbook = new XSSFWorkbook(file.getInputStream());//Excel 2007
        }else if (fileName.endsWith("xls")){
            hssfWorkbook = new HSSFWorkbook(file.getInputStream());//Excel 2003
        }
        Date date = new Date();
        List<User> userList = new ArrayList<>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
            //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    User user = new User();
                    // todo
                    int cell = 0;
                    String name = hssfRow.getCell(cell ++).getStringCellValue();
                    user.setName(name);
                    userList.add(user);
                }
            }
        }
        mapper.batchInsert(userList);
    }

    @GetMapping(value = "/exportRiskRegistration")
    public void exportRiskRegistration( HttpServletResponse response)throws Exception{
        List<User> userList = mapper.selectByExampleWithBLOBs(new UserExample());
        if (CollectionUtils.isEmpty(userList)){
            throw new Exception("data is empty");
        }
        List<String> nameList = Arrays.asList("机房","状态", "风险ID", "风险任务ID", "关联风险库ID", "关联问题单", "风险分类", "系统类别", "一级组件",
                "风险检查内容", "风险描述","风险级别","识别时间","识别人","负责人","最后更新人","最后更新时","方案");
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("风险登记册");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
        //创建单元格并设置单元格内容

        int i = 0;
        for (String name : nameList) {
            row1.createCell(i).setCellValue(name);
            i ++;
        }
        int rowNum = 1;
        for (User user : userList){
            HSSFRow row=sheet.createRow(rowNum);
            int cellNo = 0;
            row.createCell(cellNo ++).setCellValue(user.getId());
            row.createCell(cellNo ++).setCellValue(user.getName());
            row.createCell(cellNo ++).setCellValue(user.getDescription());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String  bir = null;
            if (null != user.getBir()){
                bir = format.format(user.getBir());
            }
            row.createCell(cellNo ++).setCellValue(bir);


            String description = user.getDescription();
            /*if (StringUtils.isNotBlank(description)){
                for(int j = 0; j < attachments.size() ; j ++ ){
                    Cell cell = row.createCell(cellNo + j);
                    //附件超链接   CELL_TYPE_FORMULA
                    cell.setCellType(CellType.FORMULA);
                    cell.setCellFormula("HYPERLINK(\"" + url + "/file/download?fileName=" + attachments.get(j).getAttachmentName() + "&ossKey=" + attachments.get(j).getAttachmentUrl() +"\",\"" + attachments.get(j).getAttachmentName() + "\")");
                }
            }*/
            rowNum ++;
        }

        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename="
                + new String("风险登记册".getBytes("gbk"), "iso8859-1") + ".xls");
        response.setContentType("application/octet-stream");
        wb.write(output);
        output.close();

    }


}
