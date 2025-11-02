package com.model;

public class DienThoai {
    private String maSP;
    private String tenSP;
    private double giaBan;

    public DienThoai() {
    }

    public DienThoai(String maSP, String tenSP, double giaBan) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        return "DienThoai{" + "maSP='" + maSP + '\'' + ", tenSP='" + tenSP + '\'' + ", giaBan=" + giaBan + '}';
    }

    // Xây dựng các phương thức getter - setter
    public String getmaSP() {
        return this.maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
}
