import java.util.ArrayList;
import java.util.Scanner;

class user {
    public static Scanner scanner = new Scanner (System.in);
    public static ArrayList<Book> booksList(){
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("111", "Pluto Projector", "ROC", "Romance", 3));
        books.add(new Book("222", "Manchaster United", "TenHag", "Comedy", 2));
        books.add(new Book("333", "Sympson", "Todi", "Horror", 3));

        main.setBooks(books);
        System.out.println();
        System.out.println("======================= Daftar Buku di Perpustakaan =====================");
        System.out.printf("%-10s %-20s %-20s %-15s %-10s\n", "ID", "Judul", "Penulis", "Kategori", "Stok");
        for (Book book : books) {
            System.out.printf("%-10s %-20s %-20s %-15s %-10d\n", book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
        System.out.println();

        return books;
    }
}

class admin extends user {
    static String ADMIN_USERNAME = "admin";
    static String ADMIN_PASSWORD = "admin";

    public static void loginAsAdmin(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password : ");
        String password = scanner.next();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Succesfull Login as Admin\n");
            main.Login();
        } else {
            System.out.println("Admin User Not Found");
        }
    }

    public static void addStudent() {
        System.out.print("Masukkan nama mahasiswa: ");
        String name = scanner.next();
        System.out.print("Masukkan fakultas mahasiswa: ");
        String faculty = scanner.next();
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = scanner.next();
        System.out.print("Masukkan program studi mahasiswa: ");
        String program = scanner.next();

        main.students.add(new student(name, faculty, nim, program));
        System.out.println("Mahasiswa berhasil ditambahkan!\n");
    }

    public static void displayRegisteredStudents() {
        System.out.println("\n============= Mahasiswa Terdaftar ==============");
        System.out.printf("%-20s %-20s %-25s %-30s\n", "Nama", "Fakultas", "NIM", "Program Studi");
        for (student student : main.students) {
            System.out.printf("%-20s %-20s %-25s %-30s\n", student.getName(), student.getFaculty(), student.getNim(), student.getProgram());
            System.out.println("\n");
        }
    }
}

class student extends user {
    private String name;
    private String faculty;
    private String nim;
    private String program;

    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    public student(String name, String faculty, String nim, String program) {
        this.name = name;
        this.faculty = faculty;
        this.nim = nim;
        this.program = program;
    }
    public String getName() {
        return name;
    }
    public String getFaculty() {
        return faculty;
    }
    public String getNim() {
        return nim;
    }
    public String getProgram() {
        return program;
    }

    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku apapun.");
            return;
        }
        System.out.println("\n================== Buku Terpinjam ====================");
        System.out.printf("%-10s %-20s %-20s %-10s %-10s\n", "ID", "Judul", "Penulis", "Stok", "Durasi");
        for (Book book : borrowedBooks) {
            System.out.printf("%-10s %-20s %-20s %-10d %-10s\n", book.getId(), book.getTitle(), book.getAuthor(), book.getStock(), book.getDuration());
        }
    }

    public void borrowBook(ArrayList<Book> books, Scanner scanner) {
        user.booksList();
        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        String id = scanner.next();

        if (id.isEmpty()) {
            System.out.println("ID buku tidak boleh kosong. Kembali ke menu mahasiswa...");
            return;
        }

        Book book = null;
        for (Book b : books) {
            if (b.getId().equals(id)) {
                book = b;
                break;
            }
        }

        if (book == null) {
            System.out.println("Buku tidak ditemukan. Kembali ke menu mahasiswa...");
            return;
        }

        if (book.getStock() > 0) {
            System.out.print("Masukkan durasi peminjaman (maksimal 14 hari): ");
            int duration = scanner.nextInt();

            if (duration <= 0 || duration > 14) {
                System.out.println("Durasi peminjaman tidak valid. Kembali ke menu mahasiswa...");
            }

            book.setDuration(duration);

            borrowedBooks.add(book);
            book.setStock(book.getStock() - 1);
            System.out.println("Buku berhasil dipinjam!\n");
        } else {
            System.out.println("Maaf, stok buku habis.\n");
        }
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int stock;
    public int duration;

    public Book(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;

    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

class romanceBook extends Book {
    public String category;

    public romanceBook(String id, String title, String author, String category, int stock, String category1) {
        super(id, title, author, category, stock);
        this.category = category1;
    }
}

class comedyBook extends Book {
    public String category;

    public comedyBook(String id, String title, String author, String category, int stock, String category1) {
        super(id, title, author, category, stock);
        this.category = category1;
    }
}

class horrorBook extends Book {
    public String category;

    public horrorBook(String id, String title, String author, String category, int stock, String category1) {
        super(id, title, author, category, stock);
        this.category = category1;
    }
}


public class main {
    public static ArrayList<student> students = new ArrayList<>();
    public static ArrayList<Book> books = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login sebagai Student");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi (1-3): ");
            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    loginAsStudent();
                    break;
                case 2:
                    admin.loginAsAdmin(scanner);
                    break;
                case 3:
                    System.out.println("Adios!.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Error");
            }
        }
    }
    public static void loginAsStudent() {
        System.out.println("\n===== Student Menu =====");
        System.out.print("Masukkan NIM Anda (masukkan 99 untuk kembali): ");
        String nim = scanner.next();

        if (nim.equals("99")) {
            System.out.println("Kembali ke menu utama...");
            return;
        }

        student student = findStudent(nim);

        if (student == null) {
            System.out.println("Mahasiswa tidak ditemukan. Kembali ke menu utama...");
            return;
        }

        while (true) {
            System.out.println("1. Buku Terpinjam");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi (1-3): ");
            int userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    student.viewBorrowedBooks();
                    break;
                case 2:
                    student.borrowBook(books, scanner);
                    break;
                case 3:
                    System.out.println("Keluar dari akun mahasiswa...\n");
                    return;
                default:
                    System.out.println("Input tidak valid. Silakan coba lagi.\n");
            }
        }
    }

    public static void setBooks(ArrayList<Book> booksList) {
        books = booksList;
    }

    public static void setStudents(ArrayList<student> studentsList) {
        students = studentsList;
    }

    public static student findStudent(String nim) {
        for (student s : students) {
            if (s.getNim().equals(nim)) {
                return s;
            }
        }
        return null;
    }

    public static void Login() {
        System.out.println("===== Admin Menu =====");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tampilkan Mahasiswa Terdaftar");
        System.out.println("3. Tampilkan buku yang tersedia");
        System.out.println("4. Keluar");
        System.out.print("Pilih opsi (1-4): ");
        int userInput = scanner.nextInt();

        switch (userInput) {
            case 1:
                admin.addStudent();
                break;
            case 2:
                admin.displayRegisteredStudents();
                break;
            case 3:
                user.booksList();
                break;
            case 4:
                System.out.println("Keluar");
                break;
            default:
                System.out.println("Input tidak valid. Silakan coba lagi.");
        }
    }
}
