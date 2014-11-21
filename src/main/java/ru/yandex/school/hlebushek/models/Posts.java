package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;

import java.sql.Date;

public class Posts extends Model {

    final static private String TITLE = "title";
    final static private String MESSAGE = "message";
    final static private String AUTHOR_ID = "author_id";
    final static private String CREATE_DATE = "create_date";
    final static private String MODIFIED_DATE = "modified_date";
    final static private String IS_DELETED = "is_deleted";

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

    public Date getCreateDate() {
        return getDate(CREATE_DATE);
    }

    public Date getLastUpdateDate() {
        return getDate(MODIFIED_DATE);
    }

    public boolean getIsDeleted() {
        return getBoolean(IS_DELETED);
    }

    public void setIsDeleted(boolean isDeleted) {
        setBoolean(IS_DELETED,isDeleted);
    }
}
