package ru.yandex.school.hlebushek;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.yandex.school.hlebushek.common.CookiesService;
import ru.yandex.school.hlebushek.models.Users;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/")
public class Authorization {

    private static final String YANDEX_APP_ID ="008f745997e2428d8f2fbd3548dea765";
    private static final String YANDEX_APP_SECRET  ="2c03c2165d224ef698a8f8db1e2028b3";
    @POST
    @Path("basic")
    public void basicAuth(
            @FormParam("login") String login,
            @FormParam("password") String pass,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) throws IOException {

        String referer = request.getHeader("referer");

        if (login.isEmpty() || pass.isEmpty()) {
            response.sendRedirect(referer.concat("#/auth"));
        } else {
            Users user = Users.first("login = ? ", login);
            if (user != null && pass.equals(user.getPassword())) {
                response.addCookie(CookiesService.setCookieWithUserId(user.getUserId()));
                response.sendRedirect(referer.concat("#/add-new_post"));
            } else {
                response.sendRedirect(referer.concat("#/auth"));
            }
        }
    }

    @GET
    @Path("yandex_setup")
    public void yandexSetup(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response,
            @Context ServletContext test
            ) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target =  client.target("https://oauth.yandex.ru").path("authorize");
        target = target.queryParam("response_type","code").queryParam("client_id",YANDEX_APP_ID);
        response.sendRedirect(target.getUri().toASCIIString());
    }
    
    @GET
    @Path("yandex_authorize")
    public void yandexAuthorize(
            @QueryParam("code") String code,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response
    ) throws IOException {
        if (code!=null){
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("https://oauth.yandex.ru/token");
            Form form = new Form();
            form.param("grant_type","authorization_code");
            form.param("code", code);
            form.param("client_id", YANDEX_APP_ID);
            form.param("client_secret", YANDEX_APP_SECRET);

            String tmp = target.request(MediaType.APPLICATION_JSON_TYPE)
                    .header("charset", "utf-8")
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                            String.class);
            String token =((JsonObject)new JsonParser().parse(tmp)).get("access_token").getAsString();
            Response tmp2 = client.target("https://login.yandex.ru").path("info")
                    .queryParam("format", "json")
                    .queryParam("oauth_token", token).request(MediaType.APPLICATION_JSON_TYPE).
                    header("charset", "utf-8").get();
            JsonObject user_info = (JsonObject)new JsonParser().parse(tmp2.readEntity(String.class));
            Users user = Users.findFirst("yandex_id=?", user_info.get("id").getAsInt());
            if(user==null){
                user = new Users();
                user.setYandexId(user_info.get("id").getAsInt());
                user.setFirstName(user_info.get("first_name").getAsString());
                user.setLastName(user_info.get("last_name").getAsString());
                user.setSmallImagePath("https://avatars.yandex.net/get-yapic/" + user_info.get("default_avatar_id").getAsString() + "/islands-50");
                user.setLargeImagePath("https://avatars.yandex.net/get-yapic/" + user_info.get("default_avatar_id").getAsString() + "/islands-200");
            }
            user.setToken(token);
            user.saveIt();
            response.addCookie(CookiesService.setCookieWithUserId(user.getUserId()));
        }

    }
}
