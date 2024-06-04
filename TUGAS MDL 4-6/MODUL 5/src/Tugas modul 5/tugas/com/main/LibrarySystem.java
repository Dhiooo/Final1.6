package modul4.tugas.com.main;

import modul4.tugas.books.*;
import modul4.tugas.data.Admin;
import modul4.tugas.data.Student;
import modul4.tugas.data.User;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

    // menyimpan semua object dalam satu array dengan teknik polymorphism
    //public static Book daftarBuku[] = new Book[100]; // array satu dimensi untuk menyimpan buku
    public static ArrayList <Book> daftarBuku = new ArrayList <>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static int i = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarBuku.add(new StoryBook("IS102", "Insidious", 17, "Horror", "Martis"));
        daftarBuku.add(new HistoryBook("HG100", "How To Train Your Grandma", 2, "Comedy", "Moskov")) ;
        daftarBuku.add(new TextBook("TK787", "TEKKEN LA", 4, "Story", "Eudora")) ;

        // Mengisi daftar mahasiswa
        studentList.add(0, new Student("202310370311197", "Dhio", "Teknik","Informatika"));
        studentList.add(1, new Student("202310370311121", "Lilo", "Teknik","Informatika"));
        studentList.add(2, new Student("202310370311142", "Popo", "Teknik","Elektro"));

        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("===== Library System =====");
                System.out.println("1. Login as an student");
                System.out.println("2. Login as an admin.");
                System.out.println("3. Exit");
                System.out.print("Choose from (1-3): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter an ID : ");
                        String nimStudent = scanner.next();
                        scanner.nextLine();
                        while(true){
                            if (nimStudent.length() != 15 ) {
                                System.out.print("Minimum ID number is 15!!!\n");
                                System.out.print("Input your student ID: ");
                                nimStudent = scanner.nextLine();

                            } else if (checkNim(nimStudent)){
                                Student student = new Student(nimStudent);
                                student.login();
                                break;
                            } else {
                                System.out.println("ID not listed yet!");
                                break;
                            }
                        }
                        break;
                    case 2:
                        Admin admin = new Admin();
                        admin.login();
                        break;
                    case 3:
                        System.out.println("Thank you, hope you like it");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Non valid choice!");
                }
            }
        } catch (Exception e){
            System.err.println(e);
        }

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
