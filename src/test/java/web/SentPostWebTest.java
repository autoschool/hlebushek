package web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import web.steps.PostSteps;

/**
 * Created by ksenie on 04.01.15.
 */
public class SentPostWebTest {

    private WebDriver driver;
    private PostSteps postingUser;

    private String testPostTitle = "Web test post title";
    private String testPostBody = "Web test post body";

    @Before
    public void startDriver(){
        driver = new PhantomJSDriver();
        postingUser = new PostSteps(driver);
    }

    @Test
    public void PostIsPublished(){
        postingUser.login();
        postingUser.addPost(testPostTitle, testPostBody);
        postingUser.openPostPageByTitle(testPostTitle);
        postingUser.postShouldBe(testPostTitle, testPostBody);
    }

    @After
    public void quitPostingUser(){
        postingUser.quit();
    }
}
