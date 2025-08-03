import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerManagementTest{
    private static final Logger logger = LoggerFactory.getLogger(CustomerManagementTest.class);
    WebDriver  driver;
     //access menu
    @BeforeMethod
    public void setUp() {
        logger.info("Khởi tạo WebDriver dùng chung cho toàn bộ test suite");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://dev.702prime.com/login"); //https://qa.702prime.com/

        //Có thể login tại đây nếu muốn dùng chung session
        WebElement id = driver.findElement(By.xpath("//input[@placeholder='아이디 입력']"));
        id.sendKeys("phuoc1211"); //Phuoc1211

        WebElement pass = driver.findElement(By.xpath("//input[@placeholder='비밀번호 입력']"));
        pass.sendKeys("phuoc1211"); //Phuoc1211

        WebElement loginButton = driver.findElement(By.xpath("//button[.//article[text()='로그인']]"));
        loginButton.click();

        logger.info("Bắt đầu testcase: customerManagementTest-dev");

        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // driver.get("https://qa.702prime.com/login"); //https://qa.702prime.com/

        // //Có thể login tại đây nếu muốn dùng chung session
        // WebElement id = driver.findElement(By.xpath("//input[@placeholder='아이디 입력']"));
        // id.sendKeys("Phuoc1211"); 

        // WebElement pass = driver.findElement(By.xpath("//input[@placeholder='비밀번호 입력']"));
        // pass.sendKeys("Phuoc1211"); 

        // WebElement loginButton = driver.findElement(By.xpath("//button[.//article[text()='로그인']]"));
        // loginButton.click();

        // logger.info("Bắt đầu testcase: customerManagementTest-qa");
    }   

    @Test
    public void accessMenuTest() {
        /*String currentUrl = driver.getCurrentUrl();
        if(currentUrl == "https://qa.702prime.com/") {
            logger.info("Accesseded menu");
        }*/

        String currentUrl = driver.getCurrentUrl();
        if(currentUrl == "https://qa.702prime.com/") {
            logger.info("Accesseded menu");
        }   
    }



    
    @Test
    public void modifyUserInfoTest() {
        
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
