package com.model;

public class KhachHang extends ConNguoi {
    protected String maKH;
    public static int soLuong = 0;
    public KhachHang() {
        super();
        maKH = "null";
    }
    public KhachHang(String hovaten, int tuoi, String sdt, String maKH) {
        super(hovaten, tuoi, sdt);
        this.maKH = maKH;
    }
    @Override public void getInfo() {
        super.getInfo();
        System.out.println("Ma khach hang: " + maKH);
    }
    @Override public void setInfo() {
        super.setInfo();
        System.out.print("Ma khach hang: " + maKH);
        maKH = sc.nextLine();
    }
}
