package DB;

import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Comments;
import ru.yandex.school.hlebushek.models.Posts;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import ru.yandex.school.hlebushek.models.Users;
/**
 *
 * @author Drak_kin
 */
@Path("/")
public class JoinTest { 
    @Path("test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test(){
        Users user = Users.findById(2);
        LazyList<Posts> posts = user.getAll(Posts.class);
        JsonObject jsonUser = new JsonObject();
        JsonArray array = new JsonArray();
        jsonUser.addProperty("user_id", user.getUserId());
        jsonUser.addProperty("login", user.getLogin());
        array.add(jsonUser);
        for (Posts post : posts) {
            JsonObject jsonPost = new JsonObject();
            jsonPost.addProperty("title", post.getTitle());
            jsonPost.addProperty("message", post.getMessage());
            jsonPost.addProperty("author_id", post.getAuthorId());
            jsonPost.addProperty("create_date", post.getCreateDate());
            jsonPost.addProperty("modified_date", post.getModifiedDate());
            jsonPost.addProperty("is_deleted", post.getIsDeleted());
            array.add(jsonPost);
            JsonArray Comments = new JsonArray();
            LazyList<Comments> comments = post.getAll(Comments.class);
            for (Comments comment : comments) {
                JsonObject jsonComment = new JsonObject();
                jsonComment.addProperty("comment_id", comment.getCommentId());
                jsonComment.addProperty("post_id", comment.getPostId());
                jsonComment.addProperty("message", comment.getMessage());
                jsonComment.addProperty("author", comment.getAuthorId());
                jsonComment.addProperty("create_date", comment.getCreateDate());
                jsonComment.addProperty("modified_date", comment.getModifiedDate());
                jsonComment.addProperty("is_deleted", comment.getIsDeleted());
                Comments.add(jsonComment);
            }
            array.add(Comments);
        }
        return array.toString();
    }
    @Path("test2")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String test2(){
        Users user = Users.findById(3);
        LazyList<Posts> posts = user.getAll(Posts.class);
        JsonObject jsonUser = new JsonObject();
        JsonArray array = new JsonArray();
        jsonUser.addProperty("user_id", user.getUserId());
        jsonUser.addProperty("login", user.getLogin());
        array.add(jsonUser);
        for (Posts post : posts) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("title", post.getTitle());
            jsonObject.addProperty("message", post.getMessage());
            jsonObject.addProperty("author_id", post.getAuthorId());
            jsonObject.addProperty("create_date", post.getCreateDate());
            jsonObject.addProperty("modified_date", post.getModifiedDate());
            jsonObject.addProperty("is_deleted", post.getIsDeleted());
            array.add(jsonObject);
        }
        return array.toString();
    }
}
