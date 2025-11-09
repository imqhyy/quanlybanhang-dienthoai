package com.service;

import com.list.DanhSachDonHang;
import com.model.DonHang;

import java.io.*;
import java.time.LocalDate;
// Import them de bat loi parse ngay
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuanLyDonHang implements serviceInterface.IMenu, serviceInterface.ILoadSaveData {
    DanhSachDonHang dsDH = new DanhSachDonHang();
    protected static final Scanner sc = new Scanner(System.in);
    private static final String FILE_PATH = "src/com/repository/dataDonHang.txt";

    @Override
    // Tra ve void inputData() nhu ban dau
    public void inputData() {
        try {
            try (BufferedReader input = new BufferedReader(new FileReader(FILE_PATH))) {
                String line = input.readLine();
                while (line != null) {
                    String[] arr = line.split(",");
                    if (arr.length == 4) {
                        DonHang dh = new DonHang(arr[0], arr[1], LocalDate.parse(arr[2]), Integer.parseInt(arr[3]));
                        dsDH.them(dh);
                    }
                    line = input.readLine();
                }
                System.out.println("Tai du lieu tu file thanh cong!!!");
            }
            // Bat them loi parse ngay va loi so
        } catch (IOException | NumberFormatException | DateTimeParseException e) {
            System.err.println("Khong tim thay file hoac du lieu khong hop le!!!");
        }
    }

    @Override
    public void outputData() {
        try {
            try (FileWriter fw = new FileWriter(FILE_PATH)) {
                String data = dsDH.xuatChuoi();
                if (data == null) {
                    System.out.println("Khong co gi de ghi vao file!!!");
                } else {
                    fw.write(data);
                    System.out.println("Ghi du lieu vao file thanh cong!!!");
                }
            }
        } catch (IOException e) {
            System.err.println("Khong tim thay file de ghi!!!");
        }
    }

    @Override
    public void menu() {
        int chucnang = 0;
        do {
            clearScreen();
            System.out.println("===== QUAN LY DON HANG =====");
            System.out.println("1. Xem danh sach don hang");
            System.out.println("2. Nhap danh sach moi");
            System.out.println("3. Them don hang");
            System.out.println("4. Xoa don hang");
            System.out.println("5. Sua don hang");
            // System.out.println("6. Tim kiem");
            System.out.println("6. Tai danh sach tu file");
            System.out.println("7. Xuat danh sach ra file");
            System.out.println("0. Thoat");
            System.out.print("Nhap chuc nang: ");

            try {
                chucnang = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                sc.nextLine();
                continue;
            }
            switch (chucnang) {
                case 1: {
                    dsDH.xuat();
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 2: {
                    dsDH.nhap();
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 3: {
                    dsDH.them();
                    System.out.println("Them thanh cong!!!");
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 4: {
                    System.out.print("Nhap ma don hang can xoa: ");
                    String ma = sc.nextLine();
                    dsDH.xoa(ma);
                    System.out.println("Xoa thanh cong!!!");
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 5: {
                    dsDH.sua();
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                // case 6: {
                // ... (code tim kiem)
                // }
                case 6: {
                    inputData();
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 7: {
                    outputData();
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 0:
                    break;
                default: {
                    System.out.println("Vui long nhap dung chuc nang!!!");
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
            }

        } while (chucnang != 0);
    }

    // clear console
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
    
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("\n(Khong the xoa man hinh)\n");
        }
    }
}