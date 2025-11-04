package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HoaDon {
    String maHD;
    private KhachHang khachhang;
    private NhanVien nhanvien;
    private SmartPhone smartphone;
    private int soLuong;
    private LocalDate ngayMua;
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public HoaDon() {
        maHD = "null";
        khachhang = new KhachHang();
        nhanvien = new NhanVien();
        smartphone = new SmartPhone();
        soLuong = 0;
        ngayMua = null;
    }
    public HoaDon(String maHD, KhachHang khachhang, NhanVien nhanvien, SmartPhone smartphone, int soLuong, LocalDate ngayMua) {
        maHD = this.maHD;
        this.khachhang = khachhang;
        this.nhanvien = nhanvien;
        this.smartphone = smartphone;
        this.soLuong = soLuong;
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

    public void getInfo() {
        String ngayMuaHang = ngayMua.format(DATE_FORMATTER);
        System.out.println("------Hoa don------");
        System.out.println("Ngay: " + ngayMuaHang);
        System.out.println("Ma don hang: " + maHD);
        System.out.println("------Thong tin khach hang------");
        System.out.println("Ma khach hang: " + khachhang.getMaKH());
        System.out.println("Khach hang: " + khachhang.getHoVaTen());
        System.out.println("------Thong tin nhan vien ban hang------");
        System.out.println("Ma nhan vien: " + nhanvien.getMaNV());
        System.out.println("Ho va ten: " + nhanvien.getHoVaTen());
        System.out.println("------San pham------");

        System.out.println("Tong: ");
    }
}
