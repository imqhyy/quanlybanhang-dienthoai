package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class DonHang {
    String maDH;
    private KhachHang khachhang;
    private NhanVien nhanvien;
    private SmartPhone smartphone;
    private int soluong;
    private LocalDate ngayMua;
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public DonHang() {
        maDH = "null";
        khachhang = new KhachHang();
        nhanvien = new NhanVien();
        smartphone = new SmartPhone();
        soluong = 0;
        ngayMua = null;
    }
    public DonHang(String maDH, KhachHang khachhang, NhanVien nhanvien, SmartPhone smartphone, int soluong, LocalDate ngayMua) {
        maDH = this.maDH;
        this.khachhang = khachhang;
        this.nhanvien = nhanvien;
        this.smartphone = smartphone;
        this.soluong = soluong;
        this.ngayMua = ngayMua;
    }

    public static LocalDate nhapNgayThangNam(Scanner sc) {
        LocalDate ngayNhap = null;
        String inputString;

        do {
            System.out.print("Nhap ngay(DD/MM/YYYY): ");
            inputString = sc.nextLine();
            try {
                ngayNhap = LocalDate.parse(inputString, DATE_FORMATTER);
                return ngayNhap;
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Dinh dang ngay nhap khong hop le");
                ngayNhap = null;
            }
        } while (ngayNhap == null);

        return null;
    }

    
}
