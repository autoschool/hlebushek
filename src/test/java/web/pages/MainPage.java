package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import web.htmlelements.PostWrapper;

import java.util.List;

/**
 * Created by ksenie on 16.01.15.
 */
public class MainPage extends AbstractPage{

    @FindBy(className = "post-wrapper")
    private List<PostWrapper> postList;

    public MainPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(new HtmlElementDecorator(driver), this);
    }

    public SinglePostPage openSinglePostPage(int postIndexOnPage){
        PostWrapper post = postList.get(postIndexOnPage);
        post.getPostTitleLink().click();
        return new SinglePostPage(driver);
    }

    public SinglePostPage openSinglePostPage(String postTitle){
        SinglePostPage postPage = null;
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getPostTitleLink().getText().equals(postTitle)){
                postList.get(i).getPostTitleLink().click();
                postPage = new SinglePostPage(driver);
                break;
            } else {
                continue;
            }
        }
        return postPage;
    }
}
