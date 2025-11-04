package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HoaDon {
    private String maHD;
    private KhachHang khachhang;
    private NhanVien nhanvien;
    private SmartPhone smartphone;
    private LocalDate ngayMua;
    private static final Scanner sc = new Scanner(System.in);
    //Định dạng ngày tháng năm theo kiểu VN
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public HoaDon() {
        maHD = "null";
        khachhang = new KhachHang();
        nhanvien = new NhanVien();
        smartphone = new SmartPhone();
        ngayMua = null;
    }
    public HoaDon(String maHD, KhachHang khachhang, NhanVien nhanvien, SmartPhone smartphone, LocalDate ngayMua) {
        maHD = this.maHD;
        this.khachhang = khachhang;
        this.nhanvien = nhanvien;
        this.smartphone = smartphone;
        this.ngayMua = ngayMua;
    }
    //Hàm này dùng để nhập ngày tháng năm
    public static LocalDate nhapNgayThangNam(Scanner sc) {
        LocalDate ngayNhap = null;
        String inputString;

        do {
            System.out.print("Nhập ngày(DD/MM/YYYY): ");
            inputString = sc.nextLine();
            try {
                ngayNhap = LocalDate.parse(inputString, DATE_FORMATTER);
                return ngayNhap;
            } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error: Định dạng ngày nhập không hợp lệ!!!");
                ngayNhap = null;
            }
        } while (ngayNhap == null);

        return null;
    }

    public void getInfo() {
        String ngayMuaHang = ngayMua.format(DATE_FORMATTER);
        System.out.println("------Hoá đơn------");
        System.out.println("Ngày: " + ngayMuaHang);
        System.out.println("Mã đơn hàng: " + maHD);
        System.out.println("------Thông tin khách hàng------");
        System.out.println("Mã khách hàng: " + khachhang.getMaKH());
        System.out.println("Khách hàng: " + khachhang.getHoVaTen());
        System.out.println("------Thông tin nhân viên bán hàng------");
        System.out.println("Mã nhân viên: " + nhanvien.getMaNV());
        System.out.println("Họ và tên: " + nhanvien.getHoVaTen());
        System.out.println("------Sản phẩm------");

        System.out.println("Tổng: ");
    }
}
