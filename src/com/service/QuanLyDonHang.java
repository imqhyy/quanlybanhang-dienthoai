package com.service;

import com.list.DanhSachDonHang;
import com.model.DonHang;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class QuanLyDonHang implements serviceInterface.IMenu, serviceInterface.ILoadSaveData {
    DanhSachDonHang ds1 = new DanhSachDonHang();
    protected static final Scanner sc = new Scanner(System.in);

    public QuanLyDonHang() {
        inputData();
    }

    @Override public void inputData() {
        if(ds1.xuatN() != 0) {
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
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataDonHang.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                //Tạo ds2 ghi dữ liệu vào ds2 trước nếu không có lỗi mới ghi vào ds1
                DanhSachDonHang ds2 = new DanhSachDonHang();
                while (line != null) {
                    // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    DonHang temp = new DonHang(
                            arr[0], //mã đơn hàng
                            arr[1], //ngày đặt
                            arr[2], //mã khách hàng
                            arr[3], //mã nhân viên
                            Integer.parseInt(arr[4]), //số lượng sản phẩm
                            arr[5] //data sản phẩm VD: SP1&3|SP2&2
                    );
                    ds2.them(temp);
                    //Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if(Integer.parseInt(temp.getMaDH().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaDH().substring(2));
                    }
                    line = input.readLine();
                }
                //thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID + 1);
                ds1 = ds2;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Khong tim thay file!!!");
        }
    }

    @Override public void outputData() {
        try {
            try(FileWriter fw = new FileWriter("src/com/repository/dataDonHang.txt")) {
                String dulieu = ds1.xuatChuoi();
                if(dulieu == null) {
                    System.out.println("Khong co gi de ghi vao file!!!");
                } else {
                    fw.write(dulieu);
                    System.out.println("Ghi du lieu vao file thanh cong!!!");
                }
            }
        } catch(IOException e) {
            System.err.println("Khong tim thay file de ghi!!!");
        }
    }

    @Override public void menu() {
        int chucnang = 0;
        do {
            clearScreen();
            System.out.println("--- QUAN LY DON HANG ---");
            System.out.println("1. Xem danh sach don hang");
            System.out.println("2. Nhap danh sach moi");
            System.out.println("3. Them don hang");
            System.out.println("4. Xoa don hang");
            System.out.println("5. Sua don hang");
            System.out.println("6. Tim kiem don hang");
            System.out.println("7. Tai danh sach tu file");
            System.out.println("8. Xuat danh sach ra file");
            System.out.println("0. Thoat");
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
                    System.out.println("Nhan Enter de quay lai!!!");
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
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }

                case 4: {
                    if(ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachDHmini();
                        System.out.print("Nhap ma san pham can xoa: ");
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
                        ds1.DanhSachDHmini();
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
                        ds1.DanhSachDHmini();
                        System.out.print("Nhap ma san pham can tim(nhan enter de bo qua): ");
                        String maSP = sc.nextLine();
                        System.out.print("Nhap ten san pham can tim(nhan enter de bo qua): ");
                        String tenSP = sc.nextLine();
                        System.out.print("Nhap thuong hieu can tim(nhan enter de bo qua): ");
                        String thuonghieu = sc.nextLine();
                        DanhSachDonHang kqtimkiem = ds1.timkiemnangcao(maSP, tenSP, thuonghieu);
                        kqtimkiem.bolocKetqua(kqtimkiem);
                        
                    }
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 7: {
                    // Kiểm tra kết quả trả về của hàm
                    inputData();
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 8: {
                    outputData();
                    System.out.println("Nhan Enter de quay lai!!!");
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
        } while (chucnang != 0);
    }






    // lệnh clear console
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
