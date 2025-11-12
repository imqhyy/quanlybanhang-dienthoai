package com.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class SmartPhone {
    private String maSP;
    private String tenSP;
    private String thuonghieu;
    private BigDecimal giaBan;
    private String chipset;
    private String ram;
    private String rom;
    private String manhinh;
    private String chitiet; // mô tả chi tiết thông số camera và 1 số đặc điểm khác
    private static final Scanner sc = new Scanner(System.in);

    public SmartPhone() {
        maSP = "null";
        tenSP = "null";
        thuonghieu = "null";
        giaBan = new BigDecimal(0);
        chipset = "null";
        ram = "null";
        rom = "null";
        manhinh = "null";
        chitiet = "null";
    }

    public SmartPhone(String maSP, String tenSP, String thuonghieu, BigDecimal giaBan, String chipset, String ram,
        String rom, String manhinh, String chitiet) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.thuonghieu = thuonghieu;
        this.giaBan = giaBan;
        this.chipset = chipset;
        this.ram = ram;
        this.rom = rom;
        this.manhinh = manhinh;
        this.chitiet = chitiet;
    }

    // region phương thức get riêng lẻ
    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getThuongHieu() {
        return thuonghieu;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public String getStringGiaBan() {
        // 1. Định nghĩa mẫu định dạng (#,###) và Locale
        // Locale Việt Nam đảm bảo sử dụng dấu chấm phân cách hàng nghìn.
        Locale vietNam = new Locale("vi", "VN");
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(vietNam);

        // 2. Đặt mẫu (Pattern) thủ công:
        // " VND" là tiền tố/hậu tố.
        df.applyPattern("#,### VND");
        return df.format(giaBan);
    }

    public String getChipset() {
        return chipset;
    }

    public String getRam() {
        return ram;
    }

    public String getRom() {
        return rom;
    }

    public String getManHinh() {
        return manhinh;
    }

    public String getChiTiet() {
        return chitiet;
    }
    // endregion

    // region phương thức set riêng lẻ
    public void setMaSP() {
        System.out.print("Ma san pham: ");
        maSP = sc.nextLine();
    }

    public void setTenSP() {
        System.out.print("Ten san pham: ");
        tenSP = sc.nextLine();
    }

    public void setThuongHieu() {
        System.out.print("Thuong hieu: ");
        thuonghieu = sc.nextLine();
    }

    public void setGiaBan() {
        boolean nhapThanhCong = false;
        do {
            System.out.print("Gia ban: ");
            // bắt lỗi nếu người dùng nhập chữ
            try {
                int inputGiaban = sc.nextInt();
                sc.nextLine();
                giaBan = new BigDecimal(inputGiaban);
                nhapThanhCong = true;
            } catch (InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                sc.nextLine(); // xoá buffer trước khi người dùng nhập lại
            }

        } while (!nhapThanhCong);

    }

    public void setChipset() {
        System.out.print("Chipset: ");
        chipset = sc.nextLine();
    }

    public void setRam() {
        System.out.print("Ram: ");
        ram = sc.nextLine();
    }

    public void setRom() {
        System.out.print("Rom: ");
        rom = sc.nextLine();
    }

    public void setManHinh() {
        System.out.print("Man hinh: ");
        manhinh = sc.nextLine();
    }

    public void setChiTiet() {
        System.out.print("Chi tiet: ");
        chitiet = sc.nextLine();
    }
    // endregion

    //phương thức get tất cả thuộc tính
    public void getInfo() {
        // 1. Định nghĩa mẫu định dạng (#,###) và Locale
        // Locale Việt Nam đảm bảo sử dụng dấu chấm phân cách hàng nghìn.
        Locale vietNam = new Locale("vi", "VN");
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(vietNam);

        // 2. Đặt mẫu (Pattern) thủ công:
        // " VND" là tiền tố/hậu tố.
        df.applyPattern("#,### VND");

        System.out.println("------Thong tin dien thoai------");
        System.out.println("Ma san pham: " + maSP);
        System.out.println("Ten san pham: " + tenSP);
        System.out.println("Thuong hieu: " + thuonghieu);
        System.out.println("Gia ban: " + getStringGiaBan());
        System.out.println("------Cau hinh dien thoai------");
        System.out.println("Chipset: " + chipset);
        System.out.println("Ram: " + ram);
        System.out.println("Rom: " + rom);
        System.out.println("Man hinh: " + manhinh);
        System.out.println("Chi tiet: " + chitiet);
    }

    //phương thức set tất cả thuộc tính
    public void setInfo() {
        System.out.println("------Nhap thong tin dien thoai------");
        System.out.println("Ma san pham: " + maSP);
        setTenSP();
        setThuongHieu();
        setGiaBan();
        System.out.println("------Nhap cau hinh san pham------");
        setChipset();
        setRam();
        setRom();
        setChiTiet();
    }
    public void setInfo(int seedID) {
        System.out.println("------Nhap thong tin dien thoai------");
        String ma = "SP";
        ma = ma + Integer.toString(seedID);
        maSP = ma;
        System.out.println("Ma san pham: " + maSP);
        setTenSP();
        setThuongHieu();
        setGiaBan();
        System.out.println("------Nhap cau hinh san pham------");
        setChipset();
        setRam();
        setRom();
        setChiTiet();
    }

}
