package com.model;

public class SmartPhone extends DienThoai {
    private String maSP;
    private String tenSP;
    private String thuonghieu;
    private double giaBan;
    public SmartPhone() {
        super();
        maSP = "null";
        tenSP = "null";
        thuonghieu = "null";
        giaBan = 0;
    }
    public SmartPhone(String maSP, String tenSP, String thuonghieu, double giaBan, String chipset, String ram, String rom, String manhinh) {
        super(chipset, ram, rom, manhinh);
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.thuonghieu = thuonghieu;
        this.giaBan = giaBan;
    }
    @Override public void getInfo() {
        System.out.print("Ma san pham: " + maSP);
        System.out.println("Ten san pham: " + tenSP);
        System.out.println("Thuong hieu: " + thuonghieu);
        System.out.println("Gia ban: " + giaBan);
        super.getInfo();
    }
    @Override public void setInfo() {
        System.out.print("Ma san pham: ");
        maSP = sc.nextLine();
        System.out.print("Ten san pham: ");
        tenSP = sc.nextLine();
        System.out.print("Thuong hieu");
        thuonghieu = sc.nextLine();
        System.out.print("Gia ban: ");
        giaBan = sc.nextDouble();
        super.setInfo();
    }
    public String getMaSP() {
        return maSP;
    }
    public String getTenSP() {
        return tenSP;
    }
    public String getThuongHieu() {
        return thuonghieu;
    }
    public double getGiaBan() {
        return giaBan;
    }
    public void setMaSP() {
        System.out.print("Ma san pham: ");
        maSP = sc.nextLine();
    }
    public void setTenSP() {
        System.out.print("Ten san pham: ");
        tenSP = sc.nextLine();
    }
    public void setThuongHieu() {
        System.out.print("Thuong hieu: ");
        thuonghieu = sc.nextLine();
    }
    public void setGiaBan() {
        System.out.print("Gia ban: ");
        giaBan = sc.nextDouble();
    }
}
