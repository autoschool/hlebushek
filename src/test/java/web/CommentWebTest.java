package web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import web.steps.CommentSteps;

/**
 * Created by ksenie on 08.01.15.
 */
public class CommentWebTest {
    private WebDriver driver;
    private CommentSteps commentingUser;

    private String testComment = "The very test comment";

    @Before
    public void startDriver() {
        driver = new PhantomJSDriver();
        commentingUser = new CommentSteps(driver);
    }

    @Test
    public void commentIsPublished(){
        commentingUser.userLogin();
        commentingUser.openHomeUrl();
        commentingUser.openFirstPostPage();
        commentingUser.submitComment(testComment);
        commentingUser.commentShouldBe(testComment);
    }

    @After
    public void quitDriver(){
        driver.quit();
    }
}
