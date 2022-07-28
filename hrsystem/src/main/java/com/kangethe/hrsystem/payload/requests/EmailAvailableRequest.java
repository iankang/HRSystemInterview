package com.kangethe.hrsystem.payload.requests;

public class EmailAvailableRequest {

    private String email;


    public EmailAvailableRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailAvailableRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
