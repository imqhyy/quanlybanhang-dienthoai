package com.model;
import java.util.Scanner;

abstract public class ConNguoi {
    protected String hovaten;
    protected int tuoi;
    protected String sdt;
    protected ConNguoi() {
        hovaten = "null";
        tuoi = 0;
        sdt = "null";        
    }
    protected ConNguoi(String hovaten, int tuoi, String sdt) {
        this.hovaten = hovaten;
        this.tuoi = tuoi;
        this.sdt = sdt;
    }
    protected static final Scanner sc = new Scanner(System.in);
    protected void getInfo() {
        System.out.println("Ho va ten: " + hovaten);
        System.out.println("Tuoi: " + tuoi);
        System.out.println("So dien thoai: " + sdt);
    }
    protected void setInfo() {
        System.out.print("Ho va ten: ");
        hovaten = sc.nextLine();
        System.out.print("Tuoi: ");
        tuoi = sc.nextInt();
        sc.nextLine(); //doc ky tu enter
        System.out.print("So dien thoai: ");
        sdt = sc.nextLine();
    }
}
