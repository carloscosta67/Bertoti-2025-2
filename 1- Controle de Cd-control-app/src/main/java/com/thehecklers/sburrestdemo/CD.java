package com.thehecklers.sburrestdemo;

import java.util.UUID;

public class CD {
    private String id;
    private String title;
    private String artist;
    private int releaseYear;

    public CD() {
        this(UUID.randomUUID().toString(), "Unknown Title", "Unknown Artist", 0);
    }

    public CD(String id, String title, String artist, int releaseYear) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    public CD(String title, String artist, int releaseYear) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
