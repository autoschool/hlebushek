package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import web.htmlelements.CommentForm;


/**
 * Created by ksenie on 16.01.15.
 */
public class SinglePostPage extends AbstractPage{

    @FindBy(id = "post-title")
    private TextBlock postTitle;

    @FindBy(id = "post-message")
    private TextBlock postMessage;

    @FindBy(id = "CommentForm")
    private CommentForm commentForm;

    @FindBy(id = "username-heading")
    private TextBlock userNameHeading;

    public CommentForm getCommentForm(){
        return this.commentForm;
    }
    public TextBlock getPostTitle() { return this.postTitle; }
    public TextBlock getPostMessage() { return this.postMessage; }
    public TextBlock getUserNameHeading() {return this.userNameHeading; }

    public SinglePostPage (final WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }
}
