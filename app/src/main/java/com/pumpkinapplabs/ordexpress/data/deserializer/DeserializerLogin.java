package com.pumpkinapplabs.ordexpress.data.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.pumpkinapplabs.ordexpress.data.model.LoginPost;

import java.lang.reflect.Type;

public class DeserializerLogin implements JsonDeserializer<LoginPost> {
    @Override
    public LoginPost deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int userid = json.getAsJsonObject().get("userid").getAsInt();
        String access_token = json.getAsJsonObject().get("access_token").getAsString();
        int rol = json.getAsJsonObject().get("rol").getAsInt();
        String message = json.getAsJsonObject().get("message").getAsString();
        String token_type = json.getAsJsonObject().get("token_type").getAsString();

        LoginPost loginpost = new LoginPost(message, access_token, token_type, userid, rol);
        return loginpost;
    }
}
