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
        System.out.println("------Thông tin nhân viên------");
        System.out.println("Mã nhân viên: " + maNV);
        System.out.println("Họ và tên: " + super.getHoVaTen());
        System.out.println("Tuổi: " + super.getTuoi());
        System.out.println("Số điện thoại: " + super.getSDT());
        System.out.println("Chức vụ: " + chucvu);
        System.out.println("Lương: " + luong);
    }
    @Override public void setInfo() {
        System.out.println("------Nhập thông tin nhân viên------");
        System.out.print("Mã nhân viên: ");
        maNV = sc.nextLine();
        super.setHoVaTen();
        super.setTuoi();
        super.setSDT();
        System.out.print("Chức vụ: ");
        chucvu = sc.nextLine();
        System.out.print("Lương: ");
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
        System.out.print("Mã nhân viên: ");
        maNV = sc.nextLine();
    }
    public void setChucVu() {
        System.out.print("Chức vụ: ");
        chucvu = sc.nextLine();
    }
    public void setLuong() {
        System.out.print("Lương: ");
        luong = sc.nextDouble();
    }
}
