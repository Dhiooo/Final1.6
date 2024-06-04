package modul4.tugas.data;

import modul4.tugas.books.Book;
import modul4.tugas.util.iMenu;

import java.util.ArrayList;
import java.util.Scanner;

import static modul4.tugas.com.main.LibrarySystem.daftarBuku;

public class Student extends User implements iMenu {

    Scanner scanner = new Scanner(System.in);
    private String name;
    private String faculty;
    private String studyProgram;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student(String nim) {
        super(nim);
    }

    public Student(String nim, String name, String faculty, String studyProgram) {
        super(nim);
        this.name = name;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    public void login() {
        if (checkNim(getNim())) {
            System.out.println("Login as an student, success");
            menu();
        } else {
            System.out.println("Non valid/Not found ID");
        }
    }

    private boolean checkNim(String nim) {
        return nim.length() == 15;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Student Menu");
                System.out.println("1. Borrowed book");
                System.out.println("2. Borrow some book");
                System.out.println("3. Return book");
                System.out.println("4. Borrow book or log out");
                System.out.print("Choose between(1-4): ");
                int pilih = scanner.nextInt();

                switch (pilih) {
                    case 1:
                        System.out.println("Book succesfully borrowed: ");
                        displayBorrowedBooks();
                        break;
                    case 2:
                        choiceBook(daftarBuku);
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        logout();
                        return;
                    default:
                        System.out.println("Non valid choice!");
                }
            }
        }catch (Exception e){
            System.err.println(e);
        }

    }
    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("None of the book has been borrowed.");
        } else {
            System.out.println("=================================================================================");
            System.out.println("|| No. || Book ID      || Book name   || Author      || Category   || Duration ||");
            System.out.println("=================================================================================");
            int index = 1;
            for (Book book : borrowedBooks) {
                System.out.println("|| " + index + " || " + book.getIdBuku() + " || " + book.getJudul() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getDuration() + " ||");
                index++;
            }
            System.out.println("=================================================================================");
        }
    }
    @Override
    public void choiceBook(ArrayList<Book> bookArrayList) {
        System.out.println("Available bool");
        System.out.println("================================================================");
        System.out.println("|| No. || ID || Name || Author || Category || Stock ||");
        int index = 1;
        for (Book book :bookArrayList) {
            if (book != null) {
                System.out.println("|| " + index + " || " + book.getIdBuku() + " || " + book.getJudul() + " || " + book.getAuthor() + " || " + book.getCategory() + "  || " + book.getStok() + " ||");
                index++;
            }
        }

        System.out.println("================================================================");
        System.out.print("Enter the specific book you want to borrow: ");
        String bookId = scanner.next();
        Book selectedBook = findBookById(bookId);
        scanner.nextLine();
        int durasi;
        if (selectedBook != null) {
            System.out.print("Enter duration of the borrowed book: ");
            durasi = Integer.parseInt(scanner.nextLine());
            selectedBook.setDuration(durasi);

            if (selectedBook.getStok() > 0) {
                selectedBook.setStok(selectedBook.getStok() - 1);
                borrowedBooks.add(selectedBook);
            } else {
                System.out.println("Book is not available.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }


    private void returnBook() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You are not borrowing any book yet.");
            return;
        }
        System.out.println("List of the borrowed book:");
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println((i + 1) + ". " + borrowedBooks.get(i).getJudul());
        }
        System.out.print("Choose the book you want to return (number): ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= borrowedBooks.size()) {
            Book returnedBook = borrowedBooks.remove(choice - 1);
            returnedBook.setStok(returnedBook.getStok() + 1);
            System.out.println("Book " + returnedBook.getJudul() + " Thank you for returning our book.");
        } else {
            System.out.println("Non valid choice.");
        }
    }

    private Book findBookById(String id) {
        for (Book book : daftarBuku) {
            if (book != null && book.getIdBuku().equals(id)) {
                return book;
            }
        }
        return null;
    }

    private void logout() {
        Scanner inp = new Scanner(System.in);

        if (borrowedBooks.isEmpty()) {
            System.out.println("Logout Succesfull.");
        } else {
            displayBorrowedBooks();
            System.out.println("Are you sure you want to borrow all of that book? ");
            System.out.print("Input Y (yes) atau N (no): ");
            char pilih = inp.next().charAt(0);

            if (pilih == 'y' || pilih == 'N'){
                System.out.println("The book has succesfully borrowed");
                System.out.println("Log out succeed.");
            } else if (pilih == 't' || pilih == 'T'){
                System.out.println("Book has succesfully return to us");
                System.out.println("Logout Succeed.");
            }
        }

    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
