package ru.yandex.school.hlebushek.api;

import ru.yandex.school.hlebushek.models.PostsApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/api/")
public class SomeData {

    @GET
    @Path("post")
    @Produces(MediaType.APPLICATION_JSON)
    public PostsApi getPost(@QueryParam("id") int id) {
        PostsApi post = new PostsApi();
        post.setPostId(id);
        post.setPostTitle("Test post");
        post.setPostBody("Anyone who reads Old and Middle English literary texts will be familiar with the mid-brown volumes of the EETS, with the symbol of Alfred's jewel embossed on the front cover. Most of the works attributed to King Alfred or to Aelfric, along with some of those by bishop Wulfstan and much anonymous prose and verse from the pre-Conquest period, are to be found within the Society's three series; all of the surviving medieval drama, most of the Middle English romances, much religious and secular prose and verse including the English works of John Gower, Thomas Hoccleve and most of Caxton's prints all find their place in the publications. Without EETS editions, study of medieval English texts would hardly be possible.");
        post.setPostCreateData(new Date().toString());
        return post;
    }
}
