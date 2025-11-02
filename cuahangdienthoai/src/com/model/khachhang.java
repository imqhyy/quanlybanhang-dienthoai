package com.model;

public class KhachHang extends ConNguoi {
    String ID;
    public KhachHang() {
        super();
        ID = "null";
    }
    public KhachHang(String hovaten, int tuoi, String sdt, String ID) {
        super(hovaten, tuoi, sdt);
        this.ID = ID;
    }
    

}
