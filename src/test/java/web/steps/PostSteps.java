package web.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by ksenie on 30.12.14.
 */
public class PostSteps extends UserSteps {

    //page elements
    private static String addPostId = "nav_addPost";
    private static String postTitleInputId = "post-title-input";
    private static String postTextInputId = "post-text-input";
    private static String submitPostBtnId = "submit_post_btn";
    private static String postTitleId = "post-title";
    private static String postBodyId = "post-message";

    public PostSteps(WebDriver driver) { super(driver); }

    @Step("Send test post")
    public static void addPost(String title, String text){
        driver.findElement(By.id(addPostId)).click();
        driver.findElement(By.id(postTitleInputId)).sendKeys(title);
        driver.findElement(By.id(postTextInputId)).sendKeys(text);
        driver.findElement(By.id(submitPostBtnId)).click();
    }

    @Step("Go to single post page")
    public static void openPostPageByTitle(String postTitle){
        driver.get(homeUrl);
        driver.findElement(By.linkText(postTitle)).click();
    }

    @Step("Post title and body assertion")
    public static void postShouldBe(String expectedTitle, String expectedText){
        String postTitle = driver.findElement(By.id(postTitleId)).getText();
        String postText = driver.findElement(By.id(postBodyId)).getText();
        assertThat(postTitle, equalTo(expectedTitle));
        assertThat(postText, equalTo(expectedText));
    }
}
