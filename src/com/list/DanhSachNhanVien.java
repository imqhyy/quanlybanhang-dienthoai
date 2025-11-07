package com.list;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.model.NhanVien;
import java.io.IOException;


public class DanhSachNhanVien implements listInterface.IList {
    NhanVien[] dsnv;
    int n;
    protected static final Scanner sc = new Scanner(System.in);
    public DanhSachNhanVien() {
        n = 0;
        dsnv = new NhanVien[0];
    }
    public DanhSachNhanVien(NhanVien[] dsnv, int n) {
        this.dsnv = dsnv;
        this.n = n;
    }
    @Override public void nhap() {
        boolean nhapThanhCong = false;
        System.out.print("\nNhap so luong nhan vien: ");
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
            System.out.print("Nhan vien " + (i+1));
            dsnv[i] = new NhanVien();
            dsnv[i].setInfo();
            System.out.println();
        }
    }
    @Override public void xuat() {
        System.out.println("\nDanh sach nhan vien");
        if(n == 0) {
            System.out.println("Khong co nhan vien nao!!!");
        } else {
            for(int i = 0; i < n; i++) {
                System.out.println("\nNhan vien " + (i + 1));
                dsnv[i].getInfo();
            }
        }
    }
    public String xuatChuoi() {
        //Hàm này xuất dữ liệu toàn bộ nhân viên thành chuỗi
        String xuatChuoi = null;
        for(int i = 0; i < n; i++) {
            String temp = String.join(",", dsnv[i].getMaNV(), dsnv[i].getHoVaTen(), Integer.toString(dsnv[i].getTuoi()), dsnv[i].getSDT(), dsnv[i].getChucVu(), Double.toString(dsnv[i].getLuong()));
            if(i == 0) xuatChuoi = temp;
            else {
                xuatChuoi = xuatChuoi + temp;
            }
            
            //Xuống dòng với mỗi dữ liệu nhân viên mới
            xuatChuoi = xuatChuoi + "\n";
        }
        //Xoá ký tự enter cuối chuỗi
        if(xuatChuoi != null && xuatChuoi.length() > 0 ) {
            xuatChuoi = xuatChuoi.substring(0, xuatChuoi.length() - 1);
        }
        return xuatChuoi;
    }
    @Override public void them() {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = new NhanVien();
        dsnv[n].setInfo();
        n++;
    }
    public void them(NhanVien a) {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = new NhanVien();
        dsnv[n] = a;
        n++;
    }
    @Override public void xoa(String ma) {
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV().equals(ma)) {
                for(int j = i; j < n - 1; j++) {
                    dsnv[j] = dsnv[j + 1];
                }
                dsnv = Arrays.copyOf(dsnv, n - 1);
                n--;
                break;
            }
        }
    }
    public NhanVien timkiem(String ma) {
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV().equals(ma)) {
                return dsnv[i];
            }
        }
        return null;
    }
    @Override public void sua() {
        String ma;
        System.out.print("Nhap ma nhan vien can sua: ");
        ma = sc.nextLine();
        boolean suaThanhCong = false;
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV().equals(ma)) {
                int chucnang = 0;
                do {
                    clearScreen();
                    System.out.println("1. Sua ma nhan vien");
                    System.out.println("2. Sua ten");
                    System.out.println("3. Sua tuoi");
                    System.out.println("4. Sua so dien thoai");
                    System.out.println("5. Sua chuc vu");
                    System.out.println("6. Sua luong");
                    System.out.println("7. Sua tat ca");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap tinh nang: ");
                    try{
                        chucnang = sc.nextInt();
                        sc.nextLine();
                    } catch(InputMismatchException e) {
                        System.err.println("Vui long nhap so!!!");
                        //Xoá buffer trước khi người dùng nhập lại
                        sc.nextLine();
                    }
                    switch (chucnang) {
                        case 1:
                            dsnv[i].setMaNV();
                            break;
                        case 2:
                            dsnv[i].setHoVaTen();
                            break;
                        case 3:
                            dsnv[i].setTuoi();
                            break;
                        case 4:
                            dsnv[i].setSDT();
                            break;
                        case 5:
                            dsnv[i].setChucVu();
                            break;
                        case 6: 
                            dsnv[i].setLuong();
                            break;
                        case 7:
                            dsnv[i].setInfo();
                            break;
                        case 0:
                            break;
                        default: {
                            System.out.println("Chuc nang khong hop le!!!");
                            System.out.println("Nhan enter de quay lai!!!");
                            sc.nextLine();
                        }
                            
                    }
                } while(chucnang != 0);
                suaThanhCong = true;
                break;
            }
        }
        if(!suaThanhCong) {
            System.out.println("Khong co nhan vien nay!!!");
        } else {
            System.out.println("Sua thanh cong!!!");
        }
    }


    //lệnh clear console
    public static void clearScreen() {
        try {
            // Lựa chọn lệnh dựa trên hệ điều hành
            final String os = System.getProperty("os.name");
            
            if (os.contains("Windows")) {
                // Đối với Windows, dùng 'cls'
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Đối với Linux/macOS, dùng 'clear'
                // Hoặc dùng chuỗi escape code (ít phụ thuộc vào lệnh 'clear')
                System.out.print("\033[H\033[2J");
                System.out.flush(); 
            }
        } catch (final IOException | InterruptedException e) {
            // Xử lý lỗi nếu không thể chạy lệnh (ví dụ: bị hạn chế quyền)
            System.out.println("\n(Không thể xóa màn hình)\n"); 
        }
    }
}
