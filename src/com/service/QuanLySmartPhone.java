package com.service;

import com.list.DanhSachSmartphone;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import com.model.SmartPhone;
import java.io.FileWriter;
import java.util.InputMismatchException;

public class QuanLySmartPhone {
    DanhSachSmartphone ds1 = new DanhSachSmartphone();
    protected static final Scanner sc = new Scanner(System.in);

    public boolean inputData() {
        try {
            BufferedReader input = new BufferedReader(new FileReader("dataSmartPhone.txt"));
            String line = input.readLine();
            while (line != null) {
                // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                String[] arr = line.split(",");

                // Khởi tạo SmartPhone với 9 tham số
                SmartPhone temp = new SmartPhone(
                        arr[0], // maSP
                        arr[1], // tenSP
                        arr[2], // thuonghieu
                        Double.parseDouble(arr[3]), // giaBan
                        arr[4], // chipset
                        arr[5], // ram
                        arr[6], // rom
                        arr[7], // manhinh
                        arr[8] // chitiet
                );

                ds1.them(temp);
                line = input.readLine();
            }

            input.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Khong tim thay file dataSmartPhone.txt!!!");
            return false;
        }
    }

    public void outputData() {
        try {
            FileWriter fw = new FileWriter("dataSmartPhone.txt");
            String dulieu = ds1.xuatChuoi();

            if (dulieu == null) {
                System.out.println("Khong co du lieu smartphone de ghi vao file!!!");
            } else {
                fw.write(dulieu);
                System.out.println("Ghi du lieu vao file thanh cong!!!");
            }

            fw.close();

        } catch (Exception e) {

        }
    }

    public void menu() {
        int chucnang = 0;
        do {
            clearScreen();
            System.out.println("--- QUAN LY SMARTPHONE ---");
            System.out.println("1. Xem danh sach smartphone");
            System.out.println("2. Them smartphone");
            System.out.println("3. Xoa smartphone");
            System.out.println("4. Sua smartphone");
            System.out.println("5. Tim kiem smartphone");
            System.out.println("6. Tai danh sach tu file");
            System.out.println("7. Xuat danh sach ra file");
            System.out.println("0. Thoat");
            System.out.print("Nhap chuc nang: ");
            try {
                chucnang = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                // Xoá buffer trước khi người dùng nhập lại
                sc.nextLine();
            }

            switch (chucnang) {
                case 1: {
                    ds1.xuat();
                    System.out.println("\nNhan Enter de thoat!!!");
                    String ma = sc.nextLine();
                    break;
                }

                case 2: {
                    ds1.them();
                    // Thông báo "Them thanh cong!!!" đã có trong hàm them()
                    System.out.println("\nNhan Enter de thoat!!!");
                    String ma = sc.nextLine();
                    break;
                }

                case 3: {
                    System.out.print("Nhap ma smartphone can xoa: ");
                    String ma = sc.nextLine();
                    ds1.xoa(ma);
                    // Thông báo "Xoa thanh cong!!!" đã có trong hàm xoa()
                    System.out.println("\nNhan Enter de thoat!!!");
                    ma = sc.nextLine();
                    break;
                }
                case 4: {
                    ds1.sua();
                    System.out.println("\nNhan Enter de thoat!!!");
                    String ma = sc.nextLine();
                    break;
                }

                case 5: {
                    System.out.print("Nhap ma smartphone can tim: ");
                    String ma = sc.nextLine();
                    SmartPhone ketquaTimKiem = ds1.timkiem(ma);
                    if (ketquaTimKiem == null) {
                        System.out.println("Khong tim thay smartphone!!!");

                        
                    } else {
                        ketquaTimKiem.getInfo();
                    }
                    System.out.println("\nNhan Enter de thoat!!!");
                    ma = sc.nextLine();
                    break;
                }
                case 6: {
                    // Kiểm tra kết quả trả về của hàm
                    if (inputData()) {
                        System.out.println("Tai du lieu thanh cong!");
                    } else {
                        System.out.println("Tai du lieu THAT BAI!");
                    }

                    System.out.println("\nNhan Enter de thoat!!!");
                    String ma = sc.nextLine();
                    break;
                }
                case 7: {
                    outputData();
                    System.out.println("\nNhan Enter de thoat!!!");
                    String ma = sc.nextLine();
                    break;
                }
                case 0:
                    break;
                default: {
                    System.out.println("Vui long nhap dung chuc nang!!!");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
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
        } catch (final Exception e) {
            // Xử lý lỗi nếu không thể chạy lệnh (ví dụ: bị hạn chế quyền)
            System.out.println("\n(Không thể xóa màn hình)\n");
        }
    }
}