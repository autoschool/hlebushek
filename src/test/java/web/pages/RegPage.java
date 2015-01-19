package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import web.htmlelements.UserRegisterForm;

/**
 * Created by ksenie on 19.01.15.
 */
public class RegPage extends AbstractPage{

    @FindBy(id = "reg-form")
    private UserRegisterForm registerForm;

    public UserRegisterForm getRegisterForm(){ return this.registerForm; }

    public RegPage(final WebDriver driver){
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public void open() {
        driver.get(homeUrl + "/reg");
    }

    public AddPostPage registerUser(String login, String firstName, String lastName, String password){
        getRegisterForm().register(login, firstName, lastName, password);
        return new AddPostPage(driver);
    }
}
