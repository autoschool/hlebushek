package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
     * @param postId int { /GetPosts?post-id=num }
     * @param authorId int { /GetPosts?author-id=num }
     * @return JsonArray by String
     */
    @GET
    public String getPosts(
            @QueryParam("post-id") int postId,
            @QueryParam("author-id") int authorId) {
        if (postId != 0 && authorId == 0) {
            try {
                Posts post = Posts.findById(postId);
                array = setJsonArrayPosts(post);
            } catch (NullPointerException e) {
                System.out.println(String.format("Post_id = '%s' not found", postId));
            }
        }
        if (postId == 0 && authorId != 0) {
            LazyList<Posts> posts = Posts.where(String.format("author_id = '%s'", authorId));
            array = setJsonArrayPosts(posts);
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
