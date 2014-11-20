package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import java.sql.Date;
public class User extends Model{
    public String getFirstName() {
        return getString("first_name");
    }

    public void setFirstName(String firstName) {
        setString("first_name", firstName);
    }
    
    public String getLastName() {
        return getString("last_name");
    }

    public void setLastName(String lastName) {
        setString("last_name", lastName);
    }
    
    public String getLogin() {
        return getString("login");
    }

    public void setLogin(String lastName) {
        setString("login", lastName);
    }
    
    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        setString("password", password);
    }
    
    public Date getCreateDate() {
        return getDate("create_date");
    }
    public Date getLastUpdateDate() {
        return getDate("modified_date");
    }
}
