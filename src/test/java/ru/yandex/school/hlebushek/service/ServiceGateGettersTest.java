package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.school.hlebushek.rules.DbConnectionRule;
import ru.yandex.school.hlebushek.db.DatabaseProvider;
import ru.yandex.school.hlebushek.matchers.*;
import javax.ws.rs.core.*;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by ksenie on 19.01.15.
 */

@RunWith(Parameterized.class)
public class ServiceGateGettersTest extends JerseyTest {

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"posts", "post_id", 1},
                {"users", "user_id", 1},
                {"comments", "author_id", 3}
        });
    }

    private String serviceType;
    private String idKey;
    private int id;

    public ServiceGateGettersTest(String serviceType, String idKey, int id){
        this.serviceType = serviceType;
        this.idKey = idKey;
        this.id = id;
    }

    @Rule
    public DbConnectionRule baseConnection = new DbConnectionRule();

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig;
        resourceConfig = new ResourceConfig(ServiceGate.class);
        resourceConfig.register(DatabaseProvider.class);
        return resourceConfig;
    }

    @Test
    public void ServerResponseStatusShouldBe200() {
        int responseStatus = target(serviceType).queryParam(idKey, id).request().get().getStatus();
        assertThat(responseStatus, is(200));
    }

    @Test
    public void ResponseShouldBeJson(){
        Response response = target(serviceType).queryParam(idKey, id).request().get();
        assertThat(response.getMediaType(), is(IsJson.isJson()));
    }

    @Test
    public void existingObjectsDataShouldBeNotNull(){
        Response response = target(serviceType).queryParam(idKey, id).request().get();
        JsonObject json  = (JsonObject) new JsonParser().parse(response.readEntity(String.class));
        JsonElement responseData = json.get("data");
        assertThat(responseData, is(notNullValue()));
    }
}
