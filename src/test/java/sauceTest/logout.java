package sauceTest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class logout {

    @Test
    public void main() {
        WebDriver driver;
        String url = "https://www.saucedemo.com/";

        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        int duration = 10;
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit'][@data-test='login-button']"))
        );
        WebElement field_userName = driver.findElement(By.name("user-name"));
        WebElement field_password = driver.findElement(By.id("password"));

        field_userName.isDisplayed();
        field_userName.sendKeys("standard_user");
        Assert.assertTrue(field_password.isEnabled());
        field_password.sendKeys("secret_sauce");
        //driver.findElement(By.xpath("//input[@type='submit'][@data-test='login-button']")).click();
        //or bisa pake click
        field_password.sendKeys(Keys.ENTER);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='title'][contains(text(),'Products')]"))
        );

        WebElement btn_option = driver.findElement(By.id("react-burger-menu-btn"));
        WebElement btn_logout = driver.findElement(By.id("logout_sidebar_link"));
        btn_option.click();
        btn_logout.click();

        driver.quit();
    }
}