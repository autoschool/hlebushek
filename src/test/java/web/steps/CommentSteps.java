package web.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import web.pages.AuthPage;
import web.pages.MainPage;
import web.pages.SinglePostPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by ksenie on 07.01.15.
 */
public class CommentSteps extends LoginSteps {

    private SinglePostPage postPage;
    private MainPage mainPage;

    public CommentSteps(WebDriver driver){
        super(driver);
        mainPage = new MainPage(driver);
    }

    @Step("Find and open single post page by index")
    public void openPostPageByIndex(int postIndex){
        postPage = mainPage.openSinglePostPage(postIndex);
    }

    @Step("Fill and submit comment form")
    public void submitComment(String comment){
        postPage.getCommentForm().getCommentInput().sendKeys(comment);
        postPage.getCommentForm().getSubmitCommentBtn().click();
    }

    @Step("Comment assertion")
    public void commentShouldBe(String expectedComment){
        String commentText = driver.findElement(By.cssSelector(".single-comment:last-child > div >p")).getText();
        assertThat(commentText, equalTo(expectedComment));
    }

    @Step("Set timeout")
    public void waitSeconds(int seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
