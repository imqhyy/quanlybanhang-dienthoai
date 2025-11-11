package com.model;

import java.util.InputMismatchException;

public class NhanVien extends ConNguoi {
    private String maNV;
    private String chucvu;
    private double luong;
    public NhanVien() {
        super();
        maNV = "null";
        chucvu = "null";
        luong = 0;
    }
    public NhanVien(String maNV, String hovaten, int tuoi, String sdt, String chucvu, double luong) {
        super(hovaten, tuoi, sdt);
        this.maNV = maNV;
        this.chucvu = chucvu.toLowerCase();
        this.luong = luong;
    }
    @Override public void getInfo() {
        System.out.println("\n------Thong tin nhan vien------");
        System.out.println("Ma nhan vien: " + maNV);
        System.out.println("Ho va ten: " + super.getHoVaTen());
        System.out.println("Tuoi: " + super.getTuoi());
        System.out.println("So dien thoai: " + super.getSDT());
        System.out.println("Chuc vu: " + chucvu);
        System.out.println("Luong: " + luong);
    }
    @Override public void setInfo() {
        System.out.println("\n------Nhap thong tin nhan vien------");
        System.out.println("Ma nhan vien: " + maNV);
        super.setHoVaTen();
        super.setTuoi();
        super.setSDT();
        setChucVu();
        setLuong();
    }
    public void setInfo(int seedID) {
        System.out.println("\n------Nhap thong tin nhan vien------");
        String ma = "NV";
        ma = ma + Integer.toString(seedID);
        maNV = ma;
        System.out.println("Ma nhan vien: " + maNV);
        super.setHoVaTen();
        super.setTuoi();
        super.setSDT();
        setChucVu();
        setLuong();
    }
    public String getMaNV() {
        return maNV;
    }
    public String getChucVu() {
        return chucvu;
    }
    public double getLuong() {
        return luong;
    }
    public void setMaNV() {
        System.out.print("Ma nhan vien: ");
        maNV = sc.nextLine();
    }
    public void setChucVu() {
        System.out.print("Chuc vu: ");
        chucvu = sc.nextLine().toLowerCase();
    }
    public void setLuong() {
        boolean nhapThanhCong = false;
        do {
            System.out.print("Luong: ");
            //bắt lỗi nếu người dùng nhập chữ
            try {
                luong = sc.nextDouble();
                nhapThanhCong = true;
                sc.nextLine();
            } catch(InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                //Xoá buffer trước khi nhập liệu mới
                sc.nextLine();
            }
        } while(!nhapThanhCong);
        
    }
}
