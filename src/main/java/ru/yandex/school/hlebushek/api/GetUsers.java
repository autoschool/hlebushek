package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.models.Users;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("GetUser")
@Produces(MediaType.APPLICATION_JSON)
public class GetUsers {

    private Exception exception;
    private JsonElement json;

    /**
     * Method return json response users model
     * @param userId int { /GetComments?userId=num }
     * @param login String { /GetComments?login=string }
     * @return JsonObject by String
     */
    @GET
    public String getUser(
            @QueryParam("userId") int userId,
            @DefaultValue("") @QueryParam("login") String login) {
        try {
            if (userId != 0 && login.isEmpty()) {
                Users user =  Users.findById(userId);
                json = setJsonObjectComment(user);
            }
            if (!login.isEmpty() && userId == 0) {
                LazyList<Users> user = Users.where(String.format("login = '%s'", login));
                if (user.size() == 1) {
                    json = setJsonObjectComment(user.get(0));
                }
            }
        } catch (DBException e) {
            exception = e;
        }
        return ApiAnswer.create(json, exception).toString();
    }

    private JsonObject setJsonObjectComment(Users user) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", user.getUserId());
        jsonObject.addProperty("login", user.getLogin());
        jsonObject.addProperty("first_name", user.getFirstName());
        jsonObject.addProperty("last_name", user.getLastName());
        jsonObject.addProperty("create_date", user.getCreateDate());
        jsonObject.addProperty("modified_date", user.getModifiedDate());
        jsonObject.addProperty("is_deleted", user.getIsDeleted());
        return jsonObject;
    }
}
