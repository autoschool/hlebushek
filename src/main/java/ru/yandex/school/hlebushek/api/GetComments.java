package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Comments;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("GetComments")
public class GetComments {

    private JsonArray array = new JsonArray();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCommentsByPost(
            @QueryParam("post-id") int postId,
            @QueryParam("author-id") int authorId) {
        LazyList<Comments> comments;
        if (postId != 0 && authorId == 0) {
            comments = Comments.where(String.format("post_id = '%s'", postId));
            array = setCommentsRequest(comments);
        }
        if (postId == 0 && authorId != 0) {
            comments = Comments.where(String.format("author = '%s'", authorId));
            array = setCommentsRequest(comments);
        }
        return array.toString();
    }

    /**
     * Method create request array
     * @param comments Comments
     * @return JsonArray
     */
    private JsonArray setCommentsRequest(LazyList<Comments> comments) {
        for (Comments comment : comments) {
            JsonObject object = new JsonObject();
            object.addProperty("comment_id", comment.getCommentId());
            object.addProperty("post_id", comment.getPostId());
            object.addProperty("message", comment.getMessage());
            object.addProperty("author", comment.getAuthorId());
            object.addProperty("create_date", comment.getCreateDate());
            object.addProperty("modified_date", comment.getModifiedDate());
            object.addProperty("is_deleted", comment.getIsDeleted());
            array.add(object);
        }
        return array;
    }
}
