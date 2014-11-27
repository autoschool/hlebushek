package ru.yandex.school.hlebushek.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Posts;
import ru.yandex.school.hlebushek.models.Users;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @GET
    @Path("test")
    public String setJsonPostsFromMaps() throws JsonProcessingException {
        LazyList<Posts> posts = Posts.findAll().orderBy("create_date desc");
        posts.include(Users.class);
        List<Map> maps = posts.toMaps();
        for (Map map : maps) {
            Map user = (Map) map.get("users");
            user.remove("password");
        }
        return new ObjectMapper().writeValueAsString(responseMap(maps, null));
    }

    private Map<String, Object> responseMap(List<Map> dataMap, Exception e) {
        Map<String, Object> response = new HashMap<>();
        boolean isError = false;
        String errorMessage = "";
        if (e != null) {
            isError = true;
            errorMessage = e.getMessage();
        }
        response.put("data", dataMap);
        response.put("is_error", isError);
        response.put("error", errorMessage);
        return response;
    }
}
