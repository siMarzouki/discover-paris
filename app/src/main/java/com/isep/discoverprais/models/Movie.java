package com.isep.discoverprais.models;

import android.content.Context;

public class Movie {
    private int id;
    private String title;
    private String description;
    private String img; // Use the image file name (e.g., "poster1.jpg")
    private String video; // Video file name

    public Movie(int id, String title, String description, String img, String video) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.img = img;
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getImgResource(Context context) {
        // This method assumes that the "img" attribute contains the image file name (e.g., "poster1.jpg")
        // You need to remove the extension and any other special characters from the file name
        String resourceName = img.toLowerCase().replaceAll("[^a-z0-9_]", "_").replaceAll("_+", "_").replace(".jpg", "");
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
