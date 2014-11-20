package ru.yandex.school.hlebushek.api;

import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Posts;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/")
public class GetPosts {

    @GET
    @Path("posts")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPosts() {
        return Posts.findAll().toJson(true);
    }

    @GET
    @Path("posts")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserPosts(@QueryParam("author-id") int userId) {
        LazyList<Posts> list = Posts.where(String.format("author_id = '%s'", userId));
        return list.toJson(true);
    }

    @GET
    @Path("posts")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPost(@QueryParam("post-id") int postId) {
        LazyList<Posts> list = Posts.where(String.format("post_id = '%s'", postId));
        return list.toJson(true);
    }
}
