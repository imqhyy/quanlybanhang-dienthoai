package com.model;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class ConNguoi {
    protected String hovaten;
    protected int tuoi;
    protected  String sdt;
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
        do {
            System.out.print("Ho va ten: ");
            hovaten = sc.nextLine();
            if (hovaten.isBlank()) {
                System.out.println("Hay nhap gi do!!!");
            }
        } while (hovaten.isBlank());
    }
    public void setTuoi() {
        boolean nhapThanhCong = false;
        do {
            System.out.print("Tuoi: ");
            //tránh người dùng nhập chữ vào trường nhập liệu số
            try {
                tuoi = sc.nextInt();
                sc.nextLine();
                if(tuoi <= 0 || tuoi >= 100 ) {
                    System.out.println("Tuoi khong hop le!!!");
                    System.out.println("Tuoi phai lon hon 0 va be hon 100");
                    System.out.println("Vui long nhap lai!!!");
                } else
                    nhapThanhCong = true;
            } catch(InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                //Xoá hết những ký tự còn lại trong buffer trước khi nhập liệu mới
                sc.nextLine();
            }
        } while(!nhapThanhCong);
        
    }
    public void setSDT() {
        System.out.print("So dien thoai: ");
        boolean nhapThanhCong = false;
        do {
            sdt = sc.nextLine();
            if(sdt.matches("\\d+")) {
                /**phương thức matches() của lớp String sẽ kiểm tra xem toàn bộ chuỗi
                    có khớp với biểu thức chính quy đã cho hay không, biểu thức "\\d+"
                    đảm bảo rằng chuỗi phải chứa ít nhất một ký tự, và tất cả các ký 
                    tự đều là chữ số(0-9)
                */
                nhapThanhCong = true;
            }
            else {
                System.out.println("Chi nhap so!!!");
                System.out.print("So dien thoai: ");
            }
        } while(!nhapThanhCong);
    }
    
}
