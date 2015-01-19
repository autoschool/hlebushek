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
        commentingUser.login();
        commentingUser.openHomeUrl();
        commentingUser.openPostPageByIndex(2);
        commentingUser.submitComment(testComment);
        commentingUser.waitSeconds(5);
        commentingUser.commentShouldBe(testComment);
    }

    @After
    public void quitDriver(){
        driver.quit();
    }
}
