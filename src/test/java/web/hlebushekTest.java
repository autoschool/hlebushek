package web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;

public class hlebushekTest {
    public WebDriver driver;
    private static final String BS_URL = "http://localhost:8080";

    @Before
    public void openHomePage() {
        driver = new PhantomJSDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testGetTitleMainPage(){
        driver.get(BS_URL);
        assertThat(driver.getTitle(), equalTo("Hlebushek blog"));
    }

    @After
    public void quit(){
        driver.quit();
    }
}
