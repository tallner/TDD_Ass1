package Models;

public class Book {
    private String title;
    private String name;
    private String genre;
    private String writer;
    private String publicityDate;
    private String rating;

    public Book(){}

    public Book(String title, String name, String genre, String writer, String publicityDate, String rating) {
        this.name = name;
        this.title = title;
        this.genre = genre;
        this.writer = writer;
        this.publicityDate = publicityDate;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", writer='" + writer + '\'' +
                ", publicityDate='" + publicityDate + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
