package org.example.TugasModul6.data;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.TugasModul6.books.Book;
import org.example.TugasModul6.books.HistoryBook;
import org.example.TugasModul6.books.StoryBook;
import org.example.TugasModul6.books.TextBook;
import org.example.TugasModul6.exception.custom.IllegalAdminAccess;
import org.example.TugasModul6.util.iMenu;

import java.util.ArrayList;
import java.util.Scanner;

import static org.example.TugasModul6.com.main.LibrarySystem.*;

public class Admin extends User implements iMenu {

    Scanner scanner = new Scanner(System.in);

    public Admin() {
        super("admin");
    }

    public void login(Stage stage) throws IllegalAdminAccess {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label userLabel = new Label("Masukkan Username (admin): ");
        TextField usernameField = new TextField();
        Label passLabel = new Label("Masukkan Password (admin): ");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button backButton = new Button("Kembali");

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (isAdmin(username, password)) {
                showAdminMenu(stage);
            } else {
                showErrorDialog("Error", "Invalid credentials");
            }
        });

        backButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(userLabel, usernameField, passLabel, passwordField, loginButton, backButton);

        stage.setScene(scene);
    }

    private boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    private void showAdminMenu(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label label = new Label("Menu Admin");
        Button addStudentButton = new Button("Tambah Mahasiswa");
        Button displayStudentsButton = new Button("Tampilkan Mahasiswa");
        Button inputBookButton = new Button("Input Buku");
        Button displayBooksButton = new Button("Tampilkan Daftar Buku");
        Button logoutButton = new Button("Logout");

        addStudentButton.setOnAction(event -> addStudent(stage));
        displayStudentsButton.setOnAction(event -> displayStudents(stage));
        inputBookButton.setOnAction(event -> inputBook(stage));
        displayBooksButton.setOnAction(event -> displayBooks(stage));
        logoutButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(label, addStudentButton, displayStudentsButton, inputBookButton, displayBooksButton, logoutButton);

        stage.setScene(scene);
    }

    private void addStudent(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label nameLabel = new Label("Masukkan Nama: ");
        TextField nameField = new TextField();
        Label nimLabel = new Label("Masukkan NIM: ");
        TextField nimField = new TextField();
        Label facultyLabel = new Label("Masukkan Fakultas: ");
        TextField facultyField = new TextField();
        Label studyProgramLabel = new Label("Masukkan Program Studi: ");
        TextField studyProgramField = new TextField();
        Button addButton = new Button("Tambah");
        Button backButton = new Button("Kembali");

        addButton.setOnAction(event -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String faculty = facultyField.getText();
            String studyProgram = studyProgramField.getText();

            if (nim.length() == 15 && !checkNim(nim)) {
                studentList.add(new Student(nim, name, faculty, studyProgram));
                showAdminMenu(stage);
            } else {
                showErrorDialog("Error", "NIM tidak valid atau sudah terdaftar!");
            }
        });

        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().addAll(nameLabel, nameField, nimLabel, nimField, facultyLabel, facultyField, studyProgramLabel, studyProgramField, addButton, backButton);

        stage.setScene(scene);
    }

    private void inputBook(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label titleLabel = new Label("Masukkan Judul Buku: ");
        TextField titleField = new TextField();
        Label authorLabel = new Label("Masukkan Author Buku: ");
        TextField authorField = new TextField();
        Label categoryLabel = new Label("Masukkan Category Buku: ");
        TextField categoryField = new TextField();
        Label stockLabel = new Label("Masukkan Stok Buku: ");
        TextField stockField = new TextField();
        Button addButton = new Button("Tambah");
        Button backButton = new Button("Kembali");

        addButton.setOnAction(event -> {
            String judul = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            int stok = Integer.parseInt(stockField.getText());

            daftarBuku.add(new Book(generateId("B"), judul, stok, category, author));
            showAdminMenu(stage);
        });

        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().addAll(titleLabel, titleField, authorLabel, authorField, categoryLabel, categoryField, stockLabel, stockField, addButton, backButton);

        stage.setScene(scene);
    }

    private String generateId(String prefix) {
        int id = daftarBuku.size() + 1;
        return prefix + String.format("%03d", id);
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
        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().add(backButton);

        stage.setScene(scene);
    }

    private void displayStudents(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        for (Student student : studentList) {
            root.getChildren().add(new Label("NIM: " + student.getNim()));
            root.getChildren().add(new Label("Nama: " + student.getName()));
            root.getChildren().add(new Label("Fakultas: " + student.getFaculty()));
            root.getChildren().add(new Label("Program Studi: " + student.getStudyProgram()));
            root.getChildren().add(new Label(""));
        }

        Button backButton = new Button("Kembali");
        backButton.setOnAction(event -> showAdminMenu(stage));

        root.getChildren().add(backButton);

        stage.setScene(scene);
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void menu() {

    }
}
