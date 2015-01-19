package web.steps;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import web.pages.AddPostPage;
import web.pages.RegPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by ksenie on 07.01.15.
 */
public class RegisterSteps extends LoginSteps {

    private RegPage regPage;
    private AddPostPage addPostPage;

    public RegisterSteps(WebDriver driver){
        super(driver);
        regPage = new RegPage(driver);
    }

    @Step("Open register page")
    public void openRegPage(){
        regPage.open();
    }

    @Step("Create test user")
    public void fillAndSaveForm(String userLogin, String userFirstName, String userLastName, String userPassword){
        addPostPage = regPage.registerUser(userLogin, userFirstName, userLastName, userPassword);
    }

    @Step("Check if new user is loged in to the blog")
    public void userShouldBe(String userFirstName, String userLastName){
        String userName = addPostPage.getUserNameHeading().getText();
        assertThat(userName, equalTo(userFirstName + " " + userLastName));
    }

}
