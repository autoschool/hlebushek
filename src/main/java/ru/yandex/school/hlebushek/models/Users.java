package ru.yandex.school.hlebushek.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.*;

@Table("Users")
@IdName("user_id")
public class Users extends Model {

    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
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
    
    public String getFirstName() {
        return getString(FIRST_NAME);
    }
    
    public String getLastName() {
        return getString(LAST_NAME);
    }

    public int getUserIsDeleted() {
        return getInteger(USER_IS_DELETED);
    }
}
