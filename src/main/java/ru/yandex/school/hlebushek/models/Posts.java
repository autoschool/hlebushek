package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.*;

@Table("Posts")
@IdName("post_id")
@BelongsTo(parent = Users.class, foreignKeyName = "author_id")
public class Posts extends Model {

    private static final String POST_ID = "post_id";
    private static final String TITLE = "title";
    private static final String POST_MESSAGE = "message";
    private static final String POST_AUTHOR_ID = "author_id";
    private static final String POST_CREATE_DATE = "create_date";
    private static final String POST_MODIFIED_DATE = "modified_date";
    private static final String POST_IS_DELETED = "is_deleted";

    public int getPostId() {
        return getInteger(POST_ID);
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        setString(TITLE, title);
    }

    public String getPostMessage() {
        return getString(POST_MESSAGE);
    }

    public void setPostMessage(String message) {
        setString(POST_MESSAGE, message);
    }

    public int getPostAuthorId() {
        return getInteger(POST_AUTHOR_ID);
    }

    public void setPostAuthorId(int authorId) {
        setInteger(POST_AUTHOR_ID, authorId);
    }

    public String getPostCreateDate() {
        if (getDate(POST_CREATE_DATE) != null) {
            return getDate(POST_CREATE_DATE).toString();
        } else {
            return null;
        }
    }

    public void setPostCreateDate(String date) {
        if (date != null && !date.isEmpty()) {
            setString(POST_CREATE_DATE, date);
        }
    }

    public String getPostModifiedDate() {
        if (getDate(POST_MODIFIED_DATE) != null) {
            return getDate(POST_MODIFIED_DATE).toString();
        } else {
            return null;
        }
    }

    public boolean getPostIsDeleted() {
        return getBoolean(POST_IS_DELETED);
    }
}
