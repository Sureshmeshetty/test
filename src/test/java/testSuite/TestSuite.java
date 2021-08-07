package testSuite;

import common.Constants;
import common.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.DataProviders;
import common.DriverFactory;
import pages.LandingPage;
public class TestSuite extends DriverFactory{
	LandingPage landingPage;
	@Test(enabled = true, dataProvider = "productSearch", dataProviderClass = DataProviders.class)
	public void validateProductSearch(String keyWord1) {
		landingPage = new LandingPage();
		landingPage.searchForProduct(keyWord1);
		landingPage.selectRandomProduct();
	}
	
	@Test(enabled = false)
	public void negativeTest() {
		Assert.fail();
	}

	@Test(enabled = false,dataProvider = "jsonProvider", dataProviderClass = DataProviders.class)
	public void testJsonData(String userName,String password){
		System.out.println("UserName: "+userName+"\t Password: "+password);
	}

	@Test(enabled = true, dataProvider = "jsonProvider", dataProviderClass = DataProviders.class)
	public void validateProductSearchUsingJson(String keyWord1) {
		landingPage = new LandingPage();
		landingPage.searchForProduct(keyWord1);
		landingPage.selectRandomProduct();
	}


	@Test(enabled = true)
	public void validateProductSearch() {
		String[][] stringArry = ExcelReader.getExcelData(Constants.EXCEL_DATA,"Product");
		for(int i=0;i<stringArry.length;i++){ //4
			for(int j=0;j<stringArry[i].length;j++){//1
				landingPage = new LandingPage();
				landingPage.searchForProduct(stringArry[i][j]);
				landingPage.selectRandomProduct();
			}
		}
	}
}
