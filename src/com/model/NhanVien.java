package com.model;

public class NhanVien extends ConNguoi {
    private String maNV;
    private String chucvu;
    private double luong;
    public static int soLuong = 0;
    public NhanVien() {
        super();
        maNV = "null";
        chucvu = "null";
        luong = 0;
    }
    public NhanVien(String hovaten, int tuoi, String sdt, String maNV, String chucvu, double luong) {
        super(hovaten, tuoi, sdt);
        this.maNV = maNV;
        this.chucvu = chucvu;
        this.luong = luong;
    }
    @Override public void getInfo() {
        System.out.println("------Thong tin nhan vien------");
        System.out.println("Ma nhan vien: " + maNV);
        System.out.println("Ho va ten: " + super.getHoVaTen());
        System.out.println("Tuoi: " + super.getTuoi());
        System.out.println("So dien thoai: " + super.getSDT());
        System.out.println("Chuc vu: " + chucvu);
        System.out.println("Luong: " + luong);
    }
    @Override public void setInfo() {
        System.out.println("------Nhap thong tin nhan vien------");
        System.out.print("Ma nhan vien: ");
        maNV = sc.nextLine();
        super.setHoVaTen();
        super.setTuoi();
        super.setSDT();
        System.out.print("Chuc vu: ");
        chucvu = sc.nextLine();
        System.out.print("Luong: ");
        luong = sc.nextDouble();
    }
    public String getMaNV() {
        return maNV;
    }
    public String getChucVu() {
        return chucvu;
    }
    public double getLuong() {
        return luong;
    }
    public void setMaNV() {
        System.out.print("Ma nhan vien: ");
        maNV = sc.nextLine();
    }
    public void setChucVu() {
        System.out.print("Chuc vu: ");
        chucvu = sc.nextLine();
    }
    public void setLuong() {
        System.out.print("Luong: ");
        luong = sc.nextDouble();
    }
}
