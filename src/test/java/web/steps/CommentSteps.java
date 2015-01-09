package web.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by ksenie on 07.01.15.
 */
public class CommentSteps extends UserSteps {

    //web elements
    private static String postLinkClass = "post_link";
    private static String commentInputId = "comment-input";
    private static String commentSubmitBtnId = "submit-comment-btn";

    public CommentSteps(WebDriver driver){ super(driver); }

    @Step("Find and open first post page from the all posts")
    public void openFirstPostPage(){
        driver.findElement(By.cssSelector(".post-wrapper:first-child")).findElement(By.className(postLinkClass)).click();
    }

    @Step("Fill and submit comment form")
    public void submitComment(String comment){
        driver.findElement(By.id(commentInputId)).sendKeys(comment);
        driver.findElement(By.id(commentSubmitBtnId)).click();

    }

    @Step("Comment assertion")
    public void commentShouldBe(String expectedCcomment){
        String commentText = driver.findElement(By.xpath(".//*[@id='comments-wrapper']/div[1]/div/p")).getText();
        assertThat(commentText, equalTo(expectedCcomment));
    }
}
