package ru.yandex.school.hlebushek.api;

import java.net.URI;
import java.util.stream.DoubleStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.TokenResult;
import org.javalite.activejdbc.Model;
import ru.yandex.school.hlebushek.models.Users;

/**
 *
 * @author Drak_kin
 */
@Path("/autorization")
public class Authorization {
    
    @POST
    @Path("/basic")
    public Response Basic(@FormParam("login") String login, @FormParam("password") String pass, @Context HttpServletRequest session){
        Users user = Users.first("login=?", login);
        if(user == null){
            user = new Users();
            user.setLogin(login);
            user.setPassword(pass);
            user.save();
            session.setAttribute("user_id", user.getId());
        }else{
            if (pass.equals(user.getPassword())){
                session.setAttribute("user_id", user.getId());
            }
        }
        return Response.seeOther(URI.create("http://localhost:8080/")).build();
    }
    @GET
    @Path("/vk_setup")
    public Response vkSetup(){
        ClientIdentifier client = new ClientIdentifier("4639315", "SFM9Ke2QFDQ0LJGDqo0t");
        OAuth2CodeGrantFlow.Builder builder = OAuth2ClientSupport.authorizationCodeGrantFlowBuilder(client,
                "https://oauth.vk.com/authorize",
                "https://oauth.vk.com/access_token");
        builder.redirectUri("http://localhost:8080/service/autorization/vk_authorize");
        OAuth2CodeGrantFlow flow = builder.property(OAuth2CodeGrantFlow.Phase.AUTHORIZATION, "readOnly", "true").scope("notify")
                .property(OAuth2CodeGrantFlow.Phase.ALL, "response_type", "token")
                .property(OAuth2CodeGrantFlow.Phase.ALL, "v","5.27")
                .build();
        String authorizationUri = flow.start();
        return Response.temporaryRedirect(URI.create(authorizationUri)).build();
    }
    @GET
    @Path("/vk_authorize")
    public Response authorize(@QueryParam("access_token") String token,@QueryParam("user_id") String userId, @Context HttpServletRequest req){
        return Response.temporaryRedirect(URI.create("http://localhost:8080/#/all_posts")).build();
    }
}
