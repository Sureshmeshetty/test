package common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {
	@SuppressWarnings("resource")
	public static String[][] getExcelData(String fileName,String sheetName){
        String[][] data = null;
        try
        {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            int noOfRows = sh.getPhysicalNumberOfRows();

            XSSFRow row = sh.getRow(0);
            int noOfCols = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRows-1][noOfCols];
            for(int i =1; i<noOfRows;i++){
                for(int j=0;j<noOfCols;j++){
                    row = sh.getRow(i);
                    cell= row.getCell(j);
                    data[i-1][j] = cell.getStringCellValue();
                }
            }
        }
        catch (Exception e) {
            System.out.println("The exception is: " +e.getMessage());
        }
        return data;
    }
}
