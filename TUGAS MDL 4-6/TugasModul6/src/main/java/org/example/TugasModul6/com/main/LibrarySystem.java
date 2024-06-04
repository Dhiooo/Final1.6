package org.example.TugasModul6.com.main;

import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.TugasModul6.books.*;
import org.example.TugasModul6.data.Admin;
import org.example.TugasModul6.data.Student;

import java.util.ArrayList;

public class LibrarySystem {
    public static ArrayList<Book> daftarBuku = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();

    public static void startLibrarySystem(Stage stage) {
        daftarBuku.add(new StoryBook("Id666", "Optimus", 17, "Story", "Dyidan"));
        daftarBuku.add(new HistoryBook("JP696", "Bumblebee", 2, "History", "Dhian"));
        daftarBuku.add(new TextBook("DT00", "Sentinel", 4, "Text", "Erijja"));

        studentList.add(new Student("202310370311197", "Dhio", "Teknik", "HI"));
        studentList.add(new Student("202310370311241", "Bli", "Teknik", "Mesin"));
        studentList.add(new Student("202310370318104", "Fana", "Teknik", "Manajemen"));

        HBox root = new HBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label label = new Label("===== Library System =====");

        root.setAlignment(Pos.CENTER);



        Button studentLoginButton = new Button("Login sebagai Mahasiswa");
        studentLoginButton.setPrefHeight(50);
        studentLoginButton.setPrefWidth(100);

        Button adminLoginButton = new Button("Login sebagai Admin");
        adminLoginButton.setPrefHeight(50);
        adminLoginButton.setPrefWidth(100);

        Button exitButton = new Button("Keluar");
        exitButton.setPrefWidth(80);
        exitButton.setPrefHeight(30);

        studentLoginButton.setOnAction(event -> studentLogin(stage));
        adminLoginButton.setOnAction(event -> {
            try {
                new Admin().login(stage);
            } catch (Exception e) {
                showErrorDialog("Error", e.getMessage());
            }
        });
        exitButton.setOnAction(event -> stage.close());

        root.getChildren().addAll(label, studentLoginButton, adminLoginButton, exitButton);

        stage.setScene(scene);
        stage.setTitle("Library System");
        stage.show();
    }

    private static void studentLogin(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 400, 300);

        Label label = new Label("Masukkan NIM : ");
        TextField nimField = new TextField();
        Button loginButton = new Button("Login");
        Button backButton = new Button("Kembali");

        loginButton.setOnAction(event -> {
            String nimStudent = nimField.getText();
            if (nimStudent.length() == 15 && checkNim(nimStudent)) {
                Student student = new Student(nimStudent);
                student.login(stage);
            } else {
                showErrorDialog("Error", "Nim tidak terdaftar atau tidak valid!");
            }
        });

        backButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(label, nimField, loginButton, backButton);

        stage.setScene(scene);
    }

    private static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean checkNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }
}
