package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Comments;

class GetComments extends ServiceResult {

    /**
     * Method return json comments model
     * @param postId int
     * @param authorId int
     * @return JsonElement
     */
    public JsonElement getComments( int postId, int authorId) throws ServiceGateException {
        JsonElement json = null;
        try {
            LazyList<Comments> comments;
            if (postId != 0 && authorId == 0) {
                comments = Comments.where(String.format("post_id = '%s'", postId));
                json = setJsonArray(comments);
            }
            if (postId == 0 && authorId != 0) {
                comments = Comments.where(String.format("author_id = '%s'", authorId));
                json = setJsonArray(comments);
            }
        } catch (DBException e) {
            throw new ServiceGateException(e.getMessage());
        }
        return json;
    }

    private JsonArray setJsonArray(LazyList<Comments> comments) {
        JsonArray jsonArray = new JsonArray();
        for (Comments comment : comments) {
            jsonArray.add(setJsonObject(comment));
        }
        return jsonArray;
    }
}
