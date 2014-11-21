package ru.yandex.school.hlebushek.api;

import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Posts;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("GetPosts")
public class GetPosts {

    @GET
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
}
