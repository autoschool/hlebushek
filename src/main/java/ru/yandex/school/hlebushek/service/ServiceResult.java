package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.*;

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
        jsonObject.addProperty("create_date", user.getCreateDate());
        jsonObject.addProperty("modified_date", user.getModifiedDate());
        jsonObject.addProperty("is_deleted", user.getIsDeleted());
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
        jsonObject.addProperty("message", post.getMessage());
        jsonObject.addProperty("author_id", post.getAuthorId());
        jsonObject.addProperty("create_date", post.getCreateDate());
        jsonObject.addProperty("modified_date", post.getModifiedDate());
        jsonObject.addProperty("is_deleted", post.getIsDeleted());
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
        jsonObject.addProperty("post_id", comment.getPostId());
        jsonObject.addProperty("message", comment.getMessage());
        jsonObject.addProperty("author_id", comment.getAuthorId());
        jsonObject.addProperty("create_date", comment.getCreateDate());
        jsonObject.addProperty("modified_date", comment.getModifiedDate());
        jsonObject.addProperty("is_deleted", comment.getIsDeleted());
        return jsonObject;
    }
}
