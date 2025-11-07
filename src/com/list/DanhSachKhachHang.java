package com.list;

import java.util.Scanner;
import com.model.KhachHang;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
public class DanhSachKhachHang implements listInterface.IList {
    KhachHang[] dskh;
    int n;
    protected static final Scanner sc = new Scanner(System.in);
    public DanhSachKhachHang() {
        n = 0;
        dskh = new KhachHang[0];
    }
    public DanhSachKhachHang(KhachHang[] dskh, int n) {
        this.n = n;
        this.dskh = dskh;
    }
    @Override public void nhap() {
        boolean nhapThanhCong = false;
        System.out.print("Nhap so luong khach hang: ");
        do {
            //bắt lỗi người dùng nhập chữ
            try {
                n = sc.nextInt();
                nhapThanhCong = true;
                sc.nextLine(); //Xoá kí tự enter trong buffer
            } catch (InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
            }
        } while(!nhapThanhCong);
        dskh = new KhachHang[n];
        for(int i = 0; i < n; i++) {
            System.out.println("Khach hang " + (i + 1));
            dskh[i] = new KhachHang();
            dskh[i].setInfo();
            System.out.println();
        }
    }
    @Override public void xuat() {
        System.out.println("\nDanh sach khach hang");
        //Kiểm tra xem danh sách nhân viên có rỗng không
        if(n == 0) {
            System.out.println("Khong co khach hang nao!!!");
        } else {
            for(int i = 0; i < n; i++) {
                System.out.println("\nKhach hang " + (i + 1));
                dskh[i].getInfo();
            }
        }
    }
    public String xuatChuoi() {
        //Hàm này xuất toàn bộ dữ liệu nhân viên thành dữ liệu chuỗi
        String xuatChuoi = null;
        for(int i = 0; i < n; i++) {
            String temp = String.join(",", dskh[i].getMaKH(), dskh[i].getHoVaTen(), Integer.toString(dskh[i].getTuoi()), dskh[i].getSDT());
            if(i == 0) {
                xuatChuoi = temp;
            } else {
                xuatChuoi = xuatChuoi + temp;
            }
            //Xuống dòng cho mỗi dữ liệu mới
            xuatChuoi = xuatChuoi + "\n";
        }
        //Xoá kí tự enter cuối chuỗi
        if(xuatChuoi != null && xuatChuoi.length() > 0) {
            xuatChuoi = xuatChuoi.substring(0, xuatChuoi.length() - 1);
        }
        return xuatChuoi;
    }
    @Override public void them() {
        dskh = Arrays.copyOf(dskh, n + 1);
        dskh[n] = new KhachHang();
        dskh[n].setInfo();
        n++;
    }
    public void them(KhachHang a) {
        dskh = Arrays.copyOf(dskh, n + 1);
        dskh[n] = new KhachHang();
        dskh[n] = a;
        n++;
    }
    @Override public void xoa(String ma) {
        for(int i = 0; i < n; i++) {
            if(dskh[i].getMaKH().equals(ma)) {
                for(int j = i; j < n - 1; j++) {
                    dskh[j] = dskh[j + 1];
                }
                dskh = Arrays.copyOf(dskh, n - 1);
                n--;
                break;
            }
        }
    }
    public KhachHang timkiem(String ma) {
        for(int i = 0; i < n; i++) {
            if(dskh[i].getMaKH().equals(ma)) {
                return dskh[i];
            }
        }
        return null;
    }
    @Override public void sua() {
        String ma;
        System.out.print("Nhap ma khach hang can sua: ");
        ma = sc.nextLine();
        boolean suaThanhCong = false;
        for(int i = 0; i < n; i++) {
            if(dskh[i].getMaKH().equals(ma)) {
                int chucnang = 0;
                do { 
                    clearScreen();
                    System.out.println("1. Sua ma khach hang");
                    System.out.println("2. Sua ten");
                    System.out.println("3. Sua tuoi");
                    System.out.println("4. Sua so dien thoai");
                    System.out.println("5. Sua tat ca");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap tinh nang: ");
                    try {
                        n = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.err.println("Vui long nhap so!!!");
                        sc.nextLine(); //Xoá buffer bàn phím trước khi người dùng nhập lại
                    }
                    switch(chucnang) {
                        case 1:
                            dskh[i].setMaKH();
                            break;
                        case 2:
                            dskh[i].setHoVaTen();
                            break;
                        case 3:
                            dskh[i].setTuoi();
                            break;
                        case 4:
                            dskh[i].setSDT();
                            break;
                        case 5:
                            dskh[i].setInfo();
                            break;
                        case 0:
                            break;
                        default: {
                            System.out.println("Chuc nang khong hop le!!!");
                            System.out.println("Nhan enter de quay lai!!!");
                            sc.nextLine();
                            break;
                        }
                    }
                } while (chucnang != 0);
                suaThanhCong = true;
                break;
            }
        }
        if(!suaThanhCong) {
            System.out.println("Khong co khach hang nay!!!");
        }
        else {
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
