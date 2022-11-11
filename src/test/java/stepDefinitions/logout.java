package stepDefinitions;

import config.env;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class logout extends env {

    int duration = 10;

    @When("user click logout")
    public void logout() {
        WebElement btn_option = driver.findElement(By.id("react-burger-menu-btn"));
        WebElement btn_logout = driver.findElement(By.id("logout_sidebar_link"));

        btn_option.click();
        btn_logout.click();
    }

    @Then("user successfully logout")
    public void successLogout() {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit'][@data-test='login-button']"))
        );
    }

}
