package excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AnalysisExcel {

    private static final String EXCEL_FORMAT_1 = ".xls";
    private static final String EXCEL_FORMAT_2 = ".xlsx";

    /**
     * 获取Excel中的内容
     * @param filePath 文件路径
     * @param columns Excel的列ID
     * @return
     */
    public static List<Map<String,String>> getExcelData(String filePath,String[] columns){
        //根据路径读取Excel文件
        Workbook workbook = readExcel(filePath);
        //用来存放表中数据
        List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
        if(workbook != null){
            //获取第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            Row row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            String cellData ;
            for (int i = 1; i < rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row != null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    continue;
                }
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 根据EXCEL的不同格式，生成不同的对象
     * @param filePath
     * @return
     */
    public static Workbook readExcel(String filePath){
        if(StringUtils.isEmpty(filePath) || StringUtils.isBlank(filePath)){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath);
            if(EXCEL_FORMAT_1.equals(extString)){
                return new HSSFWorkbook(inputStream);
            }else if(EXCEL_FORMAT_2.equals(extString)){
                return new XSSFWorkbook(inputStream);
            }else{
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 格式化单元格中的内容
     * @param cell
     * @return
     */
    public static Object getCellFormatValue(Cell cell){
        Object cellValue;
        if(cell != null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }


    public static void main(String[] args) {
        String filePath = "/Users/chenjiancong/Desktop/Tigerobo/AnXin/智能资讯平台项目POC测试/POC测试资讯包/个股新闻.xlsx";
        String columns[] = {"id","TITLE","来源","时间","摘要","内容"};
        List<Map<String,String>> mapList = getExcelData(filePath, columns);
        //遍历解析出来的list
        for (Map<String,String> map : mapList) {
            for (Entry<String,String> entry : map.entrySet()) {
                System.out.print(entry.getKey()+":"+entry.getValue()+",");
            }
            System.out.println();
        }

    }

}