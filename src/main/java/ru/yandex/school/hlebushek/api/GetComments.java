package ru.yandex.school.hlebushek.api;

import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Comments;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/")
public class GetComments {

    @GET
    @Path("comments")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserComments(@QueryParam("author-id") int authorId) {
        LazyList<Comments> list = Comments.where(String.format("author = '%s'", authorId));
        return list.toJson(true);
    }

    @GET
    @Path("comments")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPostComments(@QueryParam("post-id") int postId) {
        LazyList<Comments> list = Comments.where(String.format("post_id = '%s'", postId));
        return list.toJson(true);
    }
}
