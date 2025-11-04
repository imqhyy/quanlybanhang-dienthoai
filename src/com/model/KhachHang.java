package com.model;

public class KhachHang extends ConNguoi {
    private String maKH;
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
        System.out.println("------Thông tin khách hàng------");
        System.out.println("Mã khách hàng: " + maKH);
        System.out.println("Họ và tên: " + super.getHoVaTen());
        System.out.println("Tuổi: " + super.getTuoi());
        System.out.println("Số điện thoại: " + super.getSDT());
    }
    @Override public void setInfo() {
        System.out.println("------Nhập thông tin khách hàng------");
        System.out.print("Mã khách hàng: ");
        maKH = sc.nextLine();
        super.setHoVaTen();
        super.setTuoi();
        super.setSDT();
    }
    public String getMaKH() {
        return maKH;
    }
    public void setMaKH() {
        System.out.print("Mã khách hàng: ");
        maKH = sc.nextLine();
    }
}
