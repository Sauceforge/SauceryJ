package au.com.fullcirclesolutions.saucery.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.runners.Parameterized.Parameter;
//import org.junit.runners.Parameterized.Parameters;

@RunWith(Theories.class)
public class GuineaPigTest extends SauceryBase {
    private static String[] datapoints;

    @BeforeClass
    public static void generateData() {
        // do all the work of generating the datapoints
        datapoints = new String[] { "1", "2", "3", "4", "5" };
    }
    
    @DataPoints
    public static String[] data() {
        //return new String[] { "1", "2", "3", "4", "5" };
        return datapoints;
    }
    
    //@Parameters(name = "{index}: Test with X={0}, Y={1}")
    //public static List<int[]> data() {
    //    return Arrays.asList(new int[][] {
    //             { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 }  
    //       });
    //}
    
    //@Parameter // first data value (0) is default
    //public /* NOT private */ int x;

    //@Parameter(1)
    //public /* NOT private */ int y;
    
    public GuineaPigTest(String os, String platform, String browser, String browserVersion, String longName,
            String longVersion, String url, String device, String deviceOrientation) {
        super(os, platform, browser, browserVersion, longName, longVersion, url, device, deviceOrientation);
    }
    
    @Theory
    public void PageTitle(String s) {
        System.out.println(String.format("I am a data driven test testing with data=%1$s.", s));
        
        Driver.navigate().to("https://saucelabs.com/test/guinea-pig");
        
        // verify the page title is correct
        Assert.assertTrue(Driver.getTitle().contains("I am a page title - Sauce Labs"));
    }

    @Theory
    public void LinkWorks(String s) {
        Driver.navigate().to("https://saucelabs.com/test/guinea-pig");
        // find and click the link on the page
        WebElement link = Driver.findElement(By.id("i am a link"));
        link.click();

        // wait for the page to change
        WebDriverWait wait = new WebDriverWait(Driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i_am_an_id")));

        // verify the browser was navigated to the correct page
        Assert.assertTrue(Driver.getCurrentUrl().contains("saucelabs.com/test-guinea-pig2.html"));
    }

    @Theory
    public void UserAgentPresent(String s) {
        Driver.navigate().to("https://saucelabs.com/test/guinea-pig");

        // read the useragent string off the page
        String useragent = Driver.findElement(By.id("useragent")).toString();

        Assert.assertNotNull(useragent);
    }
}
/*
 * Copyright: Andrew Gray, Full Circle Solutions
 * Date: 12th July 2014
 * 
 */