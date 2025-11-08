package com.list;

import java.util.Scanner;
import com.model.DonHang;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.InputMismatchException;

public class DanhSachDonHang implements listInterface.IList {
    DonHang[] donHang;
    int n;
    protected static final Scanner sc = new Scanner(System.in);

    public DanhSachDonHang() {
        n = 0;
        donHang = new DonHang[0];
    }

    public DanhSachDonHang(DonHang[] donHang, int n) {
        this.n = n;
        this.donHang = donHang;
    }

    @Override
    public void nhap() {
        boolean nhapThanhCong = false;
        System.out.println("Nhap so luong don hang: ");
        do {
            try {
                n = sc.nextInt();
                nhapThanhCong = true;
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Vui long nhap so!!!");
                sc.nextLine();
            }
        } while (!nhapThanhCong);
        donHang = new DonHang[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap don hang " + (i + 1));
            donHang[i] = new DonHang();
            donHang[i].setInfo();
            System.out.println();
        }
    }

    @Override
    public void xuat() {
        if (n == 0) {
            System.out.println("Khong co don hang nao!!!");
        } else {
            for (int i = 0; i < n; i++) {
                System.out.println("\nDon hang " + (i + 1));
                System.out.println(donHang[i]);
            }
        }
    }

    @Override
    public void them() {
        donHang = Arrays.copyOf(donHang, n + 1);
        donHang[n] = new DonHang();
        donHang[n].setInfo();
        ++n;
    }

    public void them(DonHang a) {
        donHang = Arrays.copyOf(donHang, n + 1);
        donHang[n] = new DonHang();
        donHang[n] = a;
        n++;
    }

    @Override
    public void xoa(String maDH) {
        for (int i = 0; i < n; i++) {
            if (donHang[i].getMaDH().equals(maDH)) {
                for (int j = i; j < n - 1; j++) {
                    donHang[j] = donHang[j + 1];
                }
                donHang = Arrays.copyOf(donHang, n - 1);
                n--;
                break;
            }
        }
    }

    public String xuatChuoi() {
        String xuatChuoi = null;
        for (int i = 0; i < n; i++) {
            String temp = String.join(",", donHang[i].getMaDH(), donHang[i].getMaSP(),
                    donHang[i].getNgayDat().toString(), Integer.toString(donHang[i].getSoLuong()));
            if (i == 0) {
                xuatChuoi = temp;
            } else {
                xuatChuoi = xuatChuoi + temp;
            }
            xuatChuoi = xuatChuoi + "\n";
        }
        // xoá kí tự enter cuối chuỗi
        if (xuatChuoi != null && xuatChuoi.length() > 0) {
            xuatChuoi = xuatChuoi.substring(0, xuatChuoi.length());
        }
        return xuatChuoi;
    }

    @Override
    public void sua() {
        String ma;
        System.out.print("Nhap ma don hang can sua: ");
        ma = sc.nextLine();
        boolean suaThanhCong = false;
        for (int i = 0; i < n; i++) {
            if (donHang[i].getMaDH().equals(ma)) {
                int chucnang = 0;
                do {
                    clearScreen();
                    System.out.println("1. Sua ma don hang");
                    System.out.println("2. Sua ma san pham");
                    System.out.println("3. Sua ngay dat hang");
                    System.out.println("4. Sua so luong");
                    System.out.println("5. Sua tat ca");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap tinh nang: ");
                    try {
                        chucnang = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Vui long nhap so!!!");
                        sc.nextLine(); // Xoá buffer bàn phím trước khi người dùng nhập lại
                    }
                    switch (chucnang) {
                        case 1:
                            donHang[i].setMaDH();
                            break;
                        case 2:
                            donHang[i].setMaSP();
                            break;
                        case 3:
                            donHang[i].setNgayDat();
                            ;
                            break;
                        case 4:
                            donHang[i].setSoLuong();
                            break;
                        case 5:
                            donHang[i].setInfo();
                            break;
                        case 0:
                            break;
                        default: {
                            System.out.println("Chuc nang khong hop le!!!");
                            System.out.println("Nhan enter de quay lai!!!");
                            sc.nextLine();
                            break;
                        }
                    }
                } while (chucnang != 0);
                suaThanhCong = true;
                break;
            }
        }
        if (!suaThanhCong) {
            System.out.println("Khong co khach hang nay!!!");
        } else {
            System.out.println("Sua thanh cong!!!");
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
        } catch (final IOException | InterruptedException e) {
            // Xử lý lỗi nếu không thể chạy lệnh (ví dụ: bị hạn chế quyền)
            System.out.println("\n(Không thể xóa màn hình)\n");
        }
    }
}
