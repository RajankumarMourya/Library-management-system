import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Book {
    int serialNumber;
    String title;
    String writer;
    boolean isWithdrawn;
    int withdrawnBy;

    Book(int serialNumber, String title, String writer) {
        this.serialNumber = serialNumber;
        this.title = title;
        this.writer = writer;
        this.isWithdrawn = false;
        this.withdrawnBy = -1;
    }
}

public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Map<Integer, Integer> userWithdrawals = new HashMap<>();

    public static void main(String[] args) {
        initializeBooks();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Library Management System:");
            System.out.println("1. Display all books");
            System.out.println("2. Withdraw a book");
            System.out.println("3. Submit a book");
            System.out.println("4. Check withdrawn books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayBooks();
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter your user ID: ");
                    int userId = scanner.nextInt();
                    if (isValidUserId(userId)) {
                        System.out.print("Enter book serial number to withdraw: ");
                        int serialNumber = scanner.nextInt();
                        withdrawBook(userId, serialNumber);
                    } else {
                        System.out.println("Invalid user ID. Must be between 10 and 500000.");
                    }
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter your user ID: ");
                    userId = scanner.nextInt();
                    if (isValidUserId(userId)) {
                        System.out.print("Enter book serial number to submit: ");
                        int serialNumber = scanner.nextInt();
                        submitBook(userId, serialNumber);
                    } else {
                        System.out.println("Invalid user ID. Must be between 10 and 500000.");
                    }
                    System.out.println();
                    break;
                case 4:
                    checkWithdrawnBooks();
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }
        }
        
    }

    private static void initializeBooks() {
        books.add(new Book(1000, "Head First Java", "Kathy Sierra & Bert Bates"));
        books.add(new Book(1001, "Java: A Beginner’s Guide", "Herbert Schildt"));
        books.add(new Book(1002, "Java for Dummies", "Barry A. Burd"));
        books.add(new Book(1003, "Effective Java", "Joshua Bloch"));
        books.add(new Book(1004, "Head First Design Patterns", "Eric Freeman"));
        books.add(new Book(1005, "Spring in Action", "Craig Walls and Ryan Breidenbach"));
        books.add(new Book(1006, "Clean Code", "Robert C. Martin"));
        books.add(new Book(1007, "Test Driven: TDD and Acceptance TDD for Java Developers", "Lasse Koskela"));
        books.add(new Book(1008, "Test-Driven Java Development", "Alex Garcia and Viktor Farcic"));
        books.add(new Book(1009, "Thinking in Java", "Bruce Eckel"));
        books.add(new Book(1010, "Introduction to Algorithms", "Thomas H. Cormen"));
        books.add(new Book(1011, "Algorithms", "Robert Sedgewick & Kevin Wayne"));
        books.add(new Book(1012, "The Algorithm Design Manual", "Steve S. Skiena"));
        books.add(new Book(1013, "Algorithm for Interviews", "Adnan Aziz & Tsung-Hsien Lee"));
        books.add(new Book(1014, "Algorithm in Nutshell", "George T. Heineman & Gary Pollice"));
        books.add(new Book(1015, "Algorithm Design", "Kleinberg & Tardos"));
        books.add(new Book(1016, "Python Algorithms: Mastering Basic Algorithms in the Python Language", "Magnus Lie Hetland"));
        books.add(new Book(1017, "Data Structures and Algorithms", "Alfred V. Aho, Jeffrey D. Ullman & John E. Hopcroft"));
        books.add(new Book(1018, "The Design and Analysis of Algorithms", "Dexter C. Kozen"));
        books.add(new Book(1019, "Introduction to Algorithms: A Creative Approach", "Udi Manber"));
        books.add(new Book(1020, "Algorithm Design", "Kleinberg & Tardos"));
    }

    private static void displayBooks() {
        System.out.println("List of Books:");
        for (Book book : books) {
            System.out.println("Serial Number: " + book.serialNumber + ", Title: " + book.title + ", Writer: " + book.writer + ", Withdrawn: " + (book.isWithdrawn ? "Yes" : "No"));
        }
    }

    private static void withdrawBook(int userId, int serialNumber) {
        for (Book book : books) {
            if (book.serialNumber == serialNumber) {
                if (!book.isWithdrawn) {
                    book.isWithdrawn = true;
                    book.withdrawnBy = userId;
                    userWithdrawals.put(userId, serialNumber);
                    System.out.println("Book withdrawn successfully.");
                } else {
                    System.out.println("Book is already withdrawn.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void submitBook(int userId, int serialNumber) {
        for (Book book : books) {
            if (book.serialNumber == serialNumber) {
                if (book.isWithdrawn && book.withdrawnBy == userId) {
                    book.isWithdrawn = false;
                    book.withdrawnBy = -1;
                    userWithdrawals.remove(userId);
                    System.out.println("Book submitted successfully.");
                } else {
                    System.out.println("Book is not withdrawn by you.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void checkWithdrawnBooks() {
        boolean found = false;
        System.out.println("List of Withdrawn Books:");
        for (Book book : books) {
            if (book.isWithdrawn) {
                System.out.println("Serial Number: " + book.serialNumber + ", Title: " + book.title + ", Writer: " + book.writer + ", Withdrawn by User ID: " + book.withdrawnBy);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Mr Rajan no record found for any withdrawn book");
        }
    }

    private static boolean isValidUserId(int userId) {
        return userId >= 10 && userId <= 500000;
    }
}
