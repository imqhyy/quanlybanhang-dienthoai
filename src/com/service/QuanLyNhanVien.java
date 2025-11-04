package com.service;

import java.util.Scanner;
import com.model.NhanVien;
import java.util.InputMismatchException;
public class QuanLyNhanVien {
    NhanVien[] dsnv;
    int n;
    protected static final Scanner sc = new Scanner(System.in);
    public QuanLyNhanVien() {
        n = 0;
        dsnv = new NhanVien[0];
    }
    public QuanLyNhanVien(NhanVien[] dsnv, int n) {
        this.dsnv = dsnv;
        this.n = n;
    }
    public void nhap() {
        boolean nhapThanhCong = false;
        System.out.print("Nhap so luong nhan vien: ");
        do {
            //bắt lỗi người dùng nhập chữ
            try {
                n = sc.nextInt();
                nhapThanhCong = true;
                sc.nextLine();
            } catch(InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                //xoá buffer trước khi người dùng nhập lại
                sc.nextLine();
            }
        } while(!nhapThanhCong);
        dsnv = new NhanVien[n];
        for(int i = 0; i < n; i++) {
            dsnv[i] = new NhanVien();
            dsnv[i].setInfo();
        }
    }
}
