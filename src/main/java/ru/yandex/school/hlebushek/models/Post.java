package ru.yandex.school.hlebushek.models;

import java.sql.Date;
import org.javalite.activejdbc.Model;

public class Post extends Model{
    public void setTitle(String title) {
        setString("title", title);
    }
    public String getTitle() {
        return getString("title");
    }
    
    public void setMessage(String message) {
        setString("Message", message); //FIX change to small letter Message from Post.Message
    }
    public String getMessage() {
        return getString("Message");
    }
    
    public void setAuthorId(Integer id) {
        setInteger("author_id", id);
    }
    public Integer getAuthorId() {
        return getInteger("author_id");
    }
    public Date getCreateDate() {
        return getDate("create_date");
    }
    public Date getLastUpdateDate() {
        return getDate("modified_date");
    }
    public Boolean getIsDeleted(){
        return getBoolean("is_deleted");
    }
    public void setIsDeleted(Boolean isDeleted){
        setBoolean("is_deleted",isDeleted);
    }
    
}
