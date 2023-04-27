package com.zw.cloud.tools.controller.excel;


import com.zw.cloud.tools.dao.TcDao;
import com.zw.cloud.tools.dao.UserMapper;
import com.zw.cloud.tools.entity.User;
import com.zw.cloud.tools.handler.poi.SheetHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/tools/poi/excel")
//@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExcelController {

    private final UserMapper mapper;

    private final TcDao tcMapper;

    @PostMapping("/import")
    //http://localhost:9040/tools/poi/excel/import
    public void importRiskRegistrationDetail(MultipartFile file)throws Exception{
        String fileName = file.getOriginalFilename();
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")){
            hssfWorkbook = new XSSFWorkbook(file.getInputStream());//Excel 2007
        } else if (fileName.endsWith("xls")){
            hssfWorkbook = new HSSFWorkbook(file.getInputStream());//Excel 2003
        } else {
            return;
        }
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
                    /*User user = new User();
                    // todo
                    int cell = 0;
                    String name = hssfRow.getCell(cell ++).getStringCellValue();
                    byte age = Byte.valueOf(hssfRow.getCell(cell ++).getStringCellValue());
                    user.setAge(age);
                    user.setName(name);
                    userList.add(user);*/
                    String fieldName = hssfRow.getCell(1).getStringCellValue();

                    String desc = hssfRow.getCell(2).getStringCellValue();
                    if (desc.contains("水槽温度实际温度") || fieldName.equals("e32") || fieldName.contains("e54")) {
                        String targetName = hssfRow.getCell(6).getStringCellValue();
                        // SPEED("d01", "车速", "speed", "车速"),
                        System.out.println(targetName.toUpperCase() + "(\"" + fieldName + "\", \"" + desc + "\", \""+ targetName +"\", \"" + desc + "\"),");
//                        System.out.println("  /**\n" +
//                                "     * "+ desc +"\n" +
//                                "     */\n" +
//                                "    private RedisDeviceAlertBaseModel " + targetName + ";");
                    }

                }
            }
        }
        //mapper.(userList);
    }

    /**
     * 大数据量 使用 SXSSF
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/export")
    //http://localhost:9040/tools/poi/excel/export
    public void export( HttpServletResponse response)throws Exception{

        List<User> userList = new ArrayList<>(10000);

        Random random = new Random();
        User u;
        LocalDateTime date = LocalDateTime.now();
        for (int k = 0; k < 100000 ; k ++){
            u = new User();
            u.setId((long)k);
            u.setName("zw" + k);
            u.setAge((byte)(random.nextInt(100)));
            u.setBir(date);
            //u.setDescription("描述:" + k);
            userList.add(u);
        }
        List<String> nameList = Arrays.asList("id","1", "2", "3", "4","5","blue1","blue2");
        //创建HSSFWorkbook对象(excel的文档对象)
        SXSSFWorkbook wb = new SXSSFWorkbook(200);
        //建立新的sheet对象（excel的表单）
        SXSSFSheet sheet = wb.createSheet("用户数据");
        //在sheet里创建第一行，参数为行索引(excel的行)
        SXSSFRow row1 = sheet.createRow(0);
        //创建单元格并设置单元格内容
        int i = 0;
        for (String name : nameList) {
            row1.createCell(i).setCellValue(name);
            i ++;
        }
        int rowNum = 1;
        for (User tc : userList){
            SXSSFRow row = sheet.createRow(rowNum);
            int cellNo = 0;
            row.createCell(cellNo ++).setCellValue(tc.getId());

            /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String  bir = null;
            if (null != user.getBir()){
                bir = format.format(user.getBir());
            }
            row.createCell(cellNo ++).setCellValue(bir);*/

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
/*        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename="
                + new String("用户数据".getBytes("gbk"), "iso8859-1") + ".xlsx");
        response.setContentType("application/octet-stream");
        wb.write(output);
        output.close();*/

        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("用户数据", String.valueOf(StandardCharsets.UTF_8)));
        wb.write(response.getOutputStream());

    }

    /**
     * 使用 事件模式 进行excel 读取
     * @param multipartFile
     */
    @PostMapping("/parseExcel")
    //http://localhost:9040/tools/poi/excel/parseExcel
    public void parseExcel(MultipartFile multipartFile) throws Exception{
        if (null == multipartFile.getOriginalFilename()){
            return;
        }
        //multipartFile 转为 file
        File file = new File(multipartFile.getOriginalFilename());
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        //1.根据Excel获取OPCPackage对象
        OPCPackage pkg = OPCPackage.open(file, PackageAccess.READ);
        //OPCPackage pkg = OPCPackage.open("E:\\downLoad\\user.xlsx", PackageAccess.READ);
        try {
            //2.创建XSSFReader对象
            XSSFReader reader = new XSSFReader(pkg);
            //3.获取SharedStringsTable对象
            SharedStringsTable sst = reader.getSharedStringsTable();
            //4.获取StylesTable对象
            StylesTable styles = reader.getStylesTable();
            //5.创建Sax的XmlReader对象
            XMLReader parser = XMLReaderFactory.createXMLReader();
            //6.设置处理器
            parser.setContentHandler(new XSSFSheetXMLHandler(styles, sst, new
                    SheetHandler(), false));
            XSSFReader.SheetIterator sheets = (XSSFReader.SheetIterator)
                    reader.getSheetsData();
            //7.逐行读取
            while (sheets.hasNext()) {
                InputStream sheetstream = sheets.next();
                InputSource sheetSource = new InputSource(sheetstream);
                try {
                    parser.parse(sheetSource);
                } finally {
                    sheetstream.close();
                }
            }
            // 删除临时文件
            file.delete();
        } finally {
            pkg.close();
        }
    }

}
