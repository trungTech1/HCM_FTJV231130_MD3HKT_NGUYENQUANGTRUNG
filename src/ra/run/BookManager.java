package ra.run;

import ra.bussiness.Book;

import java.util.Scanner;

public class BookManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Book[] books = new Book[0];
        while (true) {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Tìm ra sản phẩm có giá lớn thứ hai trong thư viện");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin sách theo mã sách");
            System.out.println("7. Thoát");
            System.out.println("***********************************************************");
            System.out.println("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    books = addBook(scanner, books);
                    break;
                case 2:
                    displayAllBooks(books);
                    break;
                case 3:
                    findSecondBookExportPrice(books);
                    break;
                case 4:
                    books = deleteByIdBook(books, scanner);
                    break;
                case 5:
                    searchBookByNameOrDescription(books, scanner);
                    break;
                case 6:
                    updateBookById(books, scanner);
                    break;
                case 7:
                    System.out.println("Thoát chương trình");
                    System.exit(0);
                    break;

            }
        }
    }

    private static Book[] addBook(Scanner scanner, Book[] books) {
        System.out.println("Vui lòng nhập số lượng sách cần thêm");
        int n = Integer.parseInt(scanner.nextLine());
        if (n < 0 || n > 100) {
            System.out.println("Số lượng sách không thể lớn hơn 100");
            return books;
        }
        Book[] newBooks = new Book[books.length + n];
        int currentNumber = 0;
        for (int i = 0; i < books.length; i++) {
            newBooks[currentNumber] = books[i];
            currentNumber++;
        }
        for (int i = 0; i < n; i++) {
            Book book = new Book();
            System.out.println("Nhập thông tin sách thứ " + (i + 1) + ":");
            book.inputData(scanner);
            newBooks[currentNumber] = book;
            currentNumber++;
        }
        return newBooks;
    }

    private static void displayAllBooks(Book[] books) {
        if (books.length == 0) {
            System.out.println("Chưa có sách nào trong thư viện");
            return;
        }
        for (int i = 0; i < books.length; i++) {
            books[i].displayData();
        }
    }

    private static void findSecondBookExportPrice(Book[] books) {
        if (books.length == 0) {
            System.out.println("Chưa có cuốn sách nào.vui lòng thêm sách");
            return;
        } else if (books.length == 1) {
            System.out.println("Chỉ có 1 cuốn sách trong thư viện vui lòng thêm sách để có thể tìm ra giá sách thứ 2");
            return;
        } else {
            double max = books[0].getExportPrice();
            double secondMax = books[0].getExportPrice();
            for (int i = 0; i < books.length; i++) {
                if (books[i].getExportPrice() > max) {
                    secondMax = max;
                    max = books[i].getExportPrice();
                } else if (books[i].getExportPrice() > secondMax && books[i].getExportPrice() < max) {
                    secondMax = books[i].getExportPrice();
                }
            }
            for (int i = 0; i < books.length; i++) {
                if (books[i].getExportPrice() == secondMax) {
                    System.out.println(String.format("Sách có giá thứ 2 trong thư viện là: %s", books[i].getBookName()));
                }
            }
        }
    }

    private static Book[] deleteByIdBook(Book[] books, Scanner scanner) {
        System.out.println("Nhập mã sách muốn xóa :");
        int id = Integer.parseInt(scanner.nextLine());
        int booksLength = books.length;
        int index = -1;
        for (int i = 0; i < booksLength; i++) {
            if (books[i].getBookId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Không tìm thấy sách cần xóa");
        } else {
            Book[] newBooks = new Book[books.length - 1];
            for (int i = 0, j = 0; i < booksLength; i++) {
                if (i == index) {
                    continue;
                }
                newBooks[j++] = books[i];
            }
            books = newBooks;
            System.out.println(String.format("Đã xóa sách có mã %s khỏi thư viện", id));
        }
        return books;
    }

    private static void searchBookByNameOrDescription(Book[] books, Scanner scanner) {
        System.out.println("Nhập tên sách hoặc mô tả sách cần tìm kiếm: ");
        String search = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getBookName().contains(search) || books[i].getDescription().contains(search)) {
                books[i].displayData();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Không tìm thấy sách nào");
        }
    }
    private static void updateBookById(Book[] books, Scanner scanner) {
        System.out.println("Nhập mã sách cần cập nhật thông tin: ");
        int id = Integer.parseInt(scanner.nextLine());
        int booksLength = books.length;
        int index = -1;
        for (int i = 0; i < booksLength; i++) {
            if (books[i].getBookId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Không tìm thấy sách cần cập nhật");
        } else {
            books[index].inputData(scanner);
            System.out.println("Đã cập nhật thông tin sách");
        }
    }
}
