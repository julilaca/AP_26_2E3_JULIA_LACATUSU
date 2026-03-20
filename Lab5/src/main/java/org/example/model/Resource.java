package org.example.model;

public class Resource {
    private String id;
    private String author;
    private String location;
    private String year;
    private String title;

    public Resource(String id, String author, String location, String year, String title) {
        this.id = id;
        this.author = author;
        this.location = location;
        this.year = year;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", location='" + location + '\'' +
                ", year='" + year + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
