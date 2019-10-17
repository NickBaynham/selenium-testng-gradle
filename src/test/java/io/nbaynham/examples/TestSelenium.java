package io.nbaynham.examples;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestSelenium {
    private WebDriver driver;

    @Test
    public void testChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.quit();
    }

    @Test
    public void testFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.quit();
    }

    @Test
    public void testEdge() {
        System.setProperty("webdriver.edge.driver", "C:\\webdriver\\MicrosoftWebDriver.exe");
        driver = new EdgeDriver();
        driver.quit();
    }

    @Test
    public void testRemote() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WIN10);
        driver = new RemoteWebDriver(new URL("http://192.168.0.10:5566/wd/hub"), capabilities);
        driver.get("http://github.com");
        driver.quit();
    }

    // mac
    @Test
    public void testRemoteMac() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.MAC);
        driver = new RemoteWebDriver(new URL("http://192.168.0.11:4444/wd/hub"), capabilities);
        driver.get("http://github.com");
        driver.quit();
    }
}
