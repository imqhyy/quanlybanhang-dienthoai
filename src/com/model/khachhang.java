package com.model;

public class khachHang extends conNguoi {
    String ID;
    public khachHang() {
        super();
        ID = "null";
    }
    public khachHang(String hovaten, int tuoi, String sdt, String ID) {
        super(hovaten, tuoi, sdt);
        this.ID = ID;
    }
    

}
