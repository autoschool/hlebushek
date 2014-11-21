package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import java.sql.Date;

public class Users extends Model {

    final static private String FIRST_NAME = "first_name";
    final static private String LAST_NAME = "last_name";
    final static private String LOGIN = "login";
    final static private String PASSWORD = "password";
    final static private String CREATE_DATE = "create_date";
    final static private String MODIFIED_DATE = "modified_date";
    
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
    
    public String getLogin() {
        return getString(LOGIN);
    }

    public void setLogin(String login) {
        setString(LOGIN, login);
    }
    
    public String getPassword() {
        return getString(PASSWORD);
    }

    public void setPassword(String password) {
        setString(PASSWORD, password);
    }
    
    public Date getCreateDate() {
        return getDate(CREATE_DATE);
    }

    public Date getLastUpdateDate() {
        return getDate(MODIFIED_DATE);
    }
}
