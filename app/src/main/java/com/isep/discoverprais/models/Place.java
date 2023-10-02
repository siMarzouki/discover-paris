package com.isep.discoverprais.models;

public class Place {
    private String name;
    private String description;
    private String img;
    private double longitude;
    private double latitude;
    private boolean visited;

    // Constructors
    public Place() {
        // Default constructor
    }

    public Place(String name, String description, String img, double longitude, double latitude, boolean visited) {
        this.name = name;
        this.description = description;
        this.img = img;
        this.longitude = longitude;
        this.latitude = latitude;
        this.visited = visited;
    }

    // Getter and Setter methods for each attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // toString method for displaying Place details
    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", visited=" + visited +
                '}';
    }
}

