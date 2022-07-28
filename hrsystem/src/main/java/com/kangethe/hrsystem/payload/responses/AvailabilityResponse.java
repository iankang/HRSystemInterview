package com.kangethe.hrsystem.payload.responses;

public class AvailabilityResponse {

    private Boolean isAvailable;

    public AvailabilityResponse(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "AvailabilityResponse{" +
                "isAvailable=" + isAvailable +
                '}';
    }
}
