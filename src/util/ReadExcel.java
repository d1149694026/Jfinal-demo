package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 有判断性的读取
 * @author Administrator
 *
 */
public class ReadExcel {
     /** 错误信息 */  
    private String errorInfo;
    
    /**
     * 验证EXCEL文件是否合法 
     */
    public boolean validateExcel(String filePath){ 
    
        /**判断文件名是否为空或者文件是否存在 */
        if(!CEVUtil.fileExist(filePath)){
            errorInfo = "文件不存在";
            return false; 
        }
    
        /**检查文件是否是Excel格式的文件 */  
        if (!CEVUtil.isExcel(filePath))  {  
            errorInfo = "文件名不是excel格式";  
            return false;  
        } 
        return true;  
    }
    
    /** 
     * @描述：根据文件名读取excel文件 
     */  
    public Map<String,Object> read(String filePath){
        Map<String,Object> map = new HashMap<String,Object>(); 
        InputStream is = null;  
        try{
            /** 验证文件是否合法 */  
            if (!validateExcel(filePath)){ 
                map.put("error", errorInfo);
                return map;
            }  
            /** 判断文件的类型，是2003还是2007 */  
            boolean isExcel2003 = true; 
            if (CEVUtil.isExcel2007(filePath)){ 
                isExcel2003 = false;  
            }  
            /** 调用本类提供的根据流读取的方法 */  
            is = new FileInputStream(new File(filePath));
            Workbook wb = null;  
            if (isExcel2003){  
                wb = new HSSFWorkbook(is);  
            }else{  
                wb = new XSSFWorkbook(is);  
            }
            map.put("wb", wb);
            is.close();
        }catch (IOException e){  
            e.printStackTrace(); 
        }catch (Exception ex){  
            ex.printStackTrace();  
        }finally{  
            if (is != null){  
                try{  
                    is.close();  
                }catch (IOException e){  
                    is = null;  
                    e.printStackTrace();  
                }  
            }  
        }  
        return map;  
    }
    
    /** 
     * @描述：读取数据 
     */  
 
    public List<List<Object>> readExcel(Workbook workbook) 
            throws  FileNotFoundException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
        //Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(excelPath)));
        //String values="";
        Sheet sheet = workbook.getSheetAt(0);
        int startRowNum = sheet.getFirstRowNum();
        int endRowNum = sheet.getLastRowNum();
        List<List<Object>> dataLst = new ArrayList<List<Object>>();
        
        for(int rowNum = startRowNum;rowNum<=endRowNum;rowNum++){
            List<Object> rowLst = new ArrayList<Object>();  
            //values += "(";
            Row row = sheet.getRow(rowNum);
            if(row == null) continue;
            int startCellNum = row.getFirstCellNum();
            int endCellNum = row.getLastCellNum();
            for(int cellNum = startCellNum;cellNum<endCellNum;cellNum++){
                Cell cell = row.getCell(cellNum);
                if(cell == null) continue;
                int type = cell.getCellType(); 
                switch (type) {
                    case Cell.CELL_TYPE_NUMERIC://数值、日期类型
                        double d = cell.getNumericCellValue();
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期类型
                            // Date date = cell.getDateCellValue();
                            Date date = HSSFDateUtil.getJavaDate(d);
                            rowLst.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                            //values +="'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+"',";
                        }else{//数值类型          
                            System.out.print(" "+d+" ");
                            rowLst.add(d);
                        }
                        break;
                    case Cell.CELL_TYPE_BLANK://空白单元格
                        System.out.print(" null ");
                        rowLst.add("");
                        break;
                    case Cell.CELL_TYPE_STRING://字符类型
                        System.out.print(" "+cell.getStringCellValue()+" ");
                        rowLst.add(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN://布尔类型
                        System.out.println(cell.getBooleanCellValue());
                        rowLst.add(cell.getBooleanCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障  
                        System.err.println("非法字符");//非法字符;  
                        rowLst.add("非法字符");
                        break;
                    default:      
                        System.err.println("error");//未知类型
                         rowLst.add("error");
                        break;
                }
            }
            dataLst.add(rowLst);
            System.out.println();
        }
        return dataLst;
    }
    
   
}