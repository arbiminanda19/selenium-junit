package monitorApp;

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

public class deleteData {

    WebDriver driver;
    String url = "https://stormy-stream-35141.herokuapp.com/";
    int duration = 10;

    @Before
    public void login() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Smart Growth Chamber Monitoring App')]"))
        );
    }

    @Test
    public void deleteData() {
        WebElement btn_data = driver.findElement(By.xpath("//a[@href='/data']"));
        btn_data.click();

        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr/td//button[@type='button'][2]"))
        );

        WebElement btn_delete = driver.findElement(By.xpath("//tbody/tr/td//button[@type='button'][2]"));
        btn_delete.click();
        driver.switchTo().alert().accept();

        wait.until(
                ExpectedConditions.alertIsPresent()
        );
        driver.switchTo().alert().accept();
    }

    @Test
    public void cancelDeleteData() {
        WebElement btn_data = driver.findElement(By.xpath("//a[@href='/data']"));
        btn_data.click();

        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr/td//button[@type='button'][2]"))
        );

        WebElement btn_delete = driver.findElement(By.xpath("//tbody/tr/td//button[@type='button'][2]"));
        btn_delete.click();

        driver.switchTo().alert().dismiss();
        btn_delete.isEnabled();

    }

    @After
    public void after(){
        driver.quit();
    }

}
