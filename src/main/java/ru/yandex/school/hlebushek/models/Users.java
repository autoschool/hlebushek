package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

import java.sql.Date;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("Users")
@IdName("user_id")
public class Users extends Model {

    final static private String USER_ID = "user_id";
    final static private String LOGIN = "login";
    final static private String PASSWORD = "password";
    final static private String CREATE_DATE = "create_date";
    final static private String MODIFIED_DATE = "modified_date";
    final static private String FIRST_NAME = "first_name";
    final static private String LAST_NAME = "last_name";
    final static private String IS_DELETED = "is_deleted";

    public int getUserId() {
        return getInteger(USER_ID);
    }

    public String getLogin() {
        return getString(LOGIN);
    }

    public void setLogin(String login) {
        setString(LOGIN, login);
    }

    public void setPassword(String password) {
        setString(PASSWORD, password);
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

    public int getIsDeleted() {
        return getInteger(IS_DELETED);
    }

    public void setIsDeleted(int isDeleted) {
        setInteger(IS_DELETED, isDeleted);
    }
}
