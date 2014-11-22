package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("Comments")
@IdName("comment_id")
public class Comments extends Model {

    final static private String COMMENT_ID = "comment_id";
    final static private String MESSAGE = "message";
    final static private String POST_ID = "post_id";
    final static private String AUTHOR_ID = "author_id";
    final static private String CREATE_DATE = "create_date";
    final static private String MODIFIED_DATE = "modified_date";
    final static private String IS_DELETED = "is_deleted";

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
