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

    public QuanLyNhanVien() {
        inputData();
    }

    @Override public void inputData() {
        if(ds1.getDataChange()) {
            String xacnhan;
            System.out.println("Hanh dong nay se xoa du lieu cu!!!");
            System.out.print("Nhan 'y' de xac nhan, 'n' de huy: ");
            do {
                xacnhan = sc.nextLine();
                switch (xacnhan) {
                    case "n":
                        return;
                    case "y":
                        break;
                    default:
                        System.out.println("Vui long nhan 'y' hoac 'n'!");
                }
            } while(!xacnhan.toLowerCase().equals("y") && !xacnhan.toLowerCase().equals("n"));
        }
        try {
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataNhanVien.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                //Tạo ds2, ghi dữ liệu vào ds2 trước, nếu không lỗi mới ghi vào ds1
                DanhSachNhanVien ds2 = new DanhSachNhanVien();
                while(line != null) {
                    // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    NhanVien temp = new NhanVien(arr[0], arr[1], Integer.parseInt(arr[2]), arr[3], arr[4], Double.parseDouble(arr[5]));
                    //Kiểm tra xem trong danh sách nhập có mã nhân viên nào bị trùng không
                    NhanVien checkNV = ds2.timkiem(temp.getMaNV());
                    if(checkNV != null) {
                        System.out.println("Ma nhan vien " + temp.getMaNV() + " trong file bi trung lap!");
                        System.out.println("Vui long kiem tra lai du lieu trong file data!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        return;
                    }
                    ds2.them(temp);
                    //Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if(Integer.parseInt(temp.getMaNV().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaNV().substring(2));
                    }
                    line = input.readLine();
                }
                //thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID + 1);
                ds1 = ds2;
                System.out.println("Tai du lieu tu file thanh cong!!!");
                ds1.setDataChange(false);
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
                    ds1.setDataChange(false);
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
            System.out.println("0. Luu va thoat");
            System.out.print("Nhap chuc nang: ");
            boolean nhapThanhCong = false;
            do {
                //bắt lỗi người dùng nhập chữ
                try {
                    chucnang = sc.nextInt();
                    nhapThanhCong = true;
                    sc.nextLine(); //Xoá kí tự enter trong buffer
                } catch (InputMismatchException e) {
                    System.err.println("Vui long nhap so!!!");
                    sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                }
            } while(!nhapThanhCong);
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
                    if(ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachNVmini();
                        System.out.print("Nhap ma nhan vien can xoa: ");
                        String ma = sc.nextLine();
                        ds1.xoa(ma);
                        
                    }
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 5: {
                    if(ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachNVmini();
                        ds1.sua();
                    }
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                    
                case 6: {
                    if(ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachNVmini();
                        System.out.print("Nhap ma nhan vien can tim(nhan enter de bo qua): ");
                        String ma = sc.nextLine();
                        System.out.print("Nhap ho va ten nhan vien can tim(nhan enter de bo qua): ");
                        String hovaten = sc.nextLine();
                        System.out.print("Nhap so dien thoai nhan vien can tim(nhan enter de bo qua): ");
                        String sdt = sc.nextLine();
                        System.out.print("Nhap chuc vu nhan vien can tim(nhan enter de bo qua): ");
                        String chucvu = sc.nextLine();
                        DanhSachNhanVien kqtimkiem = ds1.timkiemnangcao(ma, hovaten, sdt, chucvu);
                        kqtimkiem.bolocKetqua(kqtimkiem);
                    }
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
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
                    outputData();
                    break;
                default: {
                    System.out.println("Vui long nhap dung chuc nang!!!");
                    System.out.println("Nhan enter de nhap lai!!!");
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
