package ru.yandex.school.hlebushek.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.school.hlebushek.db.DatabaseProvider;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Posts;
import ru.yandex.school.hlebushek.rules.DbConnectionRule;

import javax.ws.rs.core.Application;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;


/**
 * Created by ksenie on 23.01.15.
 */
public class PostDataTest extends JerseyTest {

    private PostsData posts;
    private int postId;
    private int authorId;

    @Rule
    public DbConnectionRule baseConnection = new DbConnectionRule();

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig;
        resourceConfig = new ResourceConfig(PostsData.class);
        resourceConfig.register(DatabaseProvider.class);
        return resourceConfig;
    }

    @Before
    public void prepare(){
        posts = new PostsData();
    }

    @Test
    public void shouldReturnAllPosts() throws ServiceGateException{
        postId = 0;
        authorId = 0;
        int expectedPostCount = Posts.findAll().size();
        int actualPostCount = posts.getPosts(postId, authorId).getAsJsonArray().size();
        assertThat(expectedPostCount, equalTo(actualPostCount));
    }

    @Test
    public void shouldReturnPostById() throws ServiceGateException, IOException {
        postId = 1;
        authorId = 3;
        Posts expectedPost = Posts.findById(postId);
        int actualPostID = posts.getPosts(postId, authorId).getAsJsonObject().get("post_id").getAsInt();
        assertThat(expectedPost.getPostId(), is(actualPostID));
    }

    @Test
    public void shouldSavePost() throws ServiceGateException{
        int authorId = 1;
        String title = "Test title";
        String postBody = "Post test body";
        posts.setPost(authorId, title, postBody);
        LazyList<Posts> post = Posts.where(String.format("title = '%s AND message = '%s", title + "'", postBody + "'"));
        assertThat(post, not(empty()));
    }

    @Test(expected = DBException.class)
    public void shouldNotSaveEmptyPost(){
        Posts p = new Posts();
        p.saveIt();
    }
}
