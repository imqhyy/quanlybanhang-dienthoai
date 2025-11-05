package com.list;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.model.SmartPhone;

public class DanhSachSmartphone {
    SmartPhone[] dsdt; // Mảng lưu SmartPhone
    int n; // Số lượng smartphone hiện có
    protected static final Scanner sc = new Scanner(System.in);

    // Constructor
    public DanhSachSmartphone() {
        n = 0;
        dsdt = new SmartPhone[0]; // Khởi tạo mảng rỗng
    }

    // Constructor
    public DanhSachSmartphone(SmartPhone[] dsdt, int n) {
        this.dsdt = dsdt;
        this.n = n;
    }

    // Nhập danh sách
    public void nhap() {
        boolean nhapThanhCong = false;
        System.out.print("\nNhap so luong smartphone: ");
        do {
            // bắt lỗi người dùng nhập chữ
            try {
                n = sc.nextInt();
                nhapThanhCong = true;
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                // xoá buffer trước khi người dùng nhập lại
                sc.nextLine();
            }
        } while (!nhapThanhCong);

        // Cấp phát mảng với số lượng n
        dsdt = new SmartPhone[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nSmartphone " + (i + 1));
            dsdt[i] = new SmartPhone();
            dsdt[i].setInfo();
            System.out.println();
        }
    }

    // Xuất danh sách
    public void xuat() {
        if (n == 0) {
            System.out.println("Danh sach SmartPhone dang rong!!");
            return;
        }
        System.out.println("\nDanh sach smartphone");
        for (int i = 0; i < n; i++) {
            System.out.println("\nSmartphone " + (i + 1));
            dsdt[i].getInfo();
        }
    }

    // Xuất ra chuỗi để ghi file
    public String xuatChuoi() {
        // Hàm này xuất dữ liệu toàn bộ smartphone thành chuỗi
        String xuatChuoi = null;
        for (int i = 0; i < n; i++) {
            // Nối tất cả thuộc tính lại, cách nhau bởi dấu phẩy
            String temp = String.join(",",
                    dsdt[i].getMaSP(),
                    dsdt[i].getTenSP(),
                    dsdt[i].getThuongHieu(),
                    Double.toString(dsdt[i].getGiaBan()), // Chuyển double sang String
                    dsdt[i].getChipset(),
                    dsdt[i].getRam(),
                    dsdt[i].getRom(),
                    dsdt[i].getManHinh(),
                    dsdt[i].getChiTiet());

            if (i == 0)
                xuatChuoi = temp;
            else {
                xuatChuoi = xuatChuoi + temp;
            }

            // Xuống dòng với mỗi dữ liệu smartphone mới
            xuatChuoi = xuatChuoi + "\n";
        }
        // Xoá ký tự enter cuối chuỗi
        if (xuatChuoi != null && xuatChuoi.length() > 0) {
            xuatChuoi = xuatChuoi.substring(0, xuatChuoi.length() - 1);
        }
        return xuatChuoi;
    }

    // Thêm (Tự nhập)
    public void them() {
        // Nới rộng mảng
        dsdt = Arrays.copyOf(dsdt, n + 1);

        // Tạo đối tượng mới và nhập thông tin
        dsdt[n] = new SmartPhone();
        dsdt[n].setInfo();

        // Tăng số lượng
        n++;
    }

    // Thêm (Đối tượng có sẵn)
    public void them(SmartPhone a) {
        dsdt = Arrays.copyOf(dsdt, n + 1);
        dsdt[n] = new SmartPhone();
        dsdt[n] = a;
        n++;
    }

    // Xóa
    public void xoa(String ma) {
        boolean daXoa = false;
        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaSP().equals(ma)) {
                for (int j = i; j < n - 1; j++) {
                    dsdt[j] = dsdt[j + 1];
                }
                dsdt = Arrays.copyOf(dsdt, n - 1);
                n--;
                daXoa = true;
                break;
            }
        }
        if (!daXoa) {
            System.err.println("Lỗi: Không tìm thấy smartphone có mã " + ma);
        }
    }

    // tÌm kiếm
    public SmartPhone timkiem(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaSP().equals(ma)) {
                return dsdt[i];
            }
        }
        return null;
    }

    public void sua() {
        String ma;
        System.out.print("Nhap ma smartphone can sua: ");
        ma = sc.nextLine();
        boolean suaThanhCong = false;

        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaSP().equals(ma)) {
                int chucnang = 0;
                do {
                    clearScreen();
                    System.out.println("--- SỬA THÔNG TIN SMARTPHONE (" + dsdt[i].getTenSP() + ") ---");
                    System.out.println("1. Sua ma san pham");
                    System.out.println("2. Sua ten san pham");
                    System.out.println("3. Sua thuong hieu");
                    System.out.println("4. Sua gia ban");
                    System.out.println("5. Sua chipset");
                    System.out.println("6. Sua RAM");
                    System.out.println("7. Sua ROM");
                    System.out.println("8. Sua man hinh");
                    System.out.println("9. Sua chi tiet");
                    System.out.println("10. Sua tat ca");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap tinh nang: ");

                    try {
                        chucnang = sc.nextInt();
                        sc.nextLine(); // Xóa buffer
                    } catch (InputMismatchException e) {
                        System.err.println("Vui long nhap so!!!");
                        sc.nextLine(); // Xoá buffer trước khi người dùng nhập lại
                    }

                    switch (chucnang) {
                        case 1:
                            dsdt[i].setMaSP();
                            break;
                        case 2:
                            dsdt[i].setTenSP();
                            break;
                        case 3:
                            dsdt[i].setThuongHieu();
                            break;
                        case 4:
                            dsdt[i].setGiaBan();
                            break;
                        case 5:
                            dsdt[i].setChipset();
                            break;
                        case 6:
                            dsdt[i].setRam();
                            break;
                        case 7:
                            dsdt[i].setRom();
                            break;
                        case 8:
                            dsdt[i].setManHinh();
                            break;
                        case 9:
                            dsdt[i].setChiTiet();
                            break;
                        case 10:
                            dsdt[i].setInfo(); // Gọi hàm setInfo của SmartPhone
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Chuc nang khong hop le!!!");
                            // Đợi 2 giây
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                            }
                    }
                } while (chucnang != 0);

                suaThanhCong = true;
                break; // Thoát vòng lặp for
            }
        }

        if (!suaThanhCong) {
            System.err.println("Khong co smartphone nay!!!");
        } else {
            System.out.println("Sua thong tin thanh cong!!!");
        }
    }

    // lệnh clear console
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