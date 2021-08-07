package common;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class SafeActions extends Synchronization{
    static JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
    static Alert alert;

    /**
     * This Method is used to Click the element Safely until it is clickable
     * @param ele
     */
    public static void click(WebElement ele){
        waitForElementClickable(ele);
        EventListeners.childTest.info("Clicking on the element using: "+ele.toString());
        ele.click();
    }

    /**
     * This Method is used to Click the element Safely until it is clickable
     * @param byLocator
     */
    public static void click(By byLocator){
        waitForElementClickable(byLocator);
        EventListeners.childTest.info("Clicking on the element using: "+byLocator.toString());
        findElement(byLocator).click();
    }

    /**
     * This Method is used to Click the element Safely until it is clickable
     * @param ele
     */
    public static void clickUsingJS(WebElement ele){
        waitForElementClickable(ele);
        EventListeners.childTest.info("Clicking on the element using: "+ele.toString());
        js.executeScript("arguments[0].click();",ele);
    }

    /**
     * This Method is used to type the text Safely until it is visible
     * @param ele
     * @param valueToEnter
     */
    public static void sendKeys(WebElement ele, String valueToEnter){
        waitForElementVisible(ele);
        ele.clear();
        EventListeners.childTest.info("Entering '"+valueToEnter.toUpperCase()+"' in the element using: "+ele.toString());
        ele.sendKeys(valueToEnter);
    }

    /**
     * This Method is used to type the text Safely until it is visible
     * @param ele
     * @param valueToEnter
     */
    public static void sendKeysUsingJS(WebElement ele, String valueToEnter){
        waitForElementVisible(ele);
        EventListeners.childTest.info("Entering '"+valueToEnter.toUpperCase()+"' in the element using: "+ele.toString());
        js.executeScript("arguments[0].value=arguments[1].toString();",ele,valueToEnter);
        //js.executeScript("arguments[0].value='"+valueToEnter+"';",ele);
        //js.executeScript("arguments[0].setAttribute('value',arguments[1].toString());",ele,valueToEnter);
    }

    /**
     * This Method is used to type the text Safely until it is visible
     * @param byLocator
     * @param valueToEnter
     */
    public static void sendKeys(By byLocator, String valueToEnter){
    	EventListeners.childTest.info("Entering '"+valueToEnter.toUpperCase()+"' in the element using: "+byLocator.toString());
        findElement(byLocator).sendKeys(valueToEnter);
    }

    /**
     * This Method is used to find the element
     * @param byLocator
     */
    public static WebElement findElement(By byLocator){
        waitForElementVisible(byLocator);
        return DriverFactory.getDriver().findElement(byLocator);
    }

    /**
     * This Method is used to find the elements
     * @param byLocator
     * @return
     */
    public static List<WebElement> findElements(By byLocator){
        waitForElementVisible(byLocator);
        return DriverFactory.getDriver().findElements(byLocator);
    }

    //Handling Dropdowns (using select tag)

    /**
     * This method is used to select any option from dropdown using Index/Value/VisibleText
     * @param consumer
     * @param selectElement
     * @throws Throwable
     */
    public static void selectValueFromDropdown(Consumer<Select> consumer, WebElement selectElement) {
        try{
            consumer.accept(new Select(selectElement));
            EventListeners.childTest.info("Selecting value from dropdown "+selectElement.toString());
        }
        catch (Exception e){
            EventListeners.childTest.info("Unable to select value from dropdown "+selectElement.toString()+"\nException: "+e.getMessage());
        }
    }

    /**
     * This method is used to select element from the dropdown using the value
     * @param selectElement
     * @param valueOfOption
     */
    public static void selectByValue(WebElement selectElement,String valueOfOption){
    	waitForElementVisible(selectElement);
        Select select = new Select(selectElement);
        EventListeners.childTest.info("Selecting '"+valueOfOption.toUpperCase()+"' Option from Dropdown using: "+selectElement.toString());
        select.selectByValue(valueOfOption);
    }

    /**
     * This method is used to select element from the dropdown using the index
     * @param selectElement
     * @param indexOfOption
     */
    public static void selectByIndex(WebElement selectElement,int indexOfOption){
        Select select = new Select(selectElement);
        EventListeners.childTest.info("Selecting value at index '"+indexOfOption+"' Option from Dropdown using: "+selectElement.toString());
        select.selectByIndex(indexOfOption);
    }

    /**
     * This method is used to select element from the dropdown using the visible text
     * @param selectElement
     * @param visibleTextOfOption
     */
    public static void selectByVisibleText(WebElement selectElement,String visibleTextOfOption){
    	waitForElementVisible(selectElement);
        Select select = new Select(selectElement);
        EventListeners.childTest.info("Selecting '"+visibleTextOfOption.toUpperCase()+"' Option from Dropdown using: "+selectElement.toString());
        select.selectByVisibleText(visibleTextOfOption);
    }

    /**
     * This method is used to deselect element from the dropdown using the value
     * @param selectElement
     * @param valueOfOption
     */
    public static void deselectByValue(WebElement selectElement,String valueOfOption){
    	waitForElementVisible(selectElement);
        Select select = new Select(selectElement);
        EventListeners.childTest.info("Deselecting '"+valueOfOption.toUpperCase()+"' Option from Dropdown using: "+selectElement.toString());
        select.deselectByValue(valueOfOption);
    }

    /**
     * This method is used to deselect element from the dropdown using the index
     * @param selectElement
     * @param indexOfOption
     */
    public static void deselectByIndex(WebElement selectElement,int indexOfOption){
    	waitForElementVisible(selectElement);
        Select select = new Select(selectElement);
        EventListeners.childTest.info("Deselecting value at index '"+indexOfOption+"' Option from Dropdown using: "+selectElement.toString());
        select.deselectByIndex(indexOfOption);
    }

    /**
     * This method is used to deselect element from the dropdown using the visible text
     * @param selectElement
     * @param visibleTextOfOption
     */
    public static void deselectByVisibleText(WebElement selectElement,String visibleTextOfOption){
    	waitForElementVisible(selectElement);
        Select select = new Select(selectElement);
        EventListeners.childTest.info("Deselecting '"+visibleTextOfOption.toUpperCase()+"' Option from Dropdown using: "+selectElement.toString());
        select.deselectByVisibleText(visibleTextOfOption);
    }

    public static boolean isDisplayed(WebElement element){
        try{
            waitForElementVisible(element);
            return element.isDisplayed();
        }
        catch (Exception e){
            return false;
        }
    }

    public static boolean isChecked(WebElement element){
        try{
            waitForElementVisible(element);
            return element.isSelected();
        }
        catch (Exception e){
            return false;
        }
    }

    public static boolean isEnabled(WebElement element){
        try{
            waitForElementVisible(element);
            return element.isEnabled();
        }
        catch (Exception e){
        	EventListeners.childTest.fail("Element is not Enabled using"+element.toString());
            return false;
        }
    }

    public static String getText(WebElement element){
        waitForElementVisible(element);
        EventListeners.childTest.info("Element's Text is "+element.getText());
        return element.getText();
    }

    public static String getAttribute(WebElement element,String attributeName){
        waitForElementVisible(element);
        EventListeners.childTest.info("Element's "+attributeName+" value is "+element.getAttribute(attributeName));
        return element.getAttribute(attributeName);
    }

    public static String getCssValue(WebElement element,String cssProperty){
        waitForElementVisible(element);
        EventListeners.childTest.info("Element's "+cssProperty+" value is "+element.getCssValue(cssProperty));
        return element.getCssValue(cssProperty);
    }

    // Handling Windows
    /**
     * This method is to get the current Window
     */
    public static String getWindowHandle(){
        return DriverFactory.getDriver().getWindowHandle();
    }
    /**
     * This method is to get all Windows
     * @return
     */
    public static Set<String> getWindowHandles(){
    	Set<String> windows = DriverFactory.getDriver().getWindowHandles();
    	EventListeners.childTest.info("Number Of Windows Opened: "+windows.size());
        return windows;
    }

    /**
     * This method is used to switch to specific window
     * @param windowTitle
     */
    public static void switchToWindow(String windowTitle){
        int windowCount = 0;
        for(String currentWindowHandle :getWindowHandles()){
            windowCount++;
            if(DriverFactory.getDriver().getTitle().equals(windowTitle)) {
            	EventListeners.childTest.info("Switching to the Window: "+windowTitle.toUpperCase());
                DriverFactory.getDriver().switchTo().window(currentWindowHandle);
                break;
            }
        }
        if(windowCount>=getWindowHandles().size()) {
        	EventListeners.childTest.fail("Window with title '"+windowTitle+"' does not exist. Please verify the window name properly");
            Assert.fail("Window with title '"+windowTitle+"' does not exist. Please verify the window name properly");
        }
    }

    //Handling Frames

    /**
     * This method is used to switch to frame based on the name or ID
     * @param frameNameOrID
     */
    public static void switchToFrame(String frameNameOrID){
    	EventListeners.childTest.info("Switching to the Frame: "+frameNameOrID.toUpperCase());
        DriverFactory.getDriver().switchTo().frame(frameNameOrID);
    }

    /**
     * This method is used to switch to frame based on the frame index
     * @param frameIndex
     */
    public static void switchToFrame(int frameIndex){
        DriverFactory.getDriver().switchTo().frame(frameIndex);
    }

    /**
     * This method is used to switch to frame based on the WebElement
     * @param frame
     */
    public static void switchToFrame(WebElement frame){
        DriverFactory.getDriver().switchTo().frame(frame);
    }

    /**
     * This method is used to switch to the first frame or main document
     */
    public static void switchToDefaultFrame(){
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    //Handling Alerts

    /**
     * This Method is used to switch to an alert
     */
    public static Alert switchToAlert(){
        try{
        	return DriverFactory.getDriver().switchTo().alert();
        }
        catch(NoAlertPresentException e) {
        	EventListeners.childTest.fail("No Alert Found: "+e.getMessage());
            return null;
        }
    }

    /**
     * This method is used to switch to an alert and get the alert message
     * @return alertMessage
     */
    public static String getAlertMessage(){
        alert = switchToAlert();
        String alertMessage= alert.getText();
        EventListeners.childTest.info("Alert Message: "+alertMessage);
        return alertMessage;
    }

    /**
     * This method is used to switch to an alert and accept the alert
     */
    public static void acceptAlert(){
        alert = switchToAlert();
        EventListeners.childTest.info("Accepting the Alert");
        alert.accept();
    }

    /**
     * This method is used to switch to an alert and dismiss the alert
     */
    public static void dismissAlert(){
        alert = switchToAlert();
        EventListeners.childTest.info("Dismissing the Alert");
        alert.getText();
    }

    public static void highlightElement(WebElement element){
        String bgcolor = getCssValue(element,"border");
        for(int i=0;i<3;i++){
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            waitTime(200);
            js.executeScript("arguments[0].style.border='"+bgcolor+"'", element);
        }
    }

    public static void takeScreenshot(){
        File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
        	String fileName = System.getProperty("user.dir")+"/target/Screenshots/"+Utils.getTimeStamp()+".png";
            FileUtils.copyFile(src, new File(fileName));
            EventListeners.childTest.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
           } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshot(String testCaseName){
        File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
        	String fileName = getScreenshotPath(testCaseName);
            FileUtils.copyFile(src, new File(fileName));
            EventListeners.childTest.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshot(Status status, String testCaseName){
        File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
        	String fileName = getScreenshotPath(testCaseName);
            FileUtils.copyFile(src, new File(fileName));
            EventListeners.childTest.log(status, MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getScreenshotPath(String testCaseName) {
    	return System.getProperty("user.dir")+"/target/Screenshots/"+testCaseName+"_"+Utils.getTimeStamp()+".png";
    }

    public static void scrollToPageDown() {
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
