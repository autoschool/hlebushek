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
    private static final String MESSAGE = "message";
    private static final String POST_ID = "post_id";
    private static final String AUTHOR_ID = "author_id";
    private static final String CREATE_DATE = "create_date";
    private static final String MODIFIED_DATE = "modified_date";
    private static final String IS_DELETED = "is_deleted";

    public int getCommentId() {
        return getInteger(COMMENT_ID);
    }

    public String getMessage() {
        return getString(MESSAGE);
    }

    public void setMessage(String message) {
        setString(MESSAGE, message);
    }

    public int getPostId() {
        return getInteger(POST_ID);
    }

    public void setPostId(int postId) {
        setInteger(POST_ID, postId);
    }

    public int getAuthorId() {
        return getInteger(AUTHOR_ID);
    }

    public void setAuthorId(int authorId) {
        setInteger(AUTHOR_ID, authorId);
    }

    public String getCreateDate() {
        if (getDate(CREATE_DATE) != null) {
            return getDate(CREATE_DATE).toString();
        } else {
            return null;
        }
    }

    public String getModifiedDate() {
        if (getDate(MODIFIED_DATE) != null) {
            return getDate(MODIFIED_DATE).toString();
        } else {
            return null;
        }
    }

    public int getIsDeleted() {
        return getInteger(IS_DELETED);
    }

    public void setIsDeleted(int isDeleted) {
        setInteger(IS_DELETED, isDeleted);
    }
}
