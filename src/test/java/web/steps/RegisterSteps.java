package web.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by ksenie on 07.01.15.
 */
public class RegisterSteps extends UserSteps {

    private static String registerUrl = "/#/reg";

    //page elements
    private static String loginInputID = "inputLogin";
    private static String firstNameInputID = "inputFirstName";
    private static String lastNameInputID = "inputLastName";
    private static String passwordInputID = "inputPassword3";
    private static String saveAccountBtnID = "createUserBtn";
    private static String userNameStringID = "username-heading";


    public RegisterSteps(WebDriver driver){ super(driver); }

    @Step("Open registration form")
    public void openRegPage(){
        driver.get(homeUrl+registerUrl);
    }

    @Step("Create test user")
    public void fillRegForm(String userLogin, String userFirstName, String userLastName, String userPassword){
        driver.findElement(By.id(loginInputID)).sendKeys(userLogin);
        driver.findElement(By.id(firstNameInputID)).sendKeys(userFirstName);
        driver.findElement(By.id(lastNameInputID)).sendKeys(userLastName);
        driver.findElement(By.id(passwordInputID)).sendKeys(userPassword);
    }

    @Step("Save new user")
    public void saveNewUser(){
        driver.findElement(By.id(saveAccountBtnID)).click();
    }

    @Step("Check if new user is loged in to the blog")
    public void userShouldBe(String userFirstName, String userLastName){
        String userName = driver.findElement(By.id(userNameStringID)).getText();
        assertThat(userName, equalTo(userFirstName + " " + userLastName));
    }

}
