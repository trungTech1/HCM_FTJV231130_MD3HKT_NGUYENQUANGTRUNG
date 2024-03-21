package ra.mode;

import java.util.Scanner;

public class Singer {
    private static int singerCount = 1;
    private int singerId;
    private String singerName;
    private int age;
    private String nationality;
    private boolean gender;
    private String genre;

    public Singer() {
    }

    public Singer(int singerId, String singerName, int age, String nationality, boolean gender, String genre) {
        this.singerId = singerId;
        this.singerName = singerName;
        this.age = age;
        this.nationality = nationality;
        this.gender = gender;
        this.genre = genre;
    }

    public static int getSingerCount() {
        return singerCount;
    }

    public static void setSingerCount(int singerCount) {
        Singer.singerCount = singerCount;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void inputSinger(Scanner scanner) {
        this.singerId = singerCount++;
        System.out.println("Nhập tên ca sỹ: ");
        String singerName = scanner.nextLine();
        while (!validate(singerName, ".+")) {
            System.out.println("Tên ca sỹ không được để trống!");
            singerName = scanner.nextLine();
        }
        this.singerName = singerName;
        System.out.println("Nhập tuổi: ");
        int age = Integer.parseInt(scanner.nextLine());
        while (age < 0) {
            System.out.println("Tuổi phải lớn hơn 0!");
            age = Integer.parseInt(scanner.nextLine());
        }
        this.age = age;
        System.out.println("Nhập quốc tịch: ");
        String nationality = scanner.nextLine();
        while (!validate(nationality, ".+")) {
            System.out.println("Quốc tịch không được để trống!");
            nationality = scanner.nextLine();
        }
        this.nationality = nationality;
        System.out.println("Nhập giới tính (Nam/Nữ): ");
        this.gender = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Nhập thể loại nhạc: ");
        String genre = scanner.nextLine();
        while (!validate(genre, ".+")) {
            System.out.println("Thể loại nhạc không được để trống!");
            genre = scanner.nextLine();
        }
        this.genre = genre;
    }

    private boolean validate(String str, String regex) {
        return str.matches(regex);
    }

    public void displaySinger() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format("""
                               
                Mã ca sỹ : %d,
                Tên ca sỹ : %s,
                Tuổi : %d,
                Quốc tịch : %s,
                Giới tính : %s,
                Thể loại nhạc : %s
                **********
                """, this.singerId, this.singerName, this
                .age, this.nationality, this.gender ? "Nam" : "Nữ", this.genre);
    }
}
