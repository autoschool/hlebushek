package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Posts;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("GetPosts")
@Produces(MediaType.APPLICATION_JSON)
public class GetPosts {

    private JsonArray array = new JsonArray();

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
        if (postId == 0 && authorId == 0) {
            LazyList<Posts> posts = Posts.findAll();
            array = setJsonArrayPosts(posts);
        } else if (postId != 0) {
            try {
                Posts post = Posts.findById(postId);
                array = setJsonArrayPosts(post);
            } catch (NullPointerException e) {
                System.out.println(String.format("post_id = '%s' not found", postId));
            }
        } else {
            try {
                LazyList<Posts> posts = Posts.where(String.format("author_id = '%s'", authorId));
                array = setJsonArrayPosts(posts);
            } catch (DBException e) {
                System.out.println(e.getMessage());
            }
        }
        return array.toString();
    }

    private JsonArray setJsonArrayPosts(LazyList<Posts> posts) {
        for (Posts post : posts) {
            array.add(setJsonObjectPost(post));
        }
        return array;
    }

    private JsonArray setJsonArrayPosts(Posts post) {
        array.add(setJsonObjectPost(post));
        return array;
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
