package com.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Locale;

public class NhanVien extends ConNguoi {
    private String maNV;
    private String chucvu;
    private BigDecimal luong;
    public NhanVien() {
        super();
        maNV = "null";
        chucvu = "null";
        luong = new BigDecimal(0);
    }
    public NhanVien(String maNV, String hovaten, int tuoi, String sdt, String chucvu, BigDecimal luong) {
        super(hovaten, tuoi, sdt);
        this.maNV = maNV;
        this.chucvu = chucvu.toLowerCase();
        this.luong = luong;
    }
    @Override public void getInfo() {
        System.out.println("\n------Thong tin nhan vien------");
        System.out.println("Ma nhan vien: " + maNV);
        System.out.println("Ho va ten: " + super.getHoVaTen());
        System.out.println("Tuoi: " + super.getTuoi());
        System.out.println("So dien thoai: " + super.getSDT());
        System.out.println("Chuc vu: " + chucvu);
        System.out.println("Luong: " + getStringLuong());
    }
    @Override public void setInfo() {
        System.out.println("\n------Nhap thong tin nhan vien------");
        System.out.println("Ma nhan vien: " + maNV);
        super.setHoVaTen();
        this.setTuoi();
        super.setSDT();
        setChucVu();
        setLuong();
    }
    public void setInfo(int seedID) {
        System.out.println("\n------Nhap thong tin nhan vien------");
        String ma = "NV";
        ma = ma + Integer.toString(seedID);
        maNV = ma;
        System.out.println("Ma nhan vien: " + maNV);
        super.setHoVaTen();
        setTuoi();
        super.setSDT();
        setChucVu();
        setLuong();
    }
    public String getMaNV() {
        return maNV;
    }
    public String getChucVu() {
        return chucvu;
    }
    public BigDecimal getLuong() {
        return luong;
    }
    public String getStringLuong() {
        // 1. Định nghĩa mẫu định dạng (#,###) và Locale
        // Locale Việt Nam đảm bảo sử dụng dấu chấm phân cách hàng nghìn.
        Locale vietNam = Locale.of("vi", "VN");
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(vietNam);

        // 2. Đặt mẫu (Pattern) thủ công:
        // " VND" là tiền tố/hậu tố.
        df.applyPattern("#,### VND");
        return df.format(luong);
    }

    
    @Override public void setTuoi() {
        boolean nhapThanhCong = false;
        do {
            System.out.print("Tuoi: ");
            //tránh người dùng nhập chữ vào trường nhập liệu số
            try {
                tuoi = sc.nextInt();
                sc.nextLine();
                if(tuoi < 18 || tuoi > 32 ) {
                    System.out.println("Tuoi khong hop le!!!");
                    System.out.println("Nhan vien phai co do tuoi tu 18 den 32 tuoi!!!");
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

    public void setChucVu() {
        System.out.print("Chuc vu: ");
        chucvu = sc.nextLine().toLowerCase();
    }
    public void setLuong() {
        boolean nhapThanhCong = false;
        do {
            System.out.print("Luong: ");
            //bắt lỗi nếu người dùng nhập chữ
            try {
                luong = new BigDecimal(sc.nextLine());
                if(luong.doubleValue() <= 0) {
                    System.out.println("Luong phai lon hon 0!!!");
                    System.out.println("Vui long nhap lai!!!");
                } else
                    nhapThanhCong = true;
            } catch(NumberFormatException e) {
                System.err.println("Vui long nhap so!!!");
            }
        } while(!nhapThanhCong);
        
    }
}
