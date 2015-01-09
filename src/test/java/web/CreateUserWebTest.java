package web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import web.steps.RegisterSteps;

/**
 * Created by ksenie on 07.01.15.
 */
public class CreateUserWebTest {

    private WebDriver driver;
    private RegisterSteps newUser;

    private static String userLogin = "newTestUserLogin";
    private static String userPassword = "newTestUserPassword";
    private static String userFirstName = "UserName";
    private static String userLastName = "UserSurname";

    @Before
    public void createDriver(){
        driver = new PhantomJSDriver();
        newUser = new RegisterSteps(driver);
    }

    @Test
    public void userIsCreated(){
        newUser.openRegPage();
        newUser.fillRegForm(userLogin, userFirstName, userLastName, userPassword);
        newUser.saveNewUser();
        newUser.userShouldBe(userFirstName, userLastName);
    }

    @After
    public void quitNewUser(){
        newUser.quit();
    }


}
