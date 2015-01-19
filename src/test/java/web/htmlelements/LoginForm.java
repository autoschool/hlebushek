package web.htmlelements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by ksenie on 17.01.15.
 */
public class LoginForm extends HtmlElement {

    @FindBy(id = "inputLogin")
    private TextInput loginInput;

    @FindBy(id = "inputPassword3")
    private TextInput passwordInput;

    @FindBy (id = "sign_btn")
    private Button signInBtn;

    public TextInput getLoginInput(){ return this.loginInput; }
    public TextInput getPasswordInput(){ return this.passwordInput; }
    public Button getSignInBtn(){ return this.signInBtn; }

    public void login(String login, String password){
        getLoginInput().sendKeys(login);
        getPasswordInput().sendKeys(password);
        getSignInBtn().click();
    }
}
