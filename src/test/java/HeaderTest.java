import org.testng.annotations.Test;
import org.testng.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;


public class HeaderTest {
    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(HeaderTest.class);
    
    @BeforeMethod
    public void setUp() {

        // logger.info("Khởi tạo RemoteWebDriver tới Selenium Grid...");
        // try {
        //     driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
        // } catch (MalformedURLException e) {
        //     logger.error("URL is malformed", e);
        //     throw new RuntimeException(e);
        // }

        driver = new ChromeDriver();    

        driver.get("https://qa.702prime.com/"); 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        logger.info("Bắt đầu testcase: HeaderTest");
    }

    

    //used-car Button
    @Test
    public void usedCarBtn_test() {
        logger.info("Bắt đầu testcase: usedCarBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement customerSupportBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='내차사기']")));
        customerSupportBtn.click();
        logger.info("Click to the '내차사기' button");
        //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/used-car/product-list"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }
    }

    //sellMyCarHomePage button
    @Test
    public void sellMyCarHomePageBtn_test() {
        logger.info("Bắt đầu testcase: sellMyCarHomePageBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement sellMyCarBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='내차팔기']")));
        sellMyCarBtn.click();
        logger.info("Click to the '내차팔기' button"); 

        //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/sellMyCarHomePage"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }
    }

    //new-car button
    @Test
    public void newCarBtn_test() {
        logger.info("Bắt đầu testcase: newCarBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement newCarBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='신차장기렌트']")));
        newCarBtn.click();
        logger.info("Click to the '신차장기렌트' button");

        //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/new-car"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }
    }

    //702Prime button
    @Test
    public void _702PrimeBtn_test() {
        logger.info("Bắt đầu testcase: 702PrimeBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement _702PrimeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='코오롱 인증중고차 소개']")));
        _702PrimeBtn.click();
        logger.info("Click to the '코오롱 인증중고차 소개' button");

        //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/702Prime"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }
    }

    //702Care button => here
    @Test
    public void _702CareBtn_test() {
        logger.info("Bắt đầu testcase: 702CareBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement _702CareBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='702 care+ 소개']")));
        _702CareBtn.click();
        logger.info("Click to the '702 care+ 소개' button");

        //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/702Care"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }
    }

    //Event button
    @Test
    public void eventBtn_test() {
        logger.info("Bắt đầu testcase: eventBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement eventBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='이벤트']")));
        eventBtn.click();
        logger.info("Click to the '이벤트' button");

        //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/event-list"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }
    }

    //Frequently Asked Questions button
    @Test
    public void faqBtn_test() {
        logger.info("Bắt đầu testcase: faqBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement customerSupportBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='고객지원']")));
        customerSupportBtn.click();
        logger.info("Click to the '고객지원' button");

        WebDriverWait waitFaqBtn = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement faqBtn = waitFaqBtn.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space(text())='자주묻는 질문']")));
        faqBtn.click();
        logger.info("Click to the '자주묻는 질문' button"); 

        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/others/FAQ"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }

        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }
    }

    //Notices button
    @Test
    public void noticesBtn_test() {
        logger.info("Bắt đầu testcase: noticesBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement customerSupportBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='고객지원']")));
        customerSupportBtn.click();
        logger.info("Click to the '고객지원' button");

        WebDriverWait waitNoticesBtn = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement noticesBtn = waitNoticesBtn.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space(text())='공지사항']")));
        noticesBtn.click();
        logger.info("Click to the '공지사항' button");

        //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/others/notice"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }

        //Click the '고객지원' button again to close the menu
        customerSupportBtn.click();
        logger.info("Click to the '고객지원' button");
    }

    @Test
    public void myPageBtn_test() {
        logger.info("Bắt đầu testcase: myPageBtn_Test");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement myPageBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space(text())='마이페이지']")));
        myPageBtn.click();
        logger.info("Click to the '마이페이지' button");

        WebDriverWait waitLoginRequiredBtn = new WebDriverWait(driver, Duration.ofSeconds(15));
        // WebElement loginRequiredBtn = waitLoginRequiredBtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//buttonb[normalize-space(text())='로그인하러가기']")));
        loginRequiredBtn.click();
        logger.info("Click to the '로그인하러가기' button");

        //click on '로그인하러가기' button to login
        WebDriverWait waitSignIn = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean isSignIn = false;
        try{
            isSignIn = waitSignIn.until(ExpectedConditions.urlContains("/login"));
            signIn();
        } catch (org.openqa.selenium.TimeoutException e){
            isSignIn = false;
        }

        //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/myPage"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        if (isRedirected) {
            logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Success");            
        } else {
            logger.warn("Cannot redirect. Still current URL: " + driver.getCurrentUrl());
            logger.info("Expected Result: Failed");
        }
    }

    public void signIn(){
            WebElement id = driver.findElement(By.xpath("//input[@placeholder='아이디 입력']"));
            id.sendKeys("Phuoc1211");

            WebElement pass = driver.findElement(By.xpath("//input[@placeholder='비밀번호 입력']"));
            pass.sendKeys("Phuoc1211");

            WebElement loginButton = driver.findElement(By.xpath("//button[.//article[text()='로그인']]"));
            loginButton.click();
            logger.info("Clicked login button");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
