import java.util.Scanner;

public class tugas {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("=== Library System ===");

        while (true) {
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    loginStudent(scan);
                    break;
                case 2:
                    loginAdmin(scan);
                    break;
                case 3:
                    System.out.println("Adios!");
                    return;
                default:
                    System.out.println("Error.");
            }
        }
    }

    public static boolean validnim(String nim) {
        return nim.length() == 15;
    }

    public static void loginStudent(Scanner scannim) {
        System.out.println("Enter yout NIM :");
        String nim = scannim.nextLine();

        if (validnim(nim)) {
            System.out.println("Succesfull Login as Student");
        } else {
            System.out.println("User Not Found");
        }
    }

    static String ADMIN_USERNAME = "1";
    static String ADMIN_PASSWORD = "1";

    public static void loginAdmin(Scanner scanadmin) {
        System.out.println("Enter your username : ");
        String username = scanadmin.nextLine();

        System.out.println("Enter your password : ");
        String password = scanadmin.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Succesfull Login as Admin");
        } else {
            System.out.println("Admin User Not Found");
        }
    }
}


