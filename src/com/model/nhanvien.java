package com.model;

public class NhanVien extends ConNguoi {
    String maNV;
    String chucvu;
    double luong;
    public static int soLuong = 0;
    public NhanVien() {
        super();
        maNV = "null";
        chucvu = "null";
        luong = 0;
    }
    public NhanVien(String hovaten, int tuoi, String sdt, String maNV, String chucvu, double luong) {
        super(hovaten, tuoi, sdt);
        this.maNV = maNV;
        this.chucvu = chucvu;
        this.luong = luong;
    }
    @Override public void getInfo() {
        super.getInfo();
        System.out.println("Ma khach hang: " + maNV);
        System.out.println("Chuc vu: " + chucvu);
        System.out.println("luong: " + luong);
    }
    @Override public void setInfo() {
        super.setInfo();
        System.out.print("maKH: ");
        maNV = sc.nextLine();
        System.out.print("Chuc vu: ");
        chucvu = sc.nextLine();
        System.out.print("Luong: ");
        luong = sc.nextDouble();
    }
}
