package ru.yandex.school.hlebushek.api;

import org.javalite.activejdbc.DBException;
import ru.yandex.school.hlebushek.models.Users;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("GetUserInfo")
public class GetUsers {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@QueryParam("user-id") int userId, @QueryParam("login") String login) {
        try {
            if (userId != 0) {
                return Users.findBySQL(String.format("select * from users where user_id = '%s'", userId)).toJson(true);
            } else if (login != null && !login.isEmpty()) {
                return Users.findBySQL(String.format("select * from users where login = '%s'", login)).toJson(true);
            }
        } catch (DBException e) {
            return e.getMessage();
        }
        return "error query parameters";
    }
}
