package common;

public class Constants {
    public static String ENV = System.getProperty("env")!=null?System.getProperty("env"):"dev";
    public static String BROWSER = System.getProperty("browser")!=null?System.getProperty("browser"):"chrome";
    public static String EXCEL_DATA = "/src/test/resources/testData/testdata.xlsx";
    public static String JSON_DATA = "/src/test/resources/testData/jsonData.json";
    
    public static int NORMAL_WAIT = 60;
    public static int LONG_WAIT = 120;
}
