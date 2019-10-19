package io.nbaynham.twitter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import static io.nbaynham.webdriver.extensions.WebDriverExtensions.*;
import static org.testng.Assert.*;

public class Twitter {
    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    ;

    @AfterMethod
    public void afterMethod() {
        if (driver != null) driver.quit();
    }

    ;

    @Test
    public void test() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://twitter.com");
        waitForPageLoaded(driver);
        System.out.println(driver.getTitle());
        assertEquals(driver.getTitle(), "Twitter. It's what's happening.");
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();
        waitForPageLoaded(driver);
        WebElement userInputField = (new WebDriverWait(driver, 20))
                .until((ExpectedCondition<WebElement>)
                        d-> d.findElement(By.xpath("//div[@class='signin-wrapper']//input[@name='session[username_or_email]']")));
        userInputField.sendKeys("*****");
        WebElement password = driver.findElement(By.xpath("//div[@class='signin-wrapper']//input[@name='session[password]']"));
        password.click();
        password.sendKeys("*****");
        WebElement loginButton = driver.findElement(By.xpath("//div[@class='signin-wrapper']//*[contains(@class,'submit')]"));
        loginButton.click();
        WebElement pageTitle = (new WebDriverWait(driver, 20))
                .until((ExpectedCondition<WebElement>) d-> d.findElement(By.xpath("//span[contains(.,'Home')]")));
    }
}
