package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Comments;

class CommentsData extends ServiceResult {

    /**
     * Method return json comments model
     * @param postId int
     * @param authorId int
     * @return JsonElement
     */
    protected JsonElement getComments(int postId, int authorId) throws ServiceGateException {
        JsonElement json = null;
        try {
            LazyList<Comments> comments;
            if (postId != 0 && authorId == 0) {
                comments = Comments.where(String.format("post_id = '%s'", postId)).orderBy("create_date ASC");
                json = setJsonArray(comments);
            }
            if (postId == 0 && authorId != 0) {
                comments = Comments.where(String.format("author_id = '%s'", authorId)).orderBy("create_date ASC");
                json = setJsonArray(comments);
            }
        } catch (DBException e) {
            throw new ServiceGateException(e.getMessage());
        }
        return json;
    }

    /**
     * Method save comment to DataBase
     * @param postId int post id
     * @param authorId int author is
     * @param message String comment body
     */
    protected void setComment(int postId, int authorId, String message) throws ServiceGateException {
        try {
            Base.openTransaction();
            Comments comment = new Comments();
            comment.setCommentPostId(postId);
            comment.setCommentAuthorId(authorId);
            comment.setCommentMessage(message);
            comment.setCommentCreateDate(getCurrentDate());
            comment.saveIt();
            Base.commitTransaction();
        } catch (DBException e) {
            Base.rollbackTransaction();
            throw new ServiceGateException(e.getMessage());
        }
    }

    private JsonArray setJsonArray(LazyList<Comments> comments) {
        JsonArray jsonArray = new JsonArray();
        for (Comments comment : comments) {
            jsonArray.add(setJsonObject(comment));
        }
        return jsonArray;
    }
}
