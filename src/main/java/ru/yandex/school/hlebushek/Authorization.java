package ru.yandex.school.hlebushek;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import org.apache.commons.codec.digest.DigestUtils;
import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import ru.yandex.school.hlebushek.models.Users;

@Path("/")
public class Authorization {

    private static final String VK_APP_ID ="4639315";
    private static final String VK_APP_SECRET ="SFM9Ke2QFDQ0LJGDqo0t";
 
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
                Cookie cookie = new Cookie("hlebushek_auth", String.valueOf(user.getUserId()));
                cookie.setMaxAge(24 * 60 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
                response.sendRedirect(referer.concat("#/account"));
            } else {
                response.sendRedirect(referer.concat("#/auth"));
            }
        }
    }

    @GET
    @Path("vk_setup")
    public void vkSetup(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) throws IOException {
        String referer = request.getHeader("referer");
        ClientIdentifier client = new ClientIdentifier(VK_APP_ID, VK_APP_SECRET);
        OAuth2CodeGrantFlow.Builder builder = OAuth2ClientSupport.authorizationCodeGrantFlowBuilder(client,
                "https://oauth.vk.com/authorize",
                "https://oauth.vk.com/access_token");
        builder.redirectUri(referer.concat("/authorization/vk_authorize"));
        OAuth2CodeGrantFlow flow = builder.property(OAuth2CodeGrantFlow.Phase.AUTHORIZATION, "readOnly", "true")
                .scope("notify")
                .property(OAuth2CodeGrantFlow.Phase.ALL, "response_type", "token")
                .property(OAuth2CodeGrantFlow.Phase.ALL, "v","5.27")
                .build();
        String authorizationUri = flow.start();
        response.sendRedirect(authorizationUri);
    }

    @GET
    @Path("vk_authorize")
    public void vkAuthorize(
            @QueryParam("access_token") String token,
            @QueryParam("user_id") String userId,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) throws IOException {
        Users user = Users.findFirst("vk_id=?", userId);
        String referer = request.getHeader("referer");
        if (user == null){
            user = new Users();
            user.setVkId(Integer.parseInt(userId));
            user.setToken(token);
            user.save();
        }
        Cookie cookie = new Cookie("hlebushek_auth",
                String.valueOf(DigestUtils.md5Hex(String.valueOf(user.getUserId()))));
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect(referer);
    }
}
