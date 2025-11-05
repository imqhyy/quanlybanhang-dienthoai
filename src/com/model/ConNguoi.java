package com.model;
import java.util.InputMismatchException;
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
    

    public String getHoVaTen() {
        return hovaten;
    }
    public int getTuoi() {
        return tuoi;
    }
    public String getSDT() {
        return sdt;
    }

    public void setHoVaTen() {
        System.out.print("Ho va ten: ");
        hovaten = sc.nextLine();
    }
    public void setTuoi() {
        boolean nhapThanhCong = false;
        do {
            System.out.print("Tuoi: ");
            //tránh người dùng nhập chữ vào trường nhập liệu số
            try {
                tuoi = sc.nextInt();
                nhapThanhCong = true;
                sc.nextLine();
            } catch(InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                //Xoá hết những ký tự còn lại trong buffer trước khi nhập liệu mới
                sc.nextLine();
            }
        } while(!nhapThanhCong);
        
    }
    public void setSDT() {
        System.out.print("So dien thoai: ");
        sdt = sc.nextLine(); 
    }
    
}
