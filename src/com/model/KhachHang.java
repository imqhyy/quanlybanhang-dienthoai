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
        System.out.println("------Thong tin khach hang------");
        System.out.println("Ma khach hang: " + maKH);
        System.out.println("Ho va ten: " + super.getHoVaTen());
        System.out.println("Tuoi: " + super.getTuoi());
        System.out.println("So dien thoai: " + super.getSDT());
    }
    @Override public void setInfo() {
        System.out.println("------Nhap thong tin khach hang------");
        System.out.print("Ma khach hang: ");
        maKH = sc.nextLine();
        super.setHoVaTen();
        super.setTuoi();
        super.setSDT();
    }
    public String getMaKH() {
        return maKH;
    }
    public void setMaKH() {
        System.out.print("Ma khach hang: ");
        maKH = sc.nextLine();
    }
}
