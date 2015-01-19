package web.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import web.pages.AddPostPage;
import web.pages.AuthPage;
import web.pages.MainPage;
import web.pages.SinglePostPage;

/**
 * Created by ksenie on 07.01.15.
 */
public class LoginSteps {
    protected static WebDriver driver;

    private AuthPage authPage;
    private AddPostPage newPostPage;

    private static String testLogin = "testuser";
    private static String testPass = "testpassword";

    protected static String homeUrl = "http://localhost:8080/";

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        authPage = new AuthPage(driver);
    }

    public void quit() { driver.quit(); }

    @Step("System login")
    public void login(){
        authPage.open();
        newPostPage = authPage.login(testLogin, testPass);
    }

    @Step("Open home url")
    public void openHomeUrl(){
        driver.get(homeUrl);
    }
}
