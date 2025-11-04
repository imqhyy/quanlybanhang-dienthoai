package com.model;
import java.util.Scanner;
public class DienThoai {
    protected String chipset;
    protected String ram;
    protected String rom;
    protected String manhinh;
    protected static final Scanner sc = new Scanner(System.in);
    protected DienThoai() {
        chipset = "null";
        ram = "null";
        rom = "null";
        manhinh = "null";
    }
    protected DienThoai(String chipset, String ram, String rom, String manhinh) {
        this.chipset = chipset;
        this.ram = ram;
        this.rom = rom;
        this.manhinh = manhinh;
    }
    protected void getInfo() {
        System.out.println("Chipset: " + chipset);
        System.out.println("Ram: " + ram);
        System.out.println("Rom: " + rom);
        System.out.println("Man hinh: " + manhinh);
    }
    protected void setInfo() {
        System.out.print("Chipset: ");
        chipset = sc.nextLine();
        System.out.print("Ram: ");
        ram = sc.nextLine();
        System.out.print("Rom: ");
        rom = sc.nextLine();
        System.out.print("Man hinh: ");
        manhinh = sc.nextLine();
    }
}
