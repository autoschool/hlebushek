package web.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by ksenie on 07.01.15.
 */
public class UserSteps {
    protected static WebDriver driver;

    protected static String testLogin = "testuser";
    protected static String testPass = "testpassword";

    protected static String homeUrl = "http://localhost:8080";
    protected static String loginUrl = "/#/auth/";

    //page elements
    protected static String loginInputId = "inputLogin";
    protected static String passwordInputId = "inputPassword3";
    protected static String signinBtnId = "sign_btn";

    public UserSteps(WebDriver driver) { this.driver = driver; }

    public void quit() { driver.quit(); }

    @Step("Test user login in.")
    public static void userLogin(){
        driver.get(homeUrl + loginUrl);
        driver.findElement(By.id(loginInputId)).sendKeys(testLogin);
        driver.findElement(By.id(passwordInputId)).sendKeys(testPass);
        driver.findElement(By.id(signinBtnId)).click();
    }

    @Step("Open home url")
    public static void openHomeUrl(){
        driver.get(homeUrl);
    }
}
