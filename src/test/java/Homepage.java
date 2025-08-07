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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;

public class Homepage{
    private static final Logger logger = LoggerFactory.getLogger(Homepage.class);
    WebDriver  driver;

    @BeforeMethod
    public void setUp() {
        logger.info("Khởi tạo WebDriver dùng chung cho toàn bộ test suite");
        driver = new ChromeDriver();
        // logger.info("Khởi tạo RemoteWebDriver tới Selenium Grid...");
        // try {
        //     driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
        // } catch (MalformedURLException e) {
        //     logger.error("URL is malformed", e);
        //     throw new RuntimeException(e);
        // }

        driver.get("https://qa.702prime.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        signIn();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe("https://qa.702prime.com/"));

        logger.info("Bắt đầu testcase: HomepageTest");

    }   

    @Test
    public void accessMenuTest() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://qa.702prime.com/", "Current URL is not correct");
        logger.info("Accesseded menu");
    }

    @Test
    public void searchModel_Test() {
        WebDriverWait waitManufacturer = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement manufacturer = waitManufacturer.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='제조사']")));
        manufacturer.click();
        logger.info("Clicked to the '제조사' button");

        //Search bằng BMW
        WebDriverWait waitBMWModel = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement modelBMW = waitBMWModel.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='BMW']")));
        modelBMW.click();
        logger.info("Clicked to the 'BMW' button");

        WebDriverWait waitSearchBtn = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchBtn = waitSearchBtn.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//article[normalize-space(text())='조회']]")));
        searchBtn.click();
        logger.info("Clicked to the '조회' button");

        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/used-car/product-list"));
            logger.info("Redirected successfully to the product list page. Current URL: " + driver.getCurrentUrl());
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
            logger.info("Cannot redirect to the product list page");
        }

        List<WebElement> carItems = driver.findElements(By.xpath("//*[starts-with(@id, 'product-used-car-rent-item-')]"));
        Assert.assertTrue(carItems.size() > 0, "No car items found");
        logger.info("Found " + carItems.size() + " car items");

    }

    @Test
    public void searchBudget_Test(){
        WebDriverWait waitBudget = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement budgetBtn = waitBudget.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[text()='예산이 정해져있어요']")));
        budgetBtn.click();
        logger.info("Clicked to the '예산이 정해져있어요' button");

        WebElement minBudgetElement = driver.findElement(By.xpath("//span[.//span[text()='최소 예산']]"));
        Select select1 = new Select(minBudgetElement);
        select1.selectByVisibleText("1,000만원");
        logger.info("Selected '1,000만원' from the minBudgetDropdown");

        WebElement maxBudgetElement = driver.findElement(By.xpath("//span[.//span[text()='최대 예산']]"));
        Select select2 = new Select(maxBudgetElement);
        select2.selectByVisibleText("2,000만원");
        logger.info("Selected '2,000만원' from the maxBudgetDropdown");

        WebElement searchBtn = driver.findElement(By.xpath("//button[.//article[normalize-space(text())='조회']]"));
        searchBtn.click();
        logger.info("Clicked to the '조회' button");

        WebDriverWait waitURL = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean isRedirected = false;
        try{
            isRedirected = waitURL.until(ExpectedConditions.urlContains("/used-car/product-list"));
            logger.info("Redirected successfully to the product list page. Current URL: " + driver.getCurrentUrl());
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
            logger.info("Cannot redirect to the product list page");
        }

        List<WebElement> carItems = driver.findElements(By.xpath("//*[starts-with(@id, 'product-used-car-rent-item-')]"));
        Assert.assertTrue(carItems.size() > 0, "No car items found");
        logger.info("Found " + carItems.size() + " car items");
    }

    public void signIn(){
        WebElement id = driver.findElement(By.xpath("//input[@placeholder='아이디 입력']"));
        id.sendKeys("Phuoc1211"); //Phuoc1211

        WebElement pass = driver.findElement(By.xpath("//input[@placeholder='비밀번호 입력']"));
        pass.sendKeys("Phuoc1211"); //Phuoc1211

        WebElement loginButton = driver.findElement(By.xpath("//button[.//article[text()='로그인']]"));
        loginButton.click();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
