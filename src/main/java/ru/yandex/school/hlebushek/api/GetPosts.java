package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Posts;

class GetPosts {

    /**
     * Method return json posts model
     * @param postId int
     * @param authorId int
     * @return JsonElement
     */
    public JsonElement getPosts(int postId, int authorId) throws ServiceGateException {
        JsonElement json;
        try {
            if (postId == 0 && authorId == 0) {
                LazyList<Posts> posts = Posts.findAll();
                json = setJsonArrayPosts(posts);
            } else if (postId != 0) {
                Posts post = Posts.findById(postId);
                json = setJsonObjectPost(post);
            } else {
                LazyList<Posts> posts = Posts.where(String.format("author_id = '%s'", authorId));
                json = setJsonArrayPosts(posts);
            }
        } catch (DBException e) {
            throw new ServiceGateException(e.getMessage());
        }
        return json;
    }

    private JsonArray setJsonArrayPosts(LazyList<Posts> posts) {
        JsonArray jsonArray = new JsonArray();
        for (Posts post : posts) {
            jsonArray.add(setJsonObjectPost(post));
        }
        return jsonArray;
    }

    private JsonObject setJsonObjectPost(Posts post) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("post_id", post.getPostId());
        jsonObject.addProperty("title", post.getTitle());
        jsonObject.addProperty("message", post.getMessage());
        jsonObject.addProperty("author_id", post.getAuthorId());
        jsonObject.addProperty("create_date", post.getCreateDate());
        jsonObject.addProperty("modified_date", post.getModifiedDate());
        jsonObject.addProperty("is_deleted", post.getIsDeleted());
        return jsonObject;
    }
}
