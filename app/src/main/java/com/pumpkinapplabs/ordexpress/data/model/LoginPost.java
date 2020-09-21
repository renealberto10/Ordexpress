package com.pumpkinapplabs.ordexpress.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//modelo de datos que se recibe de la consulta de la api
public class LoginPost {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("access_token")
    @Expose
    private String access_token;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("userid")
    @Expose
    private Integer userid;
    @SerializedName("rol")
    @Expose
    private Integer rol;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return access_token;
    }

    public void setToken(String token) {
        this.access_token = access_token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public LoginPost(String message, String access_token, String tokenType, Integer userid, Integer rol) {
        this.message = message;
        this.access_token = access_token;
        this.tokenType = tokenType;
        this.userid = userid;
        this.rol = rol;
    }

}

