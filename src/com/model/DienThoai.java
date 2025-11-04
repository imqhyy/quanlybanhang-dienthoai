package com.model;
import java.util.Scanner;
public class DienThoai {
    private String chipset;
    private String ram;
    private String rom;
    private String manhinh;
    protected static final Scanner sc = new Scanner(System.in);
    public DienThoai() {
        chipset = "null";
        ram = "null";
        rom = "null";
        manhinh = "null";
    }
    public DienThoai(String chipset, String ram, String rom, String manhinh) {
        this.chipset = chipset;
        this.ram = ram;
        this.rom = rom;
        this.manhinh = manhinh;
    }
    public void getInfo() {
        System.out.println("Chipset: " + chipset);
        System.out.println("Ram: " + ram);
        System.out.println("Rom: " + rom);
        System.out.println("Man hinh: " + manhinh);
    }
    public void setInfo() {
        System.out.print("Chipset: ");
        chipset = sc.nextLine();
        System.out.print("Ram: ");
        ram = sc.nextLine();
        System.out.print("Rom: ");
        rom = sc.nextLine();
        System.out.print("Man hinh: ");
        manhinh = sc.nextLine();
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
}
