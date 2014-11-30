package ru.yandex.school.hlebushek.service;

import com.google.gson.JsonElement;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import ru.yandex.school.hlebushek.exceptions.ServiceGateException;
import ru.yandex.school.hlebushek.models.Users;

class UsersData extends ServiceResult {

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
                json = setJsonObject(user);
            } else if (!login.isEmpty() && userId == 0) {
                LazyList<Users> user = Users.where(String.format("login = '%s'", login));
                if (user.size() == 1) {
                    json = setJsonObject(user.get(0));
                }
            }
        } catch (DBException e) {
            throw new ServiceGateException(e.getMessage());
        }
        return json;
    }

    /**
     * Method add new user in BD
     * @param login String
     * @param firstName String
     * @param lastName String
     * @param password String
     * @return Users new
     * @throws ServiceGateException
     */
    public Users setUser(String login,
                         String firstName,
                         String lastName,
                         String password) throws ServiceGateException {
        Users user = null;
        try {
            if (login != null) {
                user = Users.first("login = ?", login);
                if (user == null) {
                    user = new Users();
                    user.setLogin(login);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPassword(password);
                    user.saveIt();
                }
                user = Users.first("login = ?", login);
            }
        } catch (DBException e) {
            throw new ServiceGateException(e.getMessage());
        }
        return user;
    }
}
