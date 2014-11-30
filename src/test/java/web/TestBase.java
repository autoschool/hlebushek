package web;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.concurrent.TimeUnit;

//@RunWith(Suite.class)
//@Suite.SuiteClasses( { MainPageWebTest.class, ConnectDBWebTest.class })
public class TestBase {
    public static WebDriver driver;
    public static final String BS_URL = "http://localhost:8080";

    @BeforeClass
    public static void openHomePage() {
        driver = new PhantomJSDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void quit(){
        driver.quit();
    }
}
