package ru.yandex.school.hlebushek.api;

import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Comments;
import ru.yandex.school.hlebushek.models.Posts;
import ru.yandex.school.hlebushek.models.Users;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/data/")
public class GetData {

    @GET
    @Path("comments")
    @Produces(MediaType.APPLICATION_JSON)
    public String getComments(@QueryParam("author-id") int authorId, @QueryParam("post-id") int postId) {
        if (authorId != 0) {
            LazyList<Comments> list = Comments.where(String.format("author = '%s'", authorId));
            return list.toJson(true);
        } else if (postId != 0) {
            LazyList<Comments> list = Comments.where(String.format("post_id = '%s'", postId));
            return list.toJson(true);
        }
        return "error query parameters";
    }

    @GET
    @Path("posts")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPosts(@QueryParam("author-id") int userId, @QueryParam("post-id") int postId) {
        if (userId != 0) {
            LazyList<Posts> list = Posts.where(String.format("author_id = '%s'", userId));
            return list.toJson(true);
        } else if (postId != 0) {
            LazyList<Posts> list = Posts.where(String.format("post_id = '%s'", postId));
            return list.toJson(true);
        }
        return Posts.findAll().toJson(true);
    }

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@QueryParam("user-id") int userId, @QueryParam("login") String login) {
        try {
            if (userId != 0) {
                return Users.findBySQL(String.format("select * from users where user_id = '%s'", userId)).toJson(true);
            } else if (login != null && !login.isEmpty()) {
                return Users.findBySQL(String.format("select * from users where login = '%s'", login)).toJson(true);
            }
        } catch (DBException e) {
            return e.getMessage();
        }
        return "error query parameters";
    }
}
