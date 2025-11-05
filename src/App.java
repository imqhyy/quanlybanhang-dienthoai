import com.service.QuanLyNhanVien;
import com.service.QuanLySmartPhone;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    protected static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        QuanLyNhanVien qlnv = new QuanLyNhanVien();
        QuanLySmartPhone qlsp = new QuanLySmartPhone();

        int chucnang = 0;
        do {
            clearScreen();
            System.out.println("--- CHƯƠNG TRÌNH QUẢN LÝ CỬA HÀNG ---");
            System.out.println("1. Quan ly Nhan Vien");
            System.out.println("2. Quan ly SmartPhone");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Nhap lua chon cua ban: ");

            // Bắt lỗi nhập chữ
            try {
                chucnang = sc.nextInt();
                sc.nextLine(); // Xoá buffer
            } catch (InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                // xoá buffer trước khi người dùng nhập lại
                sc.nextLine();
                chucnang = -1; // Đặt là một số không hợp lệ để lặp lại
            }

            switch (chucnang) {
                case 1:
                    qlnv.menu();
                    break;
                case 2:
                    qlsp.menu();
                    break;
                case 0:
                    System.out.println("Da thoat chuong trinh!");
                    break;
                default:
                    System.out.println("Chuc nang khong hop le!!!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
            }

        } while (chucnang != 0);
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
        } catch (final Exception e) {
            // Xử lý lỗi nếu không thể chạy lệnh (ví dụ: bị hạn chế quyền)
            System.out.println("\n(Không thể xóa màn hình)\n");
        }
    }
}