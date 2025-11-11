package com.model;

public class KhachHang extends ConNguoi {
    private String maKH;
    public KhachHang() {
        super();
        maKH = "null";
    }
    public KhachHang(String maKH, String hovaten, int tuoi, String sdt) {
        super(hovaten, tuoi, sdt);
        this.maKH = maKH;
    }
    @Override public void getInfo() {
        System.out.println("\n------Thong tin khach hang------");
        System.out.println("Ma khach hang: " + maKH);
        System.out.println("Ho va ten: " + super.getHoVaTen());
        System.out.println("Tuoi: " + super.getTuoi());
        System.out.println("So dien thoai: " + super.getSDT());
    }
    @Override public void setInfo() {
        System.out.println("\n------Sua thong tin khach hang------");
        System.out.println("Ma khach hang: " + maKH);
        super.setHoVaTen();
        super.setTuoi();
        super.setSDT();
    }
    public void setInfo(int seedID) {
        System.out.println("\n------Nhap thong tin khach hang------");
        String ma = "KH";
        ma = ma + Integer.toString(seedID);
        maKH = ma;
        System.out.println("Ma khach hang: " + maKH);
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
