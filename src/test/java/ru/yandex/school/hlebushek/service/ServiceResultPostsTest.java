package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.school.hlebushek.db.DatabaseProvider;
import ru.yandex.school.hlebushek.models.Posts;
import static com.sudhir.json.matchers.JsonMatchers.*;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static org.mockito.Mockito.mock;

/**
 * Created by ksenie on 25.01.15.
 */
public class ServiceResultPostsTest {
    private PostsData posts;
    private Posts post = new Posts();
    private DatabaseProvider dbMock = mock(DatabaseProvider.class);

    private String title = "test atitle";
    private String message = "test post body message";
    private int authorId = 2;

    @Before
    public void createNewPost(){
        posts = new PostsData();
        dbMock.openConnection();
        post.setPostAuthorId(authorId);
        post.setTitle(title);
        post.setPostMessage(message);
        post.saveIt();
    }
    @Test
    public void ReturnedJsonShouldContainAllKeys() throws JSONException {
        JsonObject jsonPost = posts.setJsonObject(post).getAsJsonObject();
        JSONObject json = new JSONObject(jsonPost.toString());
        assertThat(json, allOf(hasJsonKey("post_id"),
                               hasJsonKey("title"),
                               hasJsonKey("message"),
                               hasJsonKey("author_id"),
                               hasJsonKey("create_date"),
                               hasJsonKey("modified_date"),
                               hasJsonKey("is_deleted"),
                               hasJsonKey("first_name"),
                               hasJsonKey("last_name")));
    }

    @Test
    public void ReturnedJsonShouldContainValues() throws JSONException {
        JsonObject jsonPost = posts.setJsonObject(post).getAsJsonObject();
        assertThat(jsonPost.get("message").getAsString(), equalTo(message));
        assertThat(jsonPost.get("author_id").getAsInt(), equalTo(authorId));
        assertThat(jsonPost.get("title").getAsString(), equalTo(title));
    }
}
