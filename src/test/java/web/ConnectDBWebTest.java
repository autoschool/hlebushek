package web;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectDBWebTest extends TestBase {


    @Test
    public void testTitleMainPage(){
        driver.get(BS_URL+"/service/posts?post_id=1");
        assertEquals("<html><head></head><body><pre style=\"word-wrap: break-word; white-space: pre-wrap;\">{\"data\":{\"post_id\":1,\"title\":\"title тест\",\"message\":\"Body test\",\"author_id\":2,\"create_date\":\"2013-11-12\",\"modified_date\":null,\"is_deleted\":false,\"first_name\":\"Name\",\"last_name\":\"Lasrname\"},\"is_error\":false,\"error\":\"\"}</pre></body></html>", driver.getPageSource());

    }


}
