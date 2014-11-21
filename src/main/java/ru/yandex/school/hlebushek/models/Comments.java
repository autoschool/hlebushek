package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;

public class Comments extends Model {

    final static private String COMMENT_ID = "comment_id";
    final static private String MESSAGE = "message";
    final static private String POST_ID = "post_id";
    final static private String AUTHOR_ID = "author";
    final static private String CREATE_DATE = "create_date";
    final static private String MODIFIED_DATE = "modified_date";
    final static private String IS_DELETED = "is_deleted";

    public int getCommentId() {
        return getInteger(COMMENT_ID);
    }

    public String getMessage() {
        return getString(MESSAGE);
    }

    public int getPostId() {
        return getInteger(POST_ID);
    }

    public int getAuthorId() {
        return getInteger(AUTHOR_ID);
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


}
