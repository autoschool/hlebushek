package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
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
            json = new UsersData().getUser(userId, login);
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
            json = new PostsData().getPosts(postId, authorId);
        }catch (ServiceGateException e) {
            exception = e;
        }
        return create(json, exception).toString();
    }

    /**
     * Method return json response new post model
     * @param authorId int author id
     * @param title String post title
     * @param message String post body message
     * @return JsonObject by String
     */
    @PUT
    @Path("posts")
    public String setPost(
            @QueryParam("author_id") int authorId,
            @QueryParam("title") String title,
            @QueryParam("message") String message) {
        ServiceGateException exception = null;
        try {
            new PostsData().setPost(authorId, title, message);
            // todo: need return new post
        }catch (ServiceGateException e) {
            exception = e;
        }
        return create(null, exception).toString();
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
            json = new CommentsData().getComments(postId, authorId);
        } catch (ServiceGateException e) {
            exception = e;
        }
        return ServiceResult.create(json, exception).toString();
    }

    /**
     * Method return json response new comment model
     * @param postId int post id
     * @param authorId int author is
     * @param message String comment body
     * @return JsonObject by String
     */
    @PUT
    @Path("comments")
    public String setComment(
            @QueryParam("post_id") int postId,
            @QueryParam("author_id") int authorId,
            @QueryParam("message") String message) {
        ServiceGateException exception = null;
        try {
            new CommentsData().setComment(postId, authorId, message);
            // todo: need return new comment
        }catch (ServiceGateException e) {
            exception = e;
        }
        return create(null, exception).toString();
    }
}
