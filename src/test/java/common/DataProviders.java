package common;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider (name = "productSearch")
    public Object[][] productSearch(){
    	return ExcelReader.getExcelData(Constants.EXCEL_DATA,"Product");
    }

    @DataProvider(name = "jsonProvider")
    public Object[][] jsonData(){
	    return JsonReader.readJsonData(Constants.JSON_DATA);
    }
}
