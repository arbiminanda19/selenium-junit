package stepDefinitions;

import config.env;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class login extends env {

    String url = "https://www.saucedemo.com/";
    int duration = 10;

    @Before
    public void before() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Given("user is on url homepage")
    public void accessURL() {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit'][@data-test='login-button']"))
        );
    }

    @When("user fill username")
    public void fillUsername() {
        WebElement field_userName = driver.findElement(By.name("user-name"));
        field_userName.isDisplayed();
        field_userName.sendKeys("standard_user");
    }

    @And("user fill password")
    public void fillPassword() {
        WebElement field_password = driver.findElement(By.id("password"));
        Assert.assertTrue(field_password.isEnabled());
        field_password.sendKeys("secret_sauce");
    }

    @And("user click enter to login")
    public void clickEnter(){
        WebElement field_password = driver.findElement(By.id("password"));
        field_password.sendKeys(Keys.ENTER);
    }

    @Then("user verify success login result")
    public void verifyLoginResult(){
        if (driver.getPageSource().contains("Epic sadface")){
            System.out.println("Login Gagal");
        } else {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='title'][contains(text(),'Products')]"))
            );
        }
    }

    @After
    public void after() {
        driver.quit();
    }

}
