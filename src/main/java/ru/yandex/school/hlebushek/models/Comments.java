package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.*;

@Table("Comments")
@IdName("comment_id")
@BelongsToParents({
    @BelongsTo(parent = Posts.class, foreignKeyName = "post_id"),
    @BelongsTo(parent = Users.class, foreignKeyName = "author_id")
})
public class Comments extends Model {

    private static final String COMMENT_ID = "comment_id";
    private static final String COMMENT_MESSAGE = "message";
    private static final String COMMENT_POST_ID = "post_id";
    private static final String COMMENT_AUTHOR_ID = "author_id";
    private static final String COMMENT_CREATE_DATE = "create_date";
    private static final String COMMENT_MODIFIED_DATE = "modified_date";
    private static final String COMMENT_IS_DELETED = "is_deleted";

    public int getCommentId() {
        return getInteger(COMMENT_ID);
    }

    public String getCommentMessage() {
        return getString(COMMENT_MESSAGE);
    }

    public void setCommentMessage(String message) {
        setString(COMMENT_MESSAGE, message);
    }

    public int getCommentPostId() {
        return getInteger(COMMENT_POST_ID);
    }

    public void setCommentPostId(int postId) {
        setInteger(COMMENT_POST_ID, postId);
    }

    public int getCommentAuthorId() {
        return getInteger(COMMENT_AUTHOR_ID);
    }

    public void setCommentAuthorId(int authorId) {
        setInteger(COMMENT_AUTHOR_ID, authorId);
    }

    public String getCommentCreateDate() {
        if (getDate(COMMENT_CREATE_DATE) != null) {
            return getDate(COMMENT_CREATE_DATE).toString();
        } else {
            return null;
        }
    }
    public void setCommentCreateDate(String date) {
        if (date != null && !date.isEmpty()) {
            setString(COMMENT_CREATE_DATE, date);
        }
    }
    
    public String getCommentModifiedDate() {
        if (getDate(COMMENT_MODIFIED_DATE) != null) {
            return getDate(COMMENT_MODIFIED_DATE).toString();
        } else {
            return null;
        }
    }

    public boolean getCommentIsDeleted() {
        return getBoolean(COMMENT_IS_DELETED);
    }

    public void setCommentIsDeleted(Boolean isDeleted) {
        setBoolean(COMMENT_IS_DELETED, isDeleted);
    }
    
}
