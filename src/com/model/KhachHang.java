package com.model;

public class KhachHang extends ConNguoi {
    private String maKH;
    private static int soLuongKH = 0;
    public KhachHang() {
        super();
        maKH = "null";
        soLuongKH++;
    }
    public KhachHang(String maKH, String hovaten, int tuoi, String sdt) {
        super(hovaten, tuoi, sdt);
        this.maKH = maKH;
        soLuongKH++;
    }
    @Override public void getInfo() {
        System.out.println("\n------Thong tin khach hang------");
        System.out.println("Ma khach hang: " + maKH);
        System.out.println("Ho va ten: " + super.getHoVaTen());
        System.out.println("Tuoi: " + super.getTuoi());
        System.out.println("So dien thoai: " + super.getSDT());
    }
    @Override public void setInfo() {
        System.out.println("\n------Nhap thong tin khach hang------");
        setMaKH();
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
    public static void HowManyKhachHang() {
        System.out.println("Co " + soLuongKH + " khach hang");
    }
}
