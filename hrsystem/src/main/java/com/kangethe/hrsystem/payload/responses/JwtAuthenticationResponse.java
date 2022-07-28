package com.kangethe.hrsystem.payload.responses;

import com.kangethe.hrsystem.entities.User;

public class JwtAuthenticationResponse {
    private String access_token;
    private String token_type;
    private User user;

    public JwtAuthenticationResponse(String access_token, String tokent_type, User user) {
        this.access_token = access_token;
        this.token_type = "Bearer";
        this.user = user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationResponse{" +
                "access_token='" + access_token + '\'' +
                ", tokent_type='" + token_type + '\'' +
                ", user=" + user +
                '}';
    }
}
