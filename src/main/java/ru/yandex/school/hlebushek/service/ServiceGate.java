package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ServiceGate extends ServiceResult {

    /**
     * Method return json response users model
     * @param userId int { /GetComments?userId=num }
     * @param login String { /GetComments?login=string }
     * @return JsonObject by String
     */
    @GET
    @Path("users")
    public String getUser(
            @QueryParam("user_id") int userId,
            @DefaultValue("") @QueryParam("login") String login) {
        ServiceGateException exception = null;
        JsonElement json = null;
        try {
            json = new GetUsers().getUser(userId, login);
        }catch (ServiceGateException e) {
            exception = e;
        }
        return create(json, exception).toString();
    }

    /**
     * Method return json response posts model
     * without parameter method return all posts
     * @param postId int { /GetPosts?postId=num }
     * @param authorId int { /GetPosts?authorId=num }
     * @return JsonObject by String
     */
    @GET
    @Path("posts")
    public String getPosts(
            @QueryParam("post_id") int postId,
            @QueryParam("author_id") int authorId) {
        ServiceGateException exception = null;
        JsonElement json = null;
        try {
            json = new GetPosts().getPosts(postId, authorId);
        }catch (ServiceGateException e) {
            exception = e;
        }
        return create(json, exception).toString();
    }

    /**
     * Method return json response comments model
     * @param postId int { /GetComments?postId=num }
     * @param authorId int { /GetComments?author-id=num }
     * @return JsonObject by String
     */
    @GET
    @Path("comments")
    public String getComments(
            @QueryParam("post_id") int postId,
            @QueryParam("author_id") int authorId) {
        ServiceGateException exception = null;
        JsonElement json = null;
        try {
            json = new GetComments().getComments(postId, authorId);
        } catch (ServiceGateException e) {
            exception = e;
        }
        return ServiceResult.create(json, exception).toString();
    }
}
