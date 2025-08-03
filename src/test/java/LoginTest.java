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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {
    
    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @BeforeTest
    public void setUp() throws MalformedURLException {
        logger.info("Khởi tạo RemoteWebDriver tới Selenium Grid...");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
        } catch (MalformedURLException e) {
            logger.error("URL is malformed", e);
            throw new RuntimeException(e);
        }
        driver.get("https://dev.702prime.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        logger.info("Bắt đầu testcase: loginTest");
    }


    @Test
    public void loginTest() throws Exception {
        List<Map<String, String>> testData = readLoginDataFromExcel();
        for (Map<String, String> row : testData) {
            String username = row.get("username");
            String password = row.get("password");

            logger.info("Test với username: " + username + ", password: " + password);
            driver.get("https://dev.702prime.com/login");

            WebElement id = driver.findElement(By.xpath("//input[@placeholder='아이디 입력']"));
            id.sendKeys(username);
            logger.info("Inputed id: " + username);

            WebElement pass = driver.findElement(By.xpath("//input[@placeholder='비밀번호 입력']"));
            pass.sendKeys(password);
            logger.info("Inputed password: " + password);

            // See password
            WebElement showPassword = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div/div/div/div[1]/form/div/div/div[2]/div/div/div[1]/div/span/span"));
            showPassword.click();
            logger.info("Clicked see password");

            // Click login button
            WebElement loginButton = driver.findElement(By.xpath("//button[.//article[text()='로그인']]"));
            loginButton.click();
            logger.info("Clicked login button");

            // Wait to URL
            logger.info("Wait to URL");
            WebDriverWait waitUrl = new WebDriverWait(driver, Duration.ofSeconds(7));
            boolean isRedirected = false;
            try {
                isRedirected = waitUrl.until(ExpectedConditions.urlToBe("https://dev.702prime.com/"));
            } catch (org.openqa.selenium.TimeoutException e) {
                isRedirected = false;
            }

            if (isRedirected) {
                logger.info("Login successful. Redirected to: " + driver.getCurrentUrl());
                logger.info("Kết quả mong đợi: Success");

                WebElement logoutLink = driver.findElement(By.xpath("//a[text()='로그아웃']"));
                logoutLink.click();
                logger.info("Clicked logout button");
                
            } else {
                logger.warn("Không chuyển sang trang homepage trong thời gian chờ. Current URL: " + driver.getCurrentUrl());
                logger.info("Kết quả mong đợi: Failed");
            }
        }
    }

    // Hàm đọc dữ liệu từ file Excel
    public List<Map<String, String>> readLoginDataFromExcel() throws Exception {
        List<Map<String, String>> dataList = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("login_data.xlsx");
        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // bỏ qua header
            Map<String, String> data = new HashMap<>();
            data.put("username", getCellValue(row.getCell(0)));
            data.put("password", getCellValue(row.getCell(1)));
            data.put("expected", getCellValue(row.getCell(2)));
            dataList.add(data);
        }
        workbook.close();
        return dataList;
    }

    private String getCellValue(Cell cell) {
        return cell == null ? "" : cell.toString();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
