package controller;
import model.BankModel.Book;
import java.util.ArrayList;
import java.util.List;

// CRUD
public class BankController {
    private List<Book> BookList = new ArrayList<>();

    public void run() {
        while (true) {
            bankView.printMainMenu();
            int idx = bankView.getIndex();

            switch (idx) {
                case 1:
                    String[] data = bankView.inputBookData();
                    create(data[0], data[1]);
                    bankView.printSuccessMessage();
                    break;

                case 2:
                    String keyword = bankView.inputSearchKeyword();
                    List<Book> found = find(keyword, "");
                    if (found.isEmpty()) {
                        System.out.println("검색 결과 없음");
                        break;
                    }
                    bankView.printBookList(found);
                    int updateIdx = bankView.getIndex();
                    Book target = found.get(updateIdx - 1);
                    String[] newData = bankView.inputBookData();
                    update(target, newData[0], newData[1]);
                    bankView.printSuccessMessage();
                    break;

                case 3:
                    bankView.printBookList(BookList);
                    break;

                case 4:
                    String delKeyword = bankView.inputSearchKeyword();
                    List<Book> delFound = find(delKeyword, "");
                    if (delFound.isEmpty()) {
                        System.out.println("검색 결과 없음");
                        break;
                    }
                    bankView.printBookList(delFound);
                    int delIdx = bankView.getIndex();
                    Book delTarget = delFound.get(delIdx - 1);
                    delete(delTarget);
                    bankView.printSuccessMessage();
                    break;

                case 0:
                    System.out.println("종료합니다.");
                    return;
            }
        }
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