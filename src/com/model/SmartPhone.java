package com.model;

public class SmartPhone extends DienThoai {
    String maSP;
    String tenSP;
    double giaBan;
    public SmartPhone() {
        super();
        maSP = "null";
        giaBan = 0;
    }
    public SmartPhone(String maSP, String tenSP, double giaBan, String chipset, String ram, String rom, String manhinh) {
        super(chipset, ram, rom, manhinh);
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
    }
    @Override public void getInfo() {
        System.out.print("Ma san pham: " + maSP);
        System.out.println("Ten san pham: " + tenSP);
        System.out.println("Gia ban: " + giaBan);
        super.getInfo();
    }
    @Override public void setInfo() {
        System.out.print("Ma san pham: ");
        maSP = sc.nextLine();
        System.out.print("Ten san pham: ");
        tenSP = sc.nextLine();
        System.out.print("Gia ban: ");
        giaBan = sc.nextDouble();
        super.setInfo();
    }
}
