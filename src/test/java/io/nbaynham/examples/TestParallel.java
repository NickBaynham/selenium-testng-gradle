package io.nbaynham.examples;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import java.net.URL;
import java.net.MalformedURLException;
import static org.testng.Assert.*;

public class TestParallel {
    private RemoteWebDriver driver;
    private String baseURL;
    private String nodeURL;

    @BeforeTest
    @Parameters({"platform", "browserName", "remoteurl"})
    public void beforeTest(@Optional String platform, @Optional String browserName, @Optional String remoteurl) throws MalformedURLException {
        if (platform == null || platform.isEmpty()) {
            platform = "WIN10";
        }
        if (browserName == null) {
            browserName = "chrome";
        }
        if (remoteurl == null || remoteurl.isEmpty()) {
            remoteurl = "http://localhost:4444/wd/hub";
        }
        baseURL = "https://dzone.com/articles/how-to-run-your-selenium-tests-in-distributed-test";
        nodeURL = "http://192.168.0.11:5566/wd/hub";
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName(browserName);
        capabilities.setPlatform(Platform.extractFromSysProperty(platform));
        capabilities.setVersion("ANY");
        driver = new RemoteWebDriver(new URL(remoteurl), capabilities);
    }

    @Test
    public void test() {
        driver.get(baseURL);
        assertEquals("How to Run Your Selenium Tests in Distributed Test Execution Environments - DZone DevOps", driver.getTitle());
    }

    @AfterTest
    public void afterTest() {
        if (driver != null) driver.quit();
    }
}
