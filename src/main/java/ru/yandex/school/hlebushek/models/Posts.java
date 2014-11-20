package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;

import java.sql.Date;

public class Posts extends Model {

    public void setTitle(String title) {
        setString("title", title);
    }
    public String getTitle() {
        return getString("title");
    }

    public void setMessage(String message) {
        setString("message", message);
    }
    public String getMessage() {
        return getString("message");
    }

    public void setAuthorId(int id) {
        setInteger("author_id", id);
    }

    public int getAuthorId() {
        return getInteger("author_id");
    }

    public Date getCreateDate() {
        return getDate("create_date");
    }

    public Date getLastUpdateDate() {
        return getDate("modified_date");
    }

    public boolean getIsDeleted() {
        return getBoolean("is_deleted");
    }

    public void setIsDeleted(boolean isDeleted) {
        setBoolean("is_deleted",isDeleted);
    }
}
