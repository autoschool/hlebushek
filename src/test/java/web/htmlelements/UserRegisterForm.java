package web.htmlelements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by ksenie on 19.01.15.
 */
public class UserRegisterForm extends HtmlElement {

    @FindBy(id = "inputLogin")
    private TextInput loginInput;

    @FindBy(id = "inputFirstName")
    private TextInput firstNameInput;

    @FindBy(id = "inputLastName")
    private TextInput lastNameInput;

    @FindBy(id = "inputPassword3")
    private TextInput passwordInput;

    @FindBy(id = "createUserBtn")
    private Button createUserBtn;

    public TextInput getLoginInput(){ return this.loginInput; }
    public TextInput getFirstNameInput(){ return this.firstNameInput; }
    public TextInput getLastNameInput() {return this.lastNameInput; }
    public TextInput getPasswordInput() {return this.passwordInput; }
    public Button getCreateUserBtn() { return this.createUserBtn; }

    public void register(String login, String firstName, String lastName, String password){
        getLoginInput().sendKeys(login);
        getFirstNameInput().sendKeys(firstName);
        getLastNameInput().sendKeys(lastName);
        getPasswordInput().sendKeys(password);
        getCreateUserBtn().click();
    }
}
