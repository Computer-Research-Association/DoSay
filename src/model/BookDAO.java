package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String DB_URL = "jdbc:sqlite:src/database/library.db";
    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS books (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                author TEXT NOT NULL
            )""";

    public BookDAO() {
        initDatabase();
    }

    private void initDatabase() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException("DB 초기화 실패", e);
        }
    }


    public List<Book> searchBooks(String title, String author) {
        List<String> conditions = new ArrayList<>();
        List<String> params     = new ArrayList<>();

        if (isValid(title)) {
            conditions.add("title LIKE ?");
            params.add("%" + title.trim() + "%");
        }
        if (isValid(author)) {
            conditions.add("author LIKE ?");
            params.add("%" + author.trim() + "%");
        }

        String sql = buildSelectQuery(conditions);

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setString(i + 1, params.get(i));
            }

            return collectBooks(pstmt.executeQuery());

        } catch (SQLException e) {
            throw new RuntimeException("도서 검색 실패", e);
        }
    }


    public void addBook(String title, String author) {
        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("도서 추가 실패: " + title, e);
        }
    }

    public void removeBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("도서 삭제 실패: id=" + id, e);
        }
    }

    public void updateBook(int id, String title, String author) {
        String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("도서 수정 실패: id=" + id, e);
        }
    }


    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private boolean isValid(String value) {
        return value != null && !value.isBlank();
    }

    private String buildSelectQuery(List<String> conditions) {
        if (conditions.isEmpty()) {
            return "SELECT * FROM books";
        }
        return "SELECT * FROM books WHERE " + String.join(" AND ", conditions);
    }


    // DB 데이터 --> Book 객체
    private Book mapRow(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author")
        );
    }

    private List<Book> collectBooks(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            books.add(mapRow(rs));
        }
        return books;
    }
}