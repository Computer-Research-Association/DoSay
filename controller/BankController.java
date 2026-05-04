package controller;
import model.BankModel.Book;
import java.util.ArrayList;
import java.util.List;

// CRUD
public class BankController {
    private List<Book> BookList = new ArrayList<>();

    public void run() {
        // Skip
    }


    private Book create(String name, String author) {
        var book = new Book(name, author);
        BookList.add(book);
        return book;
    }

    private List<Book> find(String name, String author) {
        boolean nameBlank = name.isBlank();
        boolean authorBlank = author.isBlank();

        if (nameBlank && authorBlank) { return List.of(); }

        return BookList.stream()
                .filter(b -> {
                    boolean nameMatch = nameBlank || b.getBookname().equals(name);
                    boolean authorMatch = authorBlank || b.getAuthor().equals(author);

                    return nameMatch && authorMatch;
                }).toList();
    }

    private void update(Book book, String name, String author) {
        book.setBookname(name);
        book.setAuthor(author);
    }

    private void delete(Book book) {
        BookList.remove(book);
    }
}