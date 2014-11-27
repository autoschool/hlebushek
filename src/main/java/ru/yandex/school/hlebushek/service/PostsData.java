package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import org.javalite.common.ConversionException;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Posts;

class PostsData extends ServiceResult {

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
                json = setJsonArray(posts);
            } else if (postId != 0) {
                Posts post = Posts.findById(postId);
                json = setJsonObject(post);
            } else {
                LazyList<Posts> posts = Posts.where(String.format("author_id = '%s'", authorId));
                json = setJsonArray(posts);
            }
        } catch (DBException e) {
            throw new ServiceGateException(e.getMessage());
        }
        return json;
    }

    /**
     * Method save post to DataBase
     * @param authorId int author id
     * @param title String post title
     * @param message String post body message
     * @throws ServiceGateException
     */
    public void setPost(int authorId, String title, String message) throws ServiceGateException {
        try {
            Posts post = new Posts();
            post.setPostAuthorId(authorId);
            post.setTitle(title);
            post.setPostMessage(message);
            post.setPostCreateDate(getCurrentDate());
            post.saveIt();
        } catch (DBException | ConversionException e) {
            throw new ServiceGateException(e.getMessage());
        }
    }

    private JsonArray setJsonArray(LazyList<Posts> posts) {
        JsonArray jsonArray = new JsonArray();
        for (Posts post : posts) {
            jsonArray.add(setJsonObject(post));
        }
        return jsonArray;
    }
}
