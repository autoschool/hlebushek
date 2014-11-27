package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.*;

@Table("Users")
@IdName("user_id")
public class Users extends Model {

    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String USER_CREATE_DATE = "create_date";
    private static final String USER_MODIFIED_DATE = "modified_date";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String USER_IS_DELETED = "is_deleted";

    public int getUserId() {
        return getInteger(USER_ID);
    }

    public String getLogin() {
        return getString(LOGIN);
    }
    
        public String getUserCreateDate() {
        if (getDate(USER_CREATE_DATE) != null) {
            return getDate(USER_CREATE_DATE).toString();
        } else {
            return null;
        }
    }

    public String getUserModifiedDate() {
        if (getDate(USER_MODIFIED_DATE) != null) {
            return getDate(USER_MODIFIED_DATE).toString();
        } else {
            return null;
        }
    }
    
    public void setLogin(String login) {
        setString(LOGIN, login);
    }

    public void setPassword(String password) {
        setString(USER_PASSWORD, password);
    }
    
    public String getPassword() {
        return getString(USER_PASSWORD);
    }
    
    public String getCreateDate() {
        if (getDate(USER_CREATE_DATE) != null) {
            return getDate(USER_CREATE_DATE).toString();
        } else {
            return null;
        }
    }

    public String getModifiedDate() {
        if (getDate(USER_MODIFIED_DATE) != null) {
            return getDate(USER_MODIFIED_DATE).toString();
        } else {
            return null;
        }
    }
    
    public String getFirstName() {
        return getString(FIRST_NAME);
    }

    public void setFirstName(String firstName) {
        setString(FIRST_NAME, firstName);
    }
    
    public String getLastName() {
        return getString(LAST_NAME);
    }

    public void setLastName(String lastName) {
        setString(LAST_NAME, lastName);
    }

    public Boolean getUserIsDeleted() {
        return getBoolean(USER_IS_DELETED);
    }

    public void setUserIsDeleted(Boolean isDeleted) {
        setBoolean(USER_IS_DELETED, isDeleted);
    }
    public Integer getVkId() {
        return getInteger("vk_id");
    }

    public void setVkId(Integer vkId) {
        setInteger("vk_id",vkId);
    }
    
    public String getToken() {
        return getString("vk_token");
    }

    public void setToken(String token) {
        setString("vk_token", token);
    }
    
    public String getSmallImage() {
        return getString("photo_50");
    }

    public void setSmallImage(Byte[] image) {
        set("photo_50",image);
    }
}
