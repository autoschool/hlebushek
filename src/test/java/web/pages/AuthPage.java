package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import web.htmlelements.LoginForm;

/**
 * Created by ksenie on 17.01.15.
 */
public class AuthPage extends AbstractPage{

    @FindBy(id = "auth-form")
    private LoginForm loginForm;

    public LoginForm getLoginForm(){ return this.loginForm; }

    public AuthPage(final WebDriver driver){
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public void open(){
        driver.get(homeUrl + "/auth");
    }

    public AddPostPage login(String login, String password){
        getLoginForm().login(login, password);
        return new AddPostPage(driver);
    }
}
