package org.example.TugasModul6.data;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.TugasModul6.books.Book;
import org.example.TugasModul6.data.User;
import org.example.TugasModul6.util.iMenu;

import java.util.ArrayList;

import static org.example.TugasModul6.com.main.LibrarySystem.*;

public class Student extends User implements iMenu {

    private String nim;
    private String faculty;
    private String studyProgram;

    public Student(String nim) {
        super("student");
        this.nim = nim;
    }

    public Student(String nim, String name, String faculty, String studyProgram) {
        super(name);
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    @Override
    public void menu() {
        // Ini adalah implementasi dari metode menu() yang diperlukan oleh iMenu
        // Anda bisa menempatkan kode yang ingin dijalankan ketika menu() dipanggil
    }

    public void login(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label label = new Label("Menu Mahasiswa");
        Button borrowBookButton = new Button("Pinjam Buku");
        Button displayBooksButton = new Button("Tampilkan Daftar Buku");
        Button logoutButton = new Button("Logout");

        borrowBookButton.setOnAction(event -> borrowBook(stage));
        displayBooksButton.setOnAction(event -> displayBooks(stage));
        logoutButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(label, borrowBookButton, displayBooksButton, logoutButton);

        stage.setScene(scene);
    }

    private void borrowBook(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label label = new Label("Masukkan ID buku yang ingin dipinjam: ");
        TextField idBukuField = new TextField();
        Button borrowButton = new Button("Pinjam");
        Button backButton = new Button("Kembali");

        borrowButton.setOnAction(event -> {
            String idBuku = idBukuField.getText();
            Book bookToBorrow = null;

            for (Book book : daftarBuku) {
                if (book.getIdBuku().equals(idBuku)) {
                    bookToBorrow = book;
                    break;
                }
            }

            if (bookToBorrow != null) {
                if (bookToBorrow.getStok() > 0) {
                    bookToBorrow.setStok(bookToBorrow.getStok() - 1);
                    showSuccessDialog("Success", "Buku " + bookToBorrow.getJudul() + " berhasil dipinjam.");
                } else {
                    showErrorDialog("Error", "Stok buku " + bookToBorrow.getJudul() + " sedang kosong.");
                }
            } else {
                showErrorDialog("Error", "Buku dengan ID " + idBuku + " tidak ditemukan.");
            }
        });

        backButton.setOnAction(event -> login(stage));

        root.getChildren().addAll(label, idBukuField, borrowButton, backButton);

        stage.setScene(scene);
    }

    private void displayBooks(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        for (Book book : daftarBuku) {
            root.getChildren().add(new Label("ID Buku: " + book.getIdBuku()));
            root.getChildren().add(new Label("Judul: " + book.getJudul()));
            root.getChildren().add(new Label("Author: " + book.getAuthor()));
            root.getChildren().add(new Label("Category: " + book.getCategory()));
            root.getChildren().add(new Label("Stok: " + book.getStok()));
            root.getChildren().add(new Label(""));
        }

        Button backButton = new Button("Kembali");
        backButton.setOnAction(event -> login(stage));

        root.getChildren().add(backButton);

        stage.setScene(scene);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
