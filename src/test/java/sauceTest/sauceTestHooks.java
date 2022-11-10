package sauceTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.annotations.Test;

public class sauceTestHooks {

    WebDriver driver;
    String url = "https://www.saucedemo.com/";
    int duration = 10;

    @Before
    public void login() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
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
        field_password.sendKeys(Keys.ENTER);
        if (driver.getPageSource().contains("Epic sadface")){
            System.out.println("Login Gagal");
        } else {
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='title'][contains(text(),'Products')]"))
            );
        }
    }

    @Test
    public void successBuy() {

        WebDriverWait wait = new WebDriverWait(driver, duration);

        driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a[contains(@class,'shopping_cart_link')]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText(), "Sauce Labs Backpack");

        driver.findElement(By.id("checkout")).click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(),'Checkout: Your Information')]"))
        ));

        driver.findElement(By.id("first-name")).sendKeys("Test");
        driver.findElement(By.id("last-name")).sendKeys("Automation");
        driver.findElement(By.id("postal-code")).sendKeys("078901");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'THANK YOU FOR YOUR ORDER')]"))
        ));

    }

    @Test
    public void logout() {

        WebDriverWait wait = new WebDriverWait(driver, duration);
        WebElement btn_option = driver.findElement(By.id("react-burger-menu-btn"));
        WebElement btn_logout = driver.findElement(By.id("logout_sidebar_link"));

        btn_option.click();
        btn_logout.click();

    }

    @After
    public void quit() {
        driver.quit();
    }

}
