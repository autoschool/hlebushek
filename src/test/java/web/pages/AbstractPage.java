package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import web.htmlelements.MainNav;

/**
 * Created by ksenie on 17.01.15.
 */
public class AbstractPage {

    protected WebDriver driver;
    protected String homeUrl = "http://localhost:8080/#";

    //test login info
    private String testLogin = "testuser";
    private String testPass = "testpassword";

    public String getTestLogin(){ return this.testLogin; }
    public String getTestPass(){ return this.testPass; }

    @FindBy(className = "menue_links")
    private MainNav navigationBar;

    public MainNav getNavigationBar(){ return  this.navigationBar; }

    public AbstractPage(final WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public MainPage openAllPostPage(){
        this.getNavigationBar().getGoToAllPostsNav().click();
        return new MainPage(driver);
    }
}
