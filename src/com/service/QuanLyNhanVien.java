package com.service;

import com.list.DanhSachNhanVien;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import com.model.NhanVien;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class QuanLyNhanVien implements serviceInterface.IMenu, serviceInterface.ILoadSaveData {
    DanhSachNhanVien ds1 = new DanhSachNhanVien();
    protected static final Scanner sc = new Scanner(System.in);
    @Override public void inputData() {
        try {
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataNhanVien.txt"))) {
                String line = input.readLine();
                while(line != null) {
                    // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    NhanVien temp = new NhanVien(arr[0], arr[1], Integer.parseInt(arr[2]), arr[3], arr[4], Double.parseDouble(arr[5]));
                    ds1.them(temp);
                    line = input.readLine();
                }
                System.out.println("Tai du lieu tu file thanh cong!!!");
            }
            
            
            
        } catch (IOException | NumberFormatException e) {
            System.err.println("Khong tim thay file!!!");
        }
    }
    
    @Override public void outputData() {
        try {
            try (FileWriter fw = new FileWriter("src/com/repository/dataNhanVien.txt")) {
                String dulieu = ds1.xuatChuoi();
                if(dulieu == null) {
                    System.out.println("Khong co du lieu de ghi vao file!!!");
                } else {
                    fw.write(dulieu);
                    System.out.println("Ghi du lieu vao file thanh cong!!!");
                }
            }

        } catch(IOException e) {
            System.err.println("Khong tim thay file de ghi vao file!!!");
        }
    }
    @Override public void menu() {
        int chucnang = 0;
        do {
            clearScreen();
            System.out.println("1. Xem danh sach nhan vien");
            System.out.println("2. Nhap danh sach moi");
            System.out.println("3. Them nhan vien");
            System.out.println("4. Xoa nhan vien");
            System.out.println("5. Sua nhan vien");
            System.out.println("6. Tim kiem");
            System.out.println("7. Tai danh sach tu file");
            System.out.println("8. Xuat danh sach ra file");
            System.out.println("0. Thoat");
            System.out.print("Nhap chuc nang: ");
            try {
                chucnang = sc.nextInt();
                sc.nextLine();
            } catch(InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                //Xoá buffer trước khi người dùng nhập lại
                sc.nextLine();
            }
            switch (chucnang) {
                case 1: {
                    ds1.xuat();
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 2: {
                    ds1.nhap();
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 3: {
                    ds1.them();
                    System.out.println("Them thanh cong!!!");
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break; 
                }
                             
                case 4: {
                    System.out.print("Nhap ma nhan vien can xoa: ");
                    String ma = sc.nextLine();
                    ds1.xoa(ma);
                    System.out.println("Xoa thanh cong!!!");
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 5: {
                    ds1.sua();
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                    
                case 6: {
                    System.out.print("Nhap ma nhan vien can tim: ");
                    String ma = sc.nextLine();
                    NhanVien ketquaTimKiem = ds1.timkiem(ma);
                    if(ketquaTimKiem == null) {
                        System.out.println("Khong tim thay nhan vien!!!");
                    } else {
                        ketquaTimKiem.getInfo();
                    }
                    break;
                }
                case 7: {
                    inputData();
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 8: {
                    outputData();
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 0:
                    break;
                default: {
                    System.out.println("Vui long nhap dung chuc nang!!!");
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                }
                    
            }
        } while(chucnang != 0);
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
        } catch (final Exception e) {
            // Xử lý lỗi nếu không thể chạy lệnh (ví dụ: bị hạn chế quyền)
            System.out.println("\n(Không thể xóa màn hình)\n"); 
        }
    }
}
