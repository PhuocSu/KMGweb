import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.testng.Assert;
import java.time.Duration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

public class FooterTest {
    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(FooterTest.class);
    
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa.702prime.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        logger.info("Bắt đầu testcase: FooterTest");
    }

    @Test
    public void aboutUsBtn_Test() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement aboutUsBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='회사소개']")));

        String originalTab = driver.getWindowHandle(); //get current tab

        aboutUsBtn.click();
        logger.info("Click to the '회사소개' button");
        
        switchToNewTab(originalTab);

        // //Wait to Url
        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlToBe("https://kolonmobilitygroup.com/"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        Assert.assertTrue(isRedirected, "Redirect failed. Current URL: " + driver.getCurrentUrl());
        logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
    }

    @Test
    public void privacyPolicyBtn_Test() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement privacyPolicyBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='개인정보처리방침']")));
        
        String originalTab = driver.getWindowHandle();

        privacyPolicyBtn.click();
        logger.info("Click to the '개인정보처리방침' button");

        switchToNewTab(originalTab);

        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlToBe("https://www.kolonmobilitygroup.com/policy"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        Assert.assertTrue(isRedirected, "Redirect failed. Current URL: " + driver.getCurrentUrl());
        logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
    }

    @Test
    public void landingPageBtn_Test(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement landingPageBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//article[normalize-space(text())='이용약관']")));
        landingPageBtn.click();
        logger.info("Click to the '이용약관' button");

        WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isRedirected = false;
        try{
            isRedirected = waitUrl.until(ExpectedConditions.urlContains("/landingPage"));
        } catch (org.openqa.selenium.TimeoutException e){
            isRedirected = false;
        }
        
        Assert.assertTrue(isRedirected, "Redirect failed. Current URL: " + driver.getCurrentUrl());
        logger.info("Redirected successfully. Current URL: " + driver.getCurrentUrl());
    }

    public void switchToNewTab(String originalTab) {
        //if the new tab is opened, switch to it
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.getWindowHandles().size() > 1);

        //After the new tab open, switch to it
        for(String windowHandle : driver.getWindowHandles()){
            if(!windowHandle.equals(originalTab)){
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
