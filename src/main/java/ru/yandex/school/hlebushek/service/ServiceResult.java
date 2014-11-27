package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.*;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract class ServiceResult {

    public ServiceResult() {}

    /**
     * Method create common json object for response
     * @param data JsonElement
     * @param e ServiceGateException
     * @return JsonObject
     */
    public static JsonObject create(JsonElement data, ServiceGateException e) {
        JsonObject jsonResult = new JsonObject();
        jsonResult.add("data", data);
        if (e != null) {
            jsonResult.addProperty("is_error", true);
            jsonResult.addProperty("error", e.getMessage());
        } else {
            jsonResult.addProperty("is_error", false);
            jsonResult.addProperty("error", "");
        }
        return jsonResult;
    }

    /**
     * Method set json object for users model
     * @param user Users
     * @return JsonObject
     */
    protected JsonObject setJsonObject(Users user) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", user.getUserId());
        jsonObject.addProperty("login", user.getLogin());
        jsonObject.addProperty("first_name", user.getFirstName());
        jsonObject.addProperty("last_name", user.getLastName());
        jsonObject.addProperty("create_date", user.getUserCreateDate());
        jsonObject.addProperty("modified_date", user.getUserModifiedDate());
        jsonObject.addProperty("is_deleted", user.getUserIsDeleted());
        return jsonObject;
    }

    /**
     * Method set json object for posts model
     * @param post Posts
     * @return JsonObject
     */
    protected JsonObject setJsonObject(Posts post) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("post_id", post.getPostId());
        jsonObject.addProperty("title", post.getTitle());
        jsonObject.addProperty("message", post.getPostMessage());
        jsonObject.addProperty("author_id", post.getPostAuthorId());
        jsonObject.addProperty("create_date", post.getPostCreateDate());
        jsonObject.addProperty("modified_date", post.getPostModifiedDate());
        jsonObject.addProperty("is_deleted", post.getPostIsDeleted());
        jsonObject.addProperty("first_name", post.parent(Users.class).getFirstName());
        jsonObject.addProperty("last_name", post.parent(Users.class).getLastName());
        return jsonObject;
    }

    /**
     * Method set json object for comments model
     * @param comment Comments
     * @return JsonObject
     */
    protected JsonObject setJsonObject(Comments comment) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("comment_id", comment.getCommentId());
        jsonObject.addProperty("post_id", comment.getCommentPostId());
        jsonObject.addProperty("message", comment.getCommentMessage());
        jsonObject.addProperty("author_id", comment.getCommentAuthorId());
        jsonObject.addProperty("create_date", comment.getCommentCreateDate());
        jsonObject.addProperty("modified_date", comment.getCommentModifiedDate());
        jsonObject.addProperty("is_deleted", comment.getCommentIsDeleted());
        jsonObject.addProperty("first_name", comment.parent(Users.class).getFirstName());
        jsonObject.addProperty("last_name", comment.parent(Users.class).getLastName());
        return jsonObject;
    }

    /**
     * Method return current date time
     * @return String
     */
    protected String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date().getTime());
    }
}
