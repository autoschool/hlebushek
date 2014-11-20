package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;

public class Users extends Model {

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
}
