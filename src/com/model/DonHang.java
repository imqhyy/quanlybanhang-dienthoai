package com.model;

import java.time.LocalDate;
import java.util.Scanner;

public class DonHang {
    String maDH;
    private KhachHang khachhang;
    private NhanVien nhanvien;
    private SmartPhone smartphone;
    private int soluong;
    LocalDate ngayMua;

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
    public double getTonggiatri() {
        return smartphone.giaBan * soluong;
    }
    public void getInfo() {
        System.out.println("-----------Thong tin don hang-----------");
        System.out.println("Ma don hang: " + maDH);
        
        System.out.println("Khach hang: " + khachhang.hovaten);
        System.out.println("Nhan vien ban hang: " + nhanvien.hovaten);
    }
}
