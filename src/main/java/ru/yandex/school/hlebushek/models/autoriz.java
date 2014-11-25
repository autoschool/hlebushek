package ru.yandex.school.hlebushek.models;

import java.net.URI;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.TokenResult;

/**
 *
 * @author Drak_kin
 */
@Path("/auto")
public class autoriz {
    @Context
    UriInfo uri;
        
    OAuth2CodeGrantFlow flow;
    @GET
    //@Produces(POST) 
    @Path("/")
    public Response test(){
        ClientIdentifier client= new ClientIdentifier("4639315", "SFM9Ke2QFDQ0LJGDqo0t");
        OAuth2CodeGrantFlow.Builder builder = OAuth2ClientSupport.authorizationCodeGrantFlowBuilder(client,
                "https://oauth.vk.com/authorize",
                "https://oauth.vk.com/access_token");
        builder.redirectUri(uri.getAbsolutePath().toString()+"/auto2");
//        builder.redirectUri("http://localhost:8080/service/auto/auto2");
        //builder.property(OAuth2CodeGrantFlow.Phase.ALL, "v","5.27" );
        flow = builder.property(OAuth2CodeGrantFlow.Phase.AUTHORIZATION, "readOnly", "true")
        .build();
        String authorizationUri = flow.start();
        return Response.temporaryRedirect(URI.create(authorizationUri)).build();
        //return Response.seeOther(builder.redirectUri(authorizationUri)).build();
        //final TokenResult result;
        //result = flow.finish("test","test");
        //System.out.println("Access Token: " + result.get);
    }
    @Path("/auto2")
    @GET
    public void test2(@QueryParam("code") String code,@QueryParam("state") String state){
        final TokenResult result = flow.finish(code, state);
        System.out.println("Access Token: " + result.getAccessToken());
    }
}
