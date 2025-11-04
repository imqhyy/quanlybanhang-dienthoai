package com.model;

import java.util.Scanner;

public class SmartPhone {
    private String maSP;
    private String tenSP;
    private String thuonghieu;
    private double giaBan;
    private String chipset;
    private String ram;
    private String rom;
    private String manhinh;
    private String chitiet; //mô tả chi tiết thông số camera và 1 số đặc điểm khác
    private static final Scanner sc = new Scanner(System.in);
    public SmartPhone() {
        maSP = "null";
        tenSP = "null";
        thuonghieu = "null";
        giaBan = 0;
        chipset = "null";
        ram = "null";
        rom = "null";
        manhinh = "null";
        chitiet = "null";
    }
    public SmartPhone(String maSP, String tenSP, String thuonghieu, double giaBan, String chipset, String ram, String rom, String manhinh, String chitiet) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.thuonghieu = thuonghieu;
        this.giaBan = giaBan;
        this.chipset = chipset;
        this.ram = ram;
        this.rom = rom;
        this.manhinh = manhinh;
        this.chitiet = chitiet;
    }
    public void getInfo() {
        System.out.println("------Thong tin dien thoai------");
        System.out.print("Ma san pham: " + maSP);
        System.out.println("Ten san pham: " + tenSP);
        System.out.println("Thuong hieu: " + thuonghieu);
        System.out.println("Gia ban: " + giaBan);
        System.out.println("------Cau hinh dien thoai------");
        System.out.println("Chipset: " + chipset);
        System.out.println("Ram: " + ram);
        System.out.println("Rom: " + rom);
        System.out.println("Man hinh: " + manhinh);
        System.out.println("Chi tiet: " + chitiet);
    }
    public void setInfo() {
        System.out.println("------Nhap thong tin dien thoai------");
        System.out.print("Ma san pham: ");
        maSP = sc.nextLine();
        System.out.print("Ten san pham: ");
        tenSP = sc.nextLine();
        System.out.print("Thuong hieu");
        thuonghieu = sc.nextLine();
        System.out.print("Gia ban: ");
        giaBan = sc.nextDouble();
        System.out.println("------Nhap cau hinh san pham------");
        System.out.print("Chipset: ");
        chipset = sc.nextLine();
        System.out.print("Ram: ");
        ram = sc.nextLine();
        System.out.print("Rom: ");
        rom = sc.nextLine();
        System.out.print("Man hinh: ");
        manhinh = sc.nextLine();
        System.out.print("Chi tiet: ");
        chitiet = sc.nextLine();
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
    public String getChipset() {
        return chipset;
    }
    public String getRam() {
        return ram;
    }
    public String getRom() {
        return rom;
    }
    public String getManHinh() {
        return manhinh;
    }
    public String getChiTiet() {
        return chitiet;
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
    public void setChipset() {
        System.out.print("Chipset: ");
        chipset = sc.nextLine();
    }
    public void setRam() {
        System.out.print("Ram: ");
        ram = sc.nextLine();
    }
    public void setRom() {
        System.out.print("Rom: ");
        rom = sc.nextLine();
    }
    public void setManHinh() {
        System.out.print("Man hinh: ");
        manhinh = sc.nextLine();
    }
    public void setChiTiet() {
        System.out.print("Chi tiet: ");
        chitiet = sc.nextLine();
    }
}
