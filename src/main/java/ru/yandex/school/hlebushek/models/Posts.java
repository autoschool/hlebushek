package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.*;

@Table("Posts")
@IdName("post_id")
public class Posts extends Model {

    private static final String POST_ID = "post_id";
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";
    private static final String AUTHOR_ID = "author_id";
    private static final String CREATE_DATE = "create_date";
    private static final String MODIFIED_DATE = "modified_date";
    private static final String IS_DELETED = "is_deleted";

    public int getPostId() {
        return getInteger(POST_ID);
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        setString(TITLE, title);
    }

    public String getMessage() {
        return getString(MESSAGE);
    }

    public void setMessage(String message) {
        setString(MESSAGE, message);
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

    public boolean getIsDeleted() {
        return getBoolean(IS_DELETED);
    }

    public void setIsDeleted(boolean isDeleted) {
        setBoolean(IS_DELETED, isDeleted);
    }
}
