package model;

public class Book {
    private final int id;
    private String bookname;
    private String author;

    public Book(int id, String bookname, String author) {
        this.id = id;
        this.bookname = bookname;
        this.author = author;
    }

    public int getId() { return this.id; }

    public String getBookname() { return this.bookname; }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() { return this.bookname + " - " + this.author; }
}
