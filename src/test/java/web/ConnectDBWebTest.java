package web;

import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertEquals;

public class ConnectDBWebTest extends TestBase {

    private String expected = "<html><head></head><body>" +
                                "<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">{\"data\":{\"user_id\":2,\"login\":\"testuser\",\"first_name\":\"NameТест\",\"last_name\":\"Lastname\",\"create_date\":null,\"modified_date\":null,\"is_deleted\":false},\"is_error\":false,\"error\":\"\"}" +
                                "</pre></body></html>";

    @Features("Web")
    @Stories("DB")
    @Test
    public void testDBConnect(){
        driver.get(BS_URL+"/service/users?login=testuser");
        assertEquals(expected, driver.getPageSource());
    }


}
