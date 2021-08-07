package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory extends ExtentManager{

    private static final ThreadLocal<WebDriver> threadWebDriver = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        threadWebDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return threadWebDriver.get();
    }

    public static void remove() {
        threadWebDriver.remove();
    }

    @BeforeSuite
    public void stepUp() {
        System.out.println("=======This is SetUp Method==========");
        setDriver(initializeDriver(Constants.BROWSER));

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Constants.NORMAL_WAIT, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        //If ini file
        Section section = getSection();
        getDriver().get(section.get("app.url"));

        //If properties file
        //getDriver().get(getProperty("app.url"));
    }

    private WebDriver initializeDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_76.exe");
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--verbose");
            //chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            return new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("edge")) {
            //System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }
        else if (browser.equalsIgnoreCase("undefined")) {
            Assert.fail("Invalid browser name, Please provide the valid browser");
        }
        return null;
    }

    public static String getProperty(String key) {
        Properties prop = new Properties();
        String keyValue=null;
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config.properties");
            prop.load(ip);
            keyValue = prop.getProperty(Constants.ENV+"."+key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail("File doesn't exist in the specified location. ");
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to load the property file, check the code !!!");
        }
        if(keyValue == null)
            Assert.fail("The Key '"+key+"' does not found in the config.properties file. Please check");
        return keyValue;
    }

    public static Section getSection(){
        Ini ini = null;
        try{
            ini = new Ini(new File("src/test/resources/config.ini"));
        } catch (IOException e) {
            Assert.fail("File not Found: "+e.getMessage());
        }
        return ini.get(Constants.ENV);
    }
    
    @AfterSuite
    public void tearDown() {
        remove();
        getDriver().quit();
    }
}
