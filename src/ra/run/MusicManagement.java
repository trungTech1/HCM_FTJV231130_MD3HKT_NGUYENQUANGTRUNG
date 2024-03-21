package ra.run;

import ra.mode.Singer;
import ra.mode.Song;

import java.util.Scanner;

public class MusicManagement {
    public static void main(String[] args) {
        Singer[] singers = new Singer[0];
        Song[] songs = new Song[0];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("************************MUSIC-MANAGEMENT*************************");
            System.out.println("1. Quản lý ca sỹ");
            System.out.println("2. Quản lý bài hát");
            System.out.println("3. Tìm kiếm vài hát");
            System.out.println("4. Thoát");
            System.out.println("*****************************************************************");
            System.out.println("Nhập lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    boolean isRunningSingerMenu = true;
                    while (isRunningSingerMenu) {
                        System.out.println("************************QUẢN-LÝ-CA-SỸ*************************");
                        System.out.println("1. Thêm mới ca sỹ");
                        System.out.println("2. Hiển thị thông tin tất cả ca sỹ");
                        System.out.println("3. Xóa ca sỹ theo mã ca sỹ");
                        System.out.println("4. Quay lại");
                        System.out.println("*****************************************************************");
                        System.out.println("Nhập lựa chọn của bạn: ");
                        int choiceSinger = Integer.parseInt(scanner.nextLine());
                        switch (choiceSinger) {
                            case 1:
                                singers = addSinger(scanner, singers);
                                break;
                            case 2:
                                displayAllSingers(singers);
                                break;
                            case 3:
                                singers = deleteSingerById(singers, scanner);
                                break;
                            case 4:
                                isRunningSingerMenu = false;
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                        }
                    }
                    break;
                case 2:
                    boolean isRunningSongMenu = true;
                    while (isRunningSongMenu) {
                        System.out.println("************************QUẢN-LÝ-BÀI-HÁT*************************");
                        System.out.println("1. Thêm mới bài hát");
                        System.out.println("2. Hiển thị thông tin tất cả bài hát");
                        System.out.println("3. Xóa bài hát theo mã bài hát");
                        System.out.println("4. Quay lại");
                        System.out.println("*****************************************************************");
                        System.out.println("Nhập lựa chọn của bạn: ");
                        int choiceSong = Integer.parseInt(scanner.nextLine());
                        switch (choiceSong) {
                            case 1:
                                songs = addSong(scanner, songs, singers);
                                break;
                            case 2:
                                displayAllSongs(songs);
                                break;
                            case 3:
                                songs = deleteSongById(songs, scanner);
                                break;
                            case 4:
                                isRunningSongMenu = false;
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                        }

                    }
                    break;
                case 3:
                    boolean isRunningSearchMenu = true;
                    while (isRunningSearchMenu) {
                        System.out.println("************************TÌM-KIẾM-BÀI-HÁT*************************");
                        System.out.println("1. Tìm kiếm bài hát theo tên ca sỹ hoặc thể loại");
                        System.out.println("2. Tìm kiếm ca sỹ theo tên ca sỹ hoặc thể loại");
                        System.out.println("3. Hiển thị 10 bài hát mới nhất");
                        System.out.println("4. Quay lại");
                        System.out.println("*****************************************************************");
                        System.out.println("Nhập lựa chọn của bạn: ");
                        int choiceSearch = Integer.parseInt(scanner.nextLine());
                        switch (choiceSearch) {
                            case 1:
                                searchSongBySingerOrGenre(songs, scanner);
                                break;
                            case 2:
                                searchSingerByNameOrGenre(singers, scanner);
                                break;
                            case 3:
                                displayTensSongNews(songs);
                                break;
                            case 4:
                                isRunningSearchMenu = false;
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Thoát chương trình");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    private static Song[] addSong(Scanner scanner, Song[] songs, Singer[] singers) {
        System.out.println("Vui lòng nhập số lượng bài hát cần thêm");
        int n = Integer.parseInt(scanner.nextLine());
        Song[] newSongs = new Song[songs.length + n];
        int songLength = songs.length;
        int currentNumber = 0;
        for (int i = 0; i < songLength; i++) {
            newSongs[currentNumber] = songs[i];
            currentNumber++;
        }
        for (int i = 0; i < n; i++) {
            Song song = new Song();
            System.out.println("Nhập thông tin bài hát thứ " + (i + 1) + ":");
            song.inputSong(scanner, singers);
            newSongs[currentNumber] = song;
            currentNumber++;
        }
        return newSongs;
    }

    private static void displayAllSongs(Song[] songs) {
        if(songs.length == 0){
            System.out.println("Danh sách bài hát trống!");
            return;
        }
        System.out.println("Danh sách bài hát: ");
        for (Song song : songs) {
            song.displaySong();
        }
    }

    private static Song[] deleteSongById(Song[] songs, Scanner scanner) {
        if (songs.length == 0) {
            System.out.println("Danh sách bài hát trống.Vui lòng thêm bài hát trước khi xóa!");
            return songs;
        }
        System.out.println("Nhập mã bài hát cần xóa: ");
        String songId = scanner.nextLine();
        int songLength = songs.length;
        int count = 0;
        for (int i = 0; i < songLength; i++) {
            if (songs[i].getSongId().equals(songId)) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Không tìm thấy mã bài hát cần xóa");
        } else {
            Song[] newSongs = new Song[songLength - count];
            int currentNumber = 0;
            for (int i = 0; i < songLength; i++) {
                if (!songs[i].getSongId().equals(songId)) {
                    newSongs[currentNumber] = songs[i];
                    currentNumber++;
                }
            }
            songs = newSongs;
        }
        return songs;
    }

    private static Singer[] addSinger(Scanner scanner, Singer[] singers) {
        System.out.println("Vui lòng nhập số lượng ca sỹ cần thêm: ");
        int n = Integer.parseInt(scanner.nextLine());
        Singer[] newSingers = new Singer[singers.length + n];
        int singerLength = singers.length;
        int currentNumber = 0;
        for (int i = 0; i < singerLength; i++) {
            newSingers[currentNumber] = singers[i];
            currentNumber++;
        }
        for (int i = 0; i < n; i++) {
            Singer singer = new Singer();
            System.out.println("Nhập thông tin ca sỹ thứ " + (i + 1) + ":");
            singer.inputSinger(scanner);
            newSingers[currentNumber] = singer;
            currentNumber++;
        }
        return newSingers;
    }

    private static void displayAllSingers(Singer[] singers) {
        if(singers.length == 0){
            System.out.println("Danh sách ca sỹ trống!");
            return;
        }
        System.out.println("Danh sách ca sỹ: ");
        for (Singer singer : singers) {
            singer.displaySinger();
        }
    }

    private static Singer[] deleteSingerById(Singer[] singers, Scanner scanner) {
        if(singers.length == 0){
            System.out.println("Danh sách ca sỹ trống.Vui lòng thêm ca sỹ trước khi xóa!");
            return singers;
        }
        System.out.println("Nhập mã ca sỹ cần xóa: ");
        int singerId = Integer.parseInt(scanner.nextLine());
        int singerLength = singers.length;
        int count = 0;
        for (int i = 0; i < singerLength; i++) {
            if (singers[i].getSingerId() == singerId) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Không tìm thấy mã ca sỹ cần xóa");
        } else {
            Singer[] newSingers = new Singer[singerLength - count];
            int currentNumber = 0;
            for (int i = 0; i < singerLength; i++) {
                if (singers[i].getSingerId() != singerId) {
                    newSingers[currentNumber] = singers[i];
                    currentNumber++;
                }
            }
            singers = newSingers;
        }
        return singers;
    }

    private static void searchSongBySingerOrGenre(Song[] songs, Scanner scanner) {
        System.out.println("Nhập tên ca sỹ hoặc thể loại cần tìm kiếm: ");
        String search = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < songs.length; i++) {
            if (songs[i].getSinger().getSingerName().contains(search) || songs[i].getSinger().getGenre().contains(search)) {
                songs[i].displaySong();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Không tìm thấy bài hát nào");
        }
    }

    private static void searchSingerByNameOrGenre(Singer[] singers, Scanner scanner) {
        System.out.println("Nhập tên ca sỹ hoặc thể loại cần tìm kiếm: ");
        String search = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < singers.length; i++) {
            if (singers[i].getSingerName().contains(search) || singers[i].getGenre().contains(search)) {
                singers[i].displaySinger();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Không tìm thấy ca sỹ nào");
        }
    }

    private static void displayTensSongNews(Song[] songs) {
        int[] topYears = new int[10];
        for (int i = 0; i < songs.length; i++) {
            int year = songs[i].getYear();
            for (int j = 0; j < topYears.length; j++) {
                if (year > topYears[j]) {
                    for (int k = topYears.length - 1; k > j; k--) {
                        topYears[k] = topYears[k - 1];
                    }
                    topYears[j] = year;
                    break;
                }
            }
        }
        for (Song song : songs) {
            for (int year : topYears) {
                if (song.getYear() == year) {
                    song.displaySong();
                    break;
                }
            }
        }
    }

}
