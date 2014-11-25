package ru.yandex.school.hlebushek.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

abstract class ApiAnswer {

    private static JsonObject answer = new JsonObject();

    private ApiAnswer() {}

    public static JsonObject create(JsonElement data, Exception e) {
        answer.add("data", data);
        if (e != null) {
            answer.addProperty("is_error", true);
            answer.addProperty("error", e.getMessage());
        } else {
            answer.addProperty("is_error", false);
            answer.addProperty("error", "");
        }
        return answer;
    }
}
