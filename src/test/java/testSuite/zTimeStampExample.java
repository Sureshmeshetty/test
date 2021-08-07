package testSuite;

import common.DriverFactory;
import common.SafeActions;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class zTimeStampExample {

    public static void main(String[] a)  {
        Random rand = new Random();//instance of random class

        List<Integer> intList = new ArrayList<>();
        int upperbound = 25;
        //generate random values from 0-24


        for(int i=0;i<25;i++) {
            int random = rand.nextInt(upperbound);
            if(!intList.contains(random))
                intList.add(random);
            else break;
        }
        System.out.println(intList);

        new DriverFactory().stepUp();
        DriverFactory.getDriver().get("https://en-gb.facebook.com/");
        SafeActions.click(By.cssSelector("a[data-testid*=registration]"));
        SafeActions.waitTime(2000);
        SafeActions.selectValueFromDropdown((e)->e.selectByVisibleText("25"), SafeActions.findElement(By.id("day"))); //25
        SafeActions.selectValueFromDropdown((e)->e.selectByValue("7"), SafeActions.findElement(By.id("month"))); //July
        SafeActions.selectValueFromDropdown((e)->e.selectByIndex(22), SafeActions.findElement(By.id("year"))); //2000


        SafeActions.waitTime(5000);

        DriverFactory.getDriver().quit();
    }
}
