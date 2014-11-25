package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Comments;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("GetComments")
@Produces(MediaType.APPLICATION_JSON)
public class GetComments {

    private JsonArray array = new JsonArray();

    /**
     * Method return json response comments model
     * @param postId int { /GetComments?postId=num }
     * @param authorId int { /GetComments?author-id=num }
     * @return JsonArray by String
     */
    @GET
    public String getComments(
            @QueryParam("postId") int postId,
            @QueryParam("authorId") int authorId) {
        LazyList<Comments> comments;
        if (postId != 0 && authorId == 0) {
            comments = Comments.where(String.format("post_id = '%s'", postId));
            array = setJsonArrayComments(comments);
        }
        if (postId == 0 && authorId != 0) {
            comments = Comments.where(String.format("author_id = '%s'", authorId));
            array = setJsonArrayComments(comments);
        }
        return array.toString();
    }

    private JsonArray setJsonArrayComments(LazyList<Comments> comments) {
        for (Comments comment : comments) {
            array.add(setJsonObjectComment(comment));
        }
        return array;
    }

    private JsonObject setJsonObjectComment(Comments comment) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("comment_id", comment.getCommentId());
        jsonObject.addProperty("post_id", comment.getPostId());
        jsonObject.addProperty("message", comment.getMessage());
        jsonObject.addProperty("author", comment.getAuthorId());
        jsonObject.addProperty("create_date", comment.getCreateDate());
        jsonObject.addProperty("modified_date", comment.getModifiedDate());
        jsonObject.addProperty("is_deleted", comment.getIsDeleted());
        return jsonObject;
    }
}
