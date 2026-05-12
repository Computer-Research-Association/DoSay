package controller;
import model.Book;
import model.BookDAO;
import view.LibraryView;

import java.util.List;

// CRUD
public class LibraryController {
    private final LibraryView libraryView;
    private final BookDAO dao;

    public LibraryController() {
            this.libraryView = new LibraryView();
            this.dao = new BookDAO();
    }
    public void run() {
        while (true) {
            libraryView.printMainMenu();
            try {
                int idx = libraryView.getIndex();

                switch (idx) {
                    case 1 -> option_add();
                    case 2 -> option_edit();
                    case 3 -> option_list();
                    case 4 -> option_delete();
                    case 0 -> option_quit();
                    default -> libraryView.printInvalidInputRetryMessage();
                }
            } catch (NumberFormatException e) {
                libraryView.printInvalidInputRetryMessage();
            }
        }
    }


    private void option_add() {
        String[] data = this.libraryView.inputBookData();
        dao.addBook(data[0], data[1]);
        libraryView.printSuccessMessage();
    }

    private void option_edit() {
        Book target = getSelect();
        if (target == null) { libraryView.printNoSearchMessage(); return; }

        String[] newData = libraryView.inputBookData();
        dao.updateBook(target.getId(), newData[0], newData[1]);
        libraryView.printSuccessMessage();
    }

    private void option_list() {
        libraryView.printBookList(dao.searchBooks("", ""));
    }

    private void option_delete() {
        Book target = getSelect();
        if (target == null) { libraryView.printNoSearchMessage(); return; }

        dao.removeBook(target.getId());
        libraryView.printSuccessMessage();
    }

    private void option_quit() {
        libraryView.printQuitMessage();
        System.exit(0);
    }


    private Book getSelect() {
        String inputTitle = libraryView.inputSearchTitle();
        String inputAuthor = libraryView.inputSearchAuthor();
        List<Book> found = dao.searchBooks(inputTitle, inputAuthor);

        if (found.isEmpty()) { return null; }

        libraryView.printBookList(found);

        while (true) {
            try {
                int idx = libraryView.getIndex();
                if (idx >= 1 && idx <= found.size()) return found.get(idx - 1);
            } catch (NumberFormatException _) { }
            libraryView.printInvalidInputRetryMessage();
        }
    }
}