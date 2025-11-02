package com.model;
import java.util.Scanner;

abstract public class conNguoi {
    String hovaten;
    int tuoi;
    String sdt;
    public conNguoi() {
        hovaten = "null";
        tuoi = 0;
        sdt = "null";        
    }
    public conNguoi(String hovaten, int tuoi, String sdt) {
        this.hovaten = hovaten;
        this.tuoi = tuoi;
        this.sdt = sdt;
    }
    private static final Scanner sc = new Scanner(System.in);
    public void getInfo() {
        System.out.println("ho va ten: " + hovaten);
        System.out.println("tuoi: " + tuoi);
        System.out.println("so dien thoai: " + sdt);
    }
    public void setInfo() {
        System.out.print("ho va ten: ");
        hovaten = sc.nextLine();
        tuoi = sc.nextInt();
        sc.nextLine(); //doc ky tu enter
        sdt = sc.nextLine();
    }
}
