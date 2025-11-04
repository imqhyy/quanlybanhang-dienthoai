package com.model;
import java.util.Scanner;

abstract public class ConNguoi {
    private String hovaten;
    private int tuoi;
    private String sdt;
    public ConNguoi() {
        hovaten = "null";
        tuoi = 0;
        sdt = "null";        
    }
    public ConNguoi(String hovaten, int tuoi, String sdt) {
        this.hovaten = hovaten;
        this.tuoi = tuoi;
        this.sdt = sdt;
    }
    protected static final Scanner sc = new Scanner(System.in);
    abstract public void getInfo();
    abstract public void setInfo();
    public void setHoVaTen() {
        System.out.print("Họ và tên: ");
        hovaten = sc.nextLine();
    }
    public void setTuoi() {
        System.out.print("Tuổi: ");
        tuoi = sc.nextInt();
    }
    public void setSDT() {
        System.out.print("Số điện thoại: ");
        sdt = sc.nextLine();
    }
    public String getHoVaTen() {
        return hovaten;
    }
    public int getTuoi() {
        return tuoi;
    }
    public String getSDT() {
        return sdt;
    }
}
