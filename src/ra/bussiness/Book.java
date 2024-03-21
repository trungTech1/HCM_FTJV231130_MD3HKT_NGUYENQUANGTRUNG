package ra.bussiness;

import java.util.Scanner;

public class Book {
    private static int nextbookId = 1;
    private int bookId;
    private String bookName;
    private String author;
    private String description;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus = true;

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void inputData(Scanner scanner) {
        this.bookId = nextbookId++;

        System.out.println("Nhập tên sách: ");
        String bookName = scanner.nextLine();
        while (!validate(bookName, ".+")) {
            System.out.println("Tên sách không được để trống!");
            System.out.println("Nhập tên sách: ");
            bookName = scanner.nextLine();
        }
        this.bookName = bookName;

        System.out.println("Nhâp tên tách giả: ");
        String author = scanner.nextLine();
        while (!validate(author, ".+")) {
            System.out.println("Tác giả không được để trống!");
            System.out.println("Nhâp tên tách giả: ");
            author = scanner.nextLine();
        }
        this.author = author;

        System.out.println("Mô tả: ");
        String description = scanner.nextLine();
        while (!validate(description, "\\S.{9,}")) {
            System.out.println("Mô tả không được để trống và phải có ít nhất 10 ký tự!");
            System.out.println("Mô tả: ");
            description = scanner.nextLine();
        }
        this.description = description;

        System.out.println("Gía nhập: ");
        double importPrice = Double.parseDouble(scanner.nextLine());
        while (importPrice <= 0) {
            System.out.println("Gía nhập phải lớn hơn 0!");
            System.out.println("Gía nhập: ");
            importPrice = Double.parseDouble(scanner.nextLine());
        }
        this.importPrice = importPrice;

        System.out.println("Gía bán: ");
        double exportPrice = Double.parseDouble(scanner.nextLine());
        while (exportPrice <= 0 || exportPrice <= importPrice * 1.2) {
            System.out.println("Gía bán phải lớn hơn 0 và lớn hơn giá nhập 20%!");
            System.out.println("Gía bán: ");
            exportPrice = Double.parseDouble(scanner.nextLine());
        }
        this.exportPrice = exportPrice;

        System.out.println("Trạng thái sách: ");
        this.bookStatus = Boolean.parseBoolean(scanner.nextLine());
    }

    public void displayData() {
        System.out.println("Mã sách: " + bookId);
        System.out.println("Tên sách: " + bookName);
        System.out.println("Tác giả: " + author);
        System.out.println("Mô tả: " + description);
        System.out.println("Gía nhập: " + importPrice);
        System.out.println("Gía xuất: " + exportPrice);
        System.out.println("Trạng thái sách: " + bookStatus);
        this.interest = (float) calculateInterest();
    }

    public double calculateInterest() {
        return exportPrice - importPrice;
    }

    private boolean validate(String str, String regex) {
        return str.matches(regex);
    }
}
