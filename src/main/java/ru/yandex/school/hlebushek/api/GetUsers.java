package ru.yandex.school.hlebushek.api;

import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Users;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api/")
public class GetUsers {

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@QueryParam("user-id") int userId) {
        Users user = Users.findById(userId);
        if (user != null) {
            return user.toJson(true);
        } else {
            return "user not found";
        }
    }

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserByLogin(@QueryParam("login") int userId) {
        LazyList<Users> list = Users.where(String.format("login = '%s'", userId));
        return list.get(0).toJson(true);
    }

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserContainLogin(@QueryParam("login") int userId) {
        LazyList<Users> list = Users.findBySQL(String.format("select * from users where login like '%s'", userId));
        return list.toJson(true);
    }
}
