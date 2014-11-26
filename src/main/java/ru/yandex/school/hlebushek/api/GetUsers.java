package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Users;

class GetUsers {

    /**
     * Method return json users model
     * @param userId int
     * @param login String
     * @return JsonElement
     */
    public JsonElement getUser( int userId, String login) throws ServiceGateException {
        JsonElement json = null;
        try {
            if (userId != 0 && login.isEmpty()) {
                Users user =  Users.findById(userId);
                json = setJsonObjectComment(user);
            } else if (!login.isEmpty() && userId == 0) {
                LazyList<Users> user = Users.where(String.format("login = '%s'", login));
                if (user.size() == 1) {
                    json = setJsonObjectComment(user.get(0));
                }
            }
        } catch (DBException e) {
            throw new ServiceGateException(e.getMessage());
        }
        return json;
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
