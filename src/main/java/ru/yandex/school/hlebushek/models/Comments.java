package ru.yandex.school.hlebushek.models;

import java.sql.Date;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("Comments")
@IdName("comment_id")
public class Comments extends Model {
    
    public void setMessage(String message) {
        setString("message", message); 
    }
    public String getMessage() {
        return getString("message");
    }
    
    public void setAuthorId(Integer id) {
        setInteger("author_id", id);
    }
    public Integer getAuthorId() {
        return getInteger("author_id");
    }
    public void setPostId(Integer id) {
        setInteger("post_id", id);
    }
    public Integer getPostId() {
        return getInteger("post_id");
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
