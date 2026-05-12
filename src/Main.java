import controller.LibraryController;
import model.BookDAO;

public class Main {
    public static void main(String[] args) {

        LibraryController controller = new LibraryController();
        controller.run();
    }
}