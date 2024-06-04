package modul4.tugas.data;

import modul4.tugas.books.*;
import modul4.tugas.exception.custom.illegalAdminAccess;
import modul4.tugas.util.iMenu;

import java.util.ArrayList;
import java.util.Scanner;

import static modul4.tugas.com.main.LibrarySystem.*;

public class Admin extends User implements iMenu{

    Scanner scanner = new Scanner(System.in);

    public Admin() {
        super("admin");
    }

    public void login() {
        System.out.print("Enter Username (admin): ");
        String username = scanner.next();
        System.out.print("Enter Password (admin): ");
        String password = scanner.next();
        try {
            if (isAdmin(username,password)) {
                System.out.println("Login as an admin succeed");
                menu();
            } else {
                System.out.println("Not found admin");
            }
        } catch (illegalAdminAccess e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isAdmin(String username, String password) throws illegalAdminAccess {
        if (username.equals("admin") &&  password.equals("admin")){
            System.out.println("login succeed");
        }else {
            throw new illegalAdminAccess("invalid credential");
        }
            return true;
    }

    public void menu() {
        try {
            while (true) {
                System.out.println("Admin Menu");
                System.out.println("1. Add student");
                System.out.println("2. Show student");
                System.out.println("3. Input book");
                System.out.println("4. Show the list of book");
                System.out.println("5. Logout");
                System.out.print("Choose between (1-5): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayStudents();
                        break;
                    case 3:
                        inputBook();
                        break;
                    case 4:
                        displayBooks(daftarBuku);
                        break;
                    case 5:
                        System.out.println("Logout succeed.");
                        return;
                    default:
                        System.out.println("Non valid choice!");
                }
            }
        }catch (Exception e){
            System.err.println(e);
        }

    }

    @Override
    public void addStudent() {
        // Implementasi penambahan mahasiswa
        System.out.println("Adding student...");
        System.out.print("Input name: ");
        scanner.nextLine(); // Menggunakan nextLine() untuk membaca nama dengan dua kata
        String name =scanner.nextLine();
        System.out.print("Enter the student ID: ");
        String nim = scanner.next();
        scanner.nextLine();
        while(true){
            if (nim.length() != 15 ) {
                System.out.print("Minimum number for student ID is 15!!!\n");
                System.out.print("Input student ID: ");
                nim = scanner.nextLine();
            } else if (checkNim(nim)){
                System.out.println("ID has been stored");
                System.out.print("Enter new ID: ");
                nim = scanner.nextLine();
            } else {
                break;
            }
        }
        System.out.print("Faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Major: ");
        String studyProgram = scanner.nextLine();
        studentList.add(new Student(nim, name, faculty, studyProgram));
        System.out.println("Student with an ID " + nim + " has been succesfully added to the system.");
    }


    // Admin.java
    private void inputBook() {
        // Implementasi input buku
        System.out.println("Insert book...");
        System.out.println("Choose the book genre:");
        System.out.println("1. Horror Book");
        System.out.println("2. Comedy Book");
        System.out.println("3. Story Book");
        System.out.print("Choose the genre between (1-3): ");
        int bookType = scanner.nextInt();
        scanner.nextLine();

        String idBuku, judul, author, category;
        int stok;
        System.out.print("Enter the book title: ");
        judul = scanner.nextLine();
        System.out.print("Enter the author name: ");
        author = scanner.nextLine();
        System.out.print("Enter the book category: ");
        category = scanner.nextLine();
        System.out.print("Enter the book stock: ");
        stok = scanner.nextInt();
        scanner.nextLine();

        switch (bookType) {
            case 1:
                idBuku = generateId("IS");
                daftarBuku.add(new HistoryBook(idBuku, judul, stok, category, author)) ;
                break;
            case 2:
                idBuku = generateId("HG");
                daftarBuku.add(new HistoryBook(idBuku, judul, stok, category, author)) ;
                break;
            case 3:
                idBuku = generateId("TK");
                daftarBuku.add(new TextBook(idBuku, judul, stok, category, author)) ;
                break;
            default:
                System.out.println("Non valid choice!");
                return;
        }
        System.out.println("Book has succesfully added to the library.");
    }

    @Override
    public void displayBooks(ArrayList<Book> bookArrayList) {
        // Implementasi menampilkan daftar buku
        System.out.println("List of an available books:");
        System.out.println("================================================================");
        System.out.println("|| No. || Book ID || Book Name || Author || Category || Stock ||");
        int index = 1;
        for (Book book :bookArrayList) {
            if (book != null) {
                System.out.println("|| " + index + " || " + book.getIdBuku() + " || " + book.getJudul() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStok() + " ||");
                index++;
            }
        }
        System.out.println("================================================================");
    }


    private void displayStudents() {
        System.out.println("List of an registered student:");
        for (Student student : studentList) {
            System.out.println("\nName: " + student.getName());
            System.out.println("ID: " + student.getNim());
            System.out.println("Faculty: " + student.getFaculty());
            System.out.println("Major: " + student.getStudyProgram());
            if (!student.getBorrowedBooks().isEmpty()) {
                System.out.println("  Borrowed books:");
                for (Book book : student.getBorrowedBooks()) {
                    System.out.println("    - " + book.getJudul());
                }
            }
        }
    }

    private String generateId(String prefix) {
        // Implementasi pembuatan ID unik
        // Contoh: HB001, SB002, TB003, dst.
        int nextId = i + 1;
        return prefix + String.format("%03d", nextId);
    }
}
