
import com.service.QuanLyDonHang;
import com.service.QuanLyKhachHang;
import com.service.QuanLyNhanVien;
import com.service.QuanLySmartPhone;
import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            QuanLyNhanVien dsnv = new QuanLyNhanVien();
            QuanLyKhachHang dskh = new QuanLyKhachHang();
            QuanLySmartPhone dssp = new QuanLySmartPhone();
            QuanLyDonHang dsdh = new QuanLyDonHang();
            System.out.println("Nhan enter de dong thong bao nay!!!");
            sc.nextLine();
            int chucnang = 0;
            do {
                clearScreen();
                System.out.println("1. Quan ly nhan vien");
                System.out.println("2. Quan ly khach hang");
                System.out.println("3. Quan ly smartphone");
                System.out.println("4. Quan ly don hang");
                System.out.println("0. Thoat");

                boolean nhapThanhCong = false;
                do {
                    System.out.print("Nhap chuc nang: ");
                    // bắt lỗi người dùng nhập chữ
                    try {
                        chucnang = sc.nextInt();
                        nhapThanhCong = true;
                        sc.nextLine(); // Xoá kí tự enter trong buffer
                    } catch (InputMismatchException e) {
                        System.err.println("Vui long nhap so!!!");
                        sc.nextLine();// Xoá buffer trước khi người dùng nhập lại
                    }
                } while (!nhapThanhCong);
                switch (chucnang) {
                    case 1:
                        dsnv.menu();
                        break;
                    case 2:
                        dskh.menu();
                        break;
                    case 3:
                        dssp.menu();
                        break;
                    case 4:
                        dsdh.menu();
                        break;
                    case 0:
                        break;
                    default: {
                        System.out.println("Chuc nang khong hop le!!!");
                        System.out.println("Nhan enter de nhap lai!!!");
                        sc.nextLine();
                    }
                }
            } while (chucnang != 0);
        }
    }

    // lệnh clear console (copy từ các file service của anh)
    public static void clearScreen() {
        try {
            // Lựa chọn lệnh dựa trên hệ điều hành
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Đối với Windows, dùng 'cls'
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Đối với Linux/macOS, dùng 'clear'
                // Hoặc dùng chuỗi escape code (ít phụ thuộc vào lệnh 'clear')
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final IOException | InterruptedException e) {
            // Xử lý lỗi nếu không thể chạy lệnh (ví dụ: bị hạn chế quyền)
            System.out.println("\n(Không thể xóa màn hình)\n");
        }
    }
}