package ru.yandex.school.hlebushek.service;

import java.net.URI;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import ru.yandex.school.hlebushek.models.Users;

/**
 *
 * @author Drak_kin
 */
@Path("/authorization")
public class Authorization {
    @Context UriInfo uriInfo;
    @Context ServletContext servletContext;
    private static final String VK_APP_ID ="4639315";
    private static final String VK_APP_SECRET ="SFM9Ke2QFDQ0LJGDqo0t";
 
    @POST
    @Path("/basic")
    public Response Basic(
            @FormParam("login") String login,
            @FormParam("password") String pass,
            @Context HttpServletRequest session){
        Users user = Users.first("login=?", login);
        if(user == null) {
            return Response.serverError().build();
        } else {
            if (pass.equals(user.getPassword())){
                servletContext.setAttribute("user_id", user.getId());
                return Response.seeOther(uriInfo.getBaseUriBuilder().path("/..").build()).build();
            }
        }
        return Response.seeOther(uriInfo.getBaseUriBuilder().path("/..").build()).build();
    }

    @GET
    @Path("/vk_setup")
    public Response vkSetup(){
        ClientIdentifier client = new ClientIdentifier(VK_APP_ID, VK_APP_SECRET);
        OAuth2CodeGrantFlow.Builder builder = OAuth2ClientSupport.authorizationCodeGrantFlowBuilder(client,
                "https://oauth.vk.com/authorize",
                "https://oauth.vk.com/access_token");
        builder.redirectUri(uriInfo.getBaseUriBuilder().path("/autorization/vk_authorize").build().toASCIIString());
        OAuth2CodeGrantFlow flow = builder.property(OAuth2CodeGrantFlow.Phase.AUTHORIZATION, "readOnly", "true").scope("notify")
                .property(OAuth2CodeGrantFlow.Phase.ALL, "response_type", "token")
                .property(OAuth2CodeGrantFlow.Phase.ALL, "v","5.27")
                .build();
        String authorizationUri = flow.start();
        return Response.temporaryRedirect(URI.create(authorizationUri)).build();
    }

    @GET
    @Path("/vk_authorize")
    public Response authorize(@QueryParam("access_token") String token,@QueryParam("user_id") String userId){
        Users user = Users.findFirst("vk_id=?", userId);
        if (user == null){
            user = new Users();
            user.setVkId(Integer.parseInt(userId));
            user.save();
        }
        servletContext.setAttribute("user_id", user.getId());
        user.setToken(token);
        return Response.temporaryRedirect(uriInfo.getBaseUriBuilder().path("/../#/all_posts").build()).build();
    }
}
