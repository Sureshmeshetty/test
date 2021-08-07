package common;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Synchronization {
    static WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(),Constants.NORMAL_WAIT);

    public static void waitForElementVisible(WebElement element){
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (NoSuchElementException e){
        	EventListeners.childTest.fail("Element is Not Visible using: "+element.toString());
        	Assert.fail("Element is not Visible on the Screen: "+element.toString());
        }
    }

    public static void waitForElementVisible(By byLocator){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        }
        catch (NoSuchElementException e){
        	EventListeners.childTest.fail("Element is Not Visible using: "+byLocator.toString());
        	Assert.fail("Element is not Visible on the Screen: "+byLocator.toString());
        }
    }

    public static void waitForElementClickable(WebElement element){
        try{
            waitForElementVisible(element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (ElementNotInteractableException e){
        	EventListeners.childTest.fail("Element is Not Clickable using: "+element.toString());
            Assert.fail("Element is not Clickable on the Screen: "+element.toString());
        }
    }

    public static void waitForElementClickable(By byLocator){
        try{
            waitForElementVisible(byLocator);
            wait.until(ExpectedConditions.elementToBeClickable(byLocator));
        }
        catch (ElementNotInteractableException e){
        	EventListeners.childTest.fail("Element is Not Visible using: "+byLocator.toString());
            Assert.fail("Element is not Clickable on the Screen: "+byLocator.toString());
        }
    }

    public static void waitTime(int timeToWait){
        try{
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
        }
    }
}
