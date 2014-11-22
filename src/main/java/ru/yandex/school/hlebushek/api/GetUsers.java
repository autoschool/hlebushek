package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonObject;
import ru.yandex.school.hlebushek.models.Users;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("GetUser")
@Produces(MediaType.APPLICATION_JSON)
public class GetUsers {

    private JsonObject object = new JsonObject();

    /**
     * Method return json response users model
     * @param userId int { /GetComments?user-id=num }
     * @param login String { /GetComments?login=string }
     * @return JsonObject by String
     */
    @GET
    public String getUser(
            @QueryParam("user-id") int userId,
            @DefaultValue("") @QueryParam("login") String login) {
        if (userId != 0 && login.isEmpty()) {
            try {
                Users user =  Users.findById(userId);
                object = setJsonObjectComment(user);
            } catch (NullPointerException e) {
                System.out.println(String.format("User_id = '%s' not found", userId));
            }
        }
        if (!login.isEmpty() && userId == 0) {
            try {
                Users user = (Users) Users.where(String.format("login = '%s'", login)).get(0);
                object = setJsonObjectComment(user);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(String.format("Login = '%s' not found", login));
            }
        }
        return object.toString();
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
