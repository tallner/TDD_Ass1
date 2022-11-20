package com.cte.models;

public class Book {
    private String title;
    private String genre;
    private String writer;
    private String publicityDate;
    private String rating;
    private Boolean available;

    public Book(){}

    public Book(String title, String genre, String writer, String publicityDate, String rating, Boolean available) {
        this.title = title;
        this.genre = genre;
        this.writer = writer;
        this.publicityDate = publicityDate;
        this.rating = rating;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublicityDate() {
        return publicityDate;
    }

    public void setPublicityDate(String publicityDate) {
        this.publicityDate = publicityDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Boolean getAvailability() {
        return available;
    }

    public void setAvailability(Boolean rating) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", writer='" + writer + '\'' +
                ", publicityDate='" + publicityDate + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
