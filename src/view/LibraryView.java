package view;

import java.util.Scanner;
import java.util.List;
import model.Book;

public class LibraryView {
    private final Scanner sc = new Scanner(System.in);

    public LibraryView() {}

    public void printMainMenu() {
        System.out.println("1. 도서 추가");
        System.out.println("2. 도서 정보 수정");
        System.out.println("3. 도서 목록");
        System.out.println("4. 도서 삭제");
        System.out.println("0. 프로그램 종료");
    }

    public int getIndex() {
        System.out.print(">> ");
        return Integer.parseInt(sc.nextLine());
    }

    // Controller에서 String[]로 받으니까
    public String[] inputBookData() {
        System.out.print("책 제목 >> ");
        String bookname = sc.nextLine();
        System.out.print("책 저자 >> ");
        String author = sc.nextLine();
        return new String[]{bookname, author};
    }

    // update case에서 필요
    public String inputSearchTitle() {
        System.out.print("검색할 책 제목(공백 시 무시) >> ");
        return sc.nextLine();
    }

    public String inputSearchAuthor() {
        System.out.print("검색할 책 저자(공백 시 무시) >> ");
        return sc.nextLine();
    }

    public void printBookList(List<Book> list) {
        System.out.println("======== List ========");
        if (list.isEmpty()) {
            System.out.println("도서 목록이 비어있습니다.");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i+1 + ". ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("======================");
    }

    public void printNoSearchMessage() {
        System.out.println("검색 결과가 없습니다.");
    }

    public void printInvalidInputRetryMessage() {
        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
    }

    public void printSuccessMessage() {
        System.out.println("처리가 완료되었습니다.");
    }

    public void printQuitMessage() {
        System.out.println("프로그램을 종료합니다.");
    }
}
