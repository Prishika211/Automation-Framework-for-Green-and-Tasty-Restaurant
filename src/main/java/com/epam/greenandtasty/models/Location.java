package com.epam.greenandtasty.models;

public class Location {
    private String id;
    private String address;
    private String description;
    private String totalCapacity;
    private String averageOccupancy;
    private String imageUrl;
    private String rating;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(String totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getAverageOccupancy() {
        return averageOccupancy;
    }

    public void setAverageOccupancy(String averageOccupancy) {
        this.averageOccupancy = averageOccupancy;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
