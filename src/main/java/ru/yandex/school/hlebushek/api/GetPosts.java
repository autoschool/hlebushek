package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Posts;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("GetPosts")
@Produces(MediaType.APPLICATION_JSON)
public class GetPosts {

    private Exception exception;
    private JsonElement json;

    /**
     * Method return json response posts model
     * without parameter method return all posts
     * @param postId int { /GetPosts?postId=num }
     * @param authorId int { /GetPosts?authorId=num }
     * @return JsonArray by String
     */
    @GET
    public String getPosts(
            @QueryParam("postId") int postId,
            @QueryParam("authorId") int authorId) {
        try {
            if (postId == 0 && authorId == 0) {
                LazyList<Posts> posts = Posts.findAll();
                json = setJsonArrayPosts(posts);
            } else if (postId != 0) {
                try {
                    Posts post = Posts.findById(postId);
                    json = setJsonObjectPost(post);
                } catch (NullPointerException e) {
                    System.out.println(String.format("post_id = '%s' not found", postId));
                }
            } else {
                LazyList<Posts> posts = Posts.where(String.format("author_id = '%s'", authorId));
                json = setJsonArrayPosts(posts);
            }
        } catch (DBException e) {
            this.exception = e;
        }
        return ApiAnswer.create(json, exception).toString();
    }

    private JsonArray setJsonArrayPosts(LazyList<Posts> posts) {
        JsonArray jsonArray = new JsonArray();
        for (Posts post : posts) {
            jsonArray.add(setJsonObjectPost(post));
        }
        return jsonArray;
    }

    private JsonObject setJsonObjectPost(Posts post) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("post_id", post.getPostId());
        jsonObject.addProperty("title", post.getTitle());
        jsonObject.addProperty("message", post.getMessage());
        jsonObject.addProperty("author_id", post.getAuthorId());
        jsonObject.addProperty("create_date", post.getCreateDate());
        jsonObject.addProperty("modified_date", post.getModifiedDate());
        jsonObject.addProperty("is_deleted", post.getIsDeleted());
        return jsonObject;
    }
}
