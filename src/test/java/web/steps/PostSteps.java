package web.steps;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import web.pages.AddPostPage;
import web.pages.MainPage;
import web.pages.SinglePostPage;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by ksenie on 30.12.14.
 */
public class PostSteps extends LoginSteps{

    private AddPostPage addPostPage;
    private MainPage mainPage;
    private SinglePostPage singlePostPage;

    public PostSteps(WebDriver driver) {
        super(driver);
        addPostPage = new AddPostPage(driver);
    }

    @Step("Send test post")
    public void addPost(String title, String text){
        addPostPage.getPostForm().getPostTitleInput().sendKeys(title);
        addPostPage.getPostForm().getPostBodyInput().sendKeys(text);
        addPostPage.getPostForm().getSubmitPostBtn().click();
    }

    @Step("Go to single post page")
    public void openPostPageByTitle(String postTitle){
        mainPage = addPostPage.openAllPostPage();
        singlePostPage = mainPage.openSinglePostPage(postTitle);
    }

    @Step("Post title and body assertion")
    public void postShouldBe(String expectedTitle, String expectedText){
        String postTitle = singlePostPage.getPostTitle().getText();
        String postText = singlePostPage.getPostMessage().getText();
        assertThat(postTitle, equalTo(expectedTitle));
        assertThat(postText, equalTo(expectedText));
    }
}
