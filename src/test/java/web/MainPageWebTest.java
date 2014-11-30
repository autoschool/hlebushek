package web;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MainPageWebTest extends TestBase {


    @Test
    public void testTitleMainPage(){
        driver.get(BS_URL);
        assertThat(driver.getTitle(), equalTo("Hlebushek blog"));
    }


}
