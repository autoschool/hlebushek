package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import web.htmlelements.PostForm;

/**
 * Created by ksenie on 17.01.15.
 */
public class AddPostPage extends AbstractPage{

    @FindBy(id = "add-post-form")
    private PostForm postForm;

    @FindBy(id = "username-heading")
    private TextBlock userNameHeading;

    public PostForm getPostForm(){ return this.postForm; }
    public TextBlock getUserNameHeading(){ return this.userNameHeading; }

    public AddPostPage(final WebDriver driver){
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public void open(){
        driver.get(homeUrl + "/add-new_post");
    }
}
