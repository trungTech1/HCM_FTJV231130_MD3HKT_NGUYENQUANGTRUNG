package ra.mode;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Song {
    private String songId;
    private String songName;
    private String descriptions;
    private Singer singer;
    private String songWriter;
    private int year;
    private boolean songStatus;
    private LocalDateTime createdDate;


    public Song() {
        this.createdDate = LocalDateTime.now();
    }

    public Song(String songId, String songName, Singer singer, String descriptions, String songWriter, int year, Boolean songStatus) {
        this.songId = songId;
        this.songName = songName;
        this.descriptions = descriptions;
        this.singer = singer;
        this.songWriter = songWriter;
        this.year = year;
        this.songStatus = songStatus;
        this.createdDate = LocalDateTime.now();
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public void inputSong(Scanner scanner, Singer[] singers) {
        System.out.println("Nhập mã bài hát: ");
        String songId = scanner.nextLine();
        while (!validate(songId, "S\\w{4,}")) {
            System.out.println("Mã bài hát phải bắt đầu bằng S và có ít nhất 4 ký tự! Vui lòng nhập lại:");
            songId = scanner.nextLine();
        }
        this.songId = songId;
        System.out.println("Nhập tên bài hát: ");
        String songName = scanner.nextLine();
        while (!validate(songName, ".+")) {
            System.out.println("Tên bài hát không được để trống! Vui lòng nhập lại: ");
            songName = scanner.nextLine();
        }
        this.songName = songName;
        System.out.println("Nhập mô tả: ");
        this.descriptions = scanner.nextLine();
        if (singers.length == 0) {
            System.out.println("Danh sách ca sỹ trống!");
            return;
        }
        this.selectSinger(scanner, singers);
        System.out.println("Nhập tên nhạc sĩ: ");
        this.songWriter = scanner.nextLine();
        System.out.println("Nhập năm sáng tác: ");
        int year = Integer.parseInt(scanner.nextLine());
        while (!validate(String.valueOf(year), ".+")) {
            System.out.println("Năm sáng tác không được để trống! Vui lòng nhập lại: ");
            year = Integer.parseInt(scanner.nextLine());

        }
        this.year = year;
        System.out.println("Nhập tình trạng bài hát: ");
        this.songStatus = Boolean.parseBoolean(scanner.nextLine());
        this.createdDate = LocalDateTime.now();

    }

    public void selectSinger(Scanner scanner, Singer[] singers) {
        System.out.println("Danh sách ca sĩ: ");
        for (int i = 0; i < singers.length; i++) {
            System.out.println((i + 1) + ". " + singers[i].getSingerName());
        }
        System.out.println("Vui lòng chọn ca sĩ theo id: ");
        int singerId = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < singers.length; i++) {
            if (singerId == singers[i].getSingerId()) {
                this.singer = singers[i];
            }
        }
    }

    public void displaySong() {
        System.out.println(this.toString());
    }

    public boolean validate(String Str, String regex) {
        if (Str == null) {
            return false;
        }
        return Str.matches(regex);
    }

    @Override
    public String toString() {
        return String.format("""
                               
                Mã bài hát: %s,
                Tên bài hát: %s,
                Mô tả: %s,
                Ca sĩ: %s,
                Nhạc sĩ: %s,
                Năm sáng tác: %d,
                Tình trạng bài hát: %b,
                Ngày tạo: %s
                **********
                """, this.songId, this.songName, this.descriptions, this.singer.getSingerName(), this.songWriter, this.year, this.songStatus, this.createdDate);
    }
}
