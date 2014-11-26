package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

abstract class ServiceResult {

    public ServiceResult() {}

    public static JsonObject create(JsonElement data, Exception e) {
        JsonObject jsonResult = new JsonObject();
        jsonResult.add("data", data);
        if (e != null) {
            jsonResult.addProperty("is_error", true);
            jsonResult.addProperty("error", e.getMessage());
        } else {
            jsonResult.addProperty("is_error", false);
            jsonResult.addProperty("error", "");
        }
        return jsonResult;
    }
}
