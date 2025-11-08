package com.model;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DonHang {
    private String maDH;
    private LocalDate ngayDat;
    private String maSP;
    private int soLuong;
    private static final Scanner sc = new Scanner(System.in);

    // Hàm khởi tạo ko tham số
    public DonHang() {
        this.maDH = "null";
        this.ngayDat = null;
        this.soLuong = 0;
    }

    // Hàm khởi tạo full tham số
    public DonHang(String maDH, String maSP, LocalDate ngayDat, int soLuong) {
        this.maDH = maDH;
        this.maSP = maSP;
        this.ngayDat = ngayDat;
        this.soLuong = soLuong;
    }

    public void getInfo() {
        System.out.println("------Thong tin don hang------");
        System.out.println("Ma don hang: " + this.maDH);
        System.out.println("Ma san pham: " + this.maSP);
        System.out.println("Ngay dat hang: " + this.ngayDat);
        System.out.println("So luong: " + this.soLuong);
        System.out.println();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public LocalDate getNgayDat() {
        return ngayDat;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setInfo() {
        System.out.println("------Nhap thong tin don hang------");
        setMaDH();
        setMaSP();
        setNgayDat();
        setSoLuong();

    }

    // phương thức toString
    public String toString() {
        return "Ma don hang: " + maDH + "\n" +
                "Ten smartphone: " + maSP + "\n" +
                "Ngay dat hang: " + ngayDat + "\n" +
                "So luong: " + soLuong + "\n";

    }

    public void setMaDH() {
        System.out.println("Nhap ma don hang: ");
        this.maDH = sc.nextLine();
    }

    public void setMaSP() {
        System.out.println("Nhap ma smartphone: ");
        this.maSP = sc.nextLine();
    }

    public void setNgayDat() {
        System.out.println("Nhap ngay dat hang: ");
        this.ngayDat = nhapNgayThangNam(sc);
    }

    public void setSoLuong() {
        System.out.println("Nhap so luong: ");
        this.soLuong = sc.nextInt();
        sc.nextLine();
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Hàm này dùng để nhập ngày tháng năm
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