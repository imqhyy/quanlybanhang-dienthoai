package com.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
        Locale vietNam = Locale.of("vi", "VN");
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

    public void setTenSP() {

        do {
            System.out.print("Ten san pham: ");
            tenSP = sc.nextLine();
            if (tenSP.isBlank()) {
                System.out.println("Hay nhap gi do!!!");
            }
        } while (tenSP.isBlank());
    }

    public void setThuongHieu() {
        do {
            System.out.print("Thuong hieu: ");
            thuonghieu = sc.nextLine();
            if (thuonghieu.isBlank()) {
                System.out.println("Hay nhap gi do!!!");
            }
        } while (thuonghieu.isBlank());
    }

    public void setGiaBan() {
        boolean nhapThanhCong = false;
        do {
            System.out.print("Gia ban: ");
            // bắt lỗi nếu người dùng nhập chữ
            try {
                giaBan = new BigDecimal(sc.nextLine());
                if (giaBan.doubleValue() <= 0) {
                    System.out.println("Gia ban phai lon hon 0!!!");
                    System.out.println("Vui long nhap lai!!!");
                } else
                    nhapThanhCong = true;
            } catch (NumberFormatException e) {
                System.err.println("Vui long nhap so!!!");
            }

        } while (!nhapThanhCong);

    }

    public void setChipset() {

        do {
            System.out.print("Chipset: ");
            chipset = sc.nextLine();
            if (chipset.isBlank()) {
                System.out.println("Hay nhap gi do!!!");
            }
        } while (chipset.isBlank());
    }

    public void setRam() {
        do {
            System.out.print("Ram: ");
            ram = sc.nextLine();
            if (ram.isBlank()) {
                System.out.println("Hay nhap gi do!!!");
            }
        } while (ram.isBlank());
    }

    public void setRom() {
        do {
            System.out.print("Rom: ");
            rom = sc.nextLine();
            if (rom.isBlank()) {
                System.out.println("Hay nhap gi do!!!");
            }
        } while (rom.isBlank());
    }

    public void setManHinh() {

        do {
            System.out.print("Man hinh: ");
            manhinh = sc.nextLine();
            if (manhinh.isBlank()) {
                System.out.println("Hay nhap gi do!!!");
            }
        } while (manhinh.isBlank());
    }

    public void setChiTiet() {

        do {
            System.out.print("Chi tiet: ");
            chitiet = sc.nextLine();
            if (chitiet.isBlank()) {
                System.out.println("Hay nhap gi do!!!");
            }
        } while (chitiet.isBlank());
    }
    // endregion

    // phương thức get tất cả thuộc tính
    public void getInfo() {
        // 1. Định nghĩa mẫu định dạng (#,###) và Locale
        // Locale Việt Nam đảm bảo sử dụng dấu chấm phân cách hàng nghìn.
        Locale vietNam = Locale.of("vi", "VN");
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

    // phương thức set tất cả thuộc tính
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
