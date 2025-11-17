package com.service;

import com.list.DanhSachKhachHang;
import com.model.KhachHang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuanLyKhachHang implements serviceInterface.IMenu, serviceInterface.ILoadSaveData {
    DanhSachKhachHang ds1 = new DanhSachKhachHang();
    private static final Scanner sc = new Scanner(System.in);

    public QuanLyKhachHang() {
        inputData();
    }

    @Override
    public final void inputData() {
        if (ds1.getDataChange()) {
            String xacnhan;
            System.out.println("Hanh dong nay se xoa du lieu cu!!!");
            do {
                System.out.print("Nhan 'y' de xac nhan, 'n' de huy: ");
                xacnhan = sc.nextLine();
                switch (xacnhan) {
                    case "n":
                        return;
                    case "y":
                        break;
                    default:
                        System.out.println("Vui long nhan 'y' hoac 'n'!");
                }
            } while (!xacnhan.toLowerCase().equals("y") && !xacnhan.toLowerCase().equals("n"));
        }
        try {
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataKhachHang.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                // Tạo ds2, ghi dữ liệu vào ds2 trước, nếu không lỗi mới ghi vào ds1
                DanhSachKhachHang ds2 = new DanhSachKhachHang();
                while (line != null) {
                    // Chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    KhachHang temp = new KhachHang(
                                arr[0], //Mã khách hàng
                                arr[1], //Họ và tên
                                Integer.parseInt(arr[2]), //Tuổi
                                arr[3] //Số điện thoại
                    );
                    
                    // Kiểm tra xem trong danh sách nhập có mã khách hàng nào bị trùng không
                    KhachHang checkKH = ds2.timkiem(temp.getMaKH());
                    if (checkKH != null) {
                        System.out.println("Ma khach hang " + temp.getMaKH() + " trong file bi trung lap!");
                        System.out.println("Vui long kiem tra lai du lieu trong file data!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        System.out.println("Nhan enter de dong thong bao nay!!!");
                        sc.nextLine();
                        return;
                    }
                    if(temp.getHoVaTen().isBlank()) {
                        System.out.println("Ma khach hang " + temp.getMaKH() + " co ten khong hop le!");
                        System.out.println("Vui long kiem tra lai du lieu trong file data!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        System.out.println("Nhan enter de dong thong bao nay!!!");
                        sc.nextLine();
                        return;
                    }
                    //Kiểm tra xem khách hàng temp có tuổi bé hơn hoặc bằng 0 không
                    if(temp.getTuoi() <= 0 || temp.getTuoi() >= 100) {
                        System.out.println("Ma khach hang " + temp.getMaKH() + " co tuoi khong hop le!");
                        System.out.println("Khach hang phai co tuoi lon hon 0 va be hon 100!");
                        System.out.println("Vui long kiem tra lai file data!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        System.out.println("Nhan enter de dong thong bao nay!!!");
                        sc.nextLine();
                        return;
                    }
                    //Kiểm tra xem số điện thoại có hợp lệ không
                    if(!temp.getSDT().matches("\\d+")) {
                        System.out.println("Ma khach hang " + temp.getMaKH() + " co so dien thoai khong hop le!");
                        System.out.println("Vui long kiem tra lai file data!!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        System.out.println("Nhan enter de thong bao nay!!!");
                        sc.nextLine();
                        return;
                    }
                    
                    ds2.them(temp);
                    // Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if (Integer.parseInt(temp.getMaKH().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaKH().substring(2));
                    }
                    line = input.readLine();
                }
                // thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID + 1);
                ds1 = ds2;
                ds1.setDataChange(false);
                System.out.println("Tai du lieu tu file dataKhachHang.txt thanh cong!!!");
                System.out.println("Nhan enter de dong thong bao nay!!!");
                sc.nextLine();
            }
            /** Ở đây có 4 lỗi có thể xảy ra
             * IOException 
             * NumberFormatException 
             * ArrayIndexOutOfBoundsException
             * FileNotFoundException
             */
        } catch (Exception e) {
            System.err.println("File data co the da bi loi!");
            System.out.println("Vui long kiem tra lai file data!!");
            System.out.println("Nhan enter de dong thong bao nay!!!");
            sc.nextLine();
        }
    }

    @Override
    public void outputData() {
        try {
            try (FileWriter fw = new FileWriter("src/com/repository/dataKhachHang.txt")) {
                String dulieu = ds1.xuatChuoi();
                if (dulieu == null) {
                    System.out.println("Khong co du lieu de ghi vao file!!!");
                } else {
                    fw.write(dulieu);
                    System.out.println("Ghi du lieu vao file thanh cong!!!");
                    ds1.setDataChange(false);
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
            System.out.println("1. Xem danh sach khach hang");
            System.out.println("2. Them nhieu khach hang");
            System.out.println("3. Them mot khach hang");
            System.out.println("4. Xoa khach hang");
            System.out.println("5. Sua khach hang");
            System.out.println("6. Tim kiem");
            System.out.println("7. Tai danh sach tu file");
            System.out.println("8. Xuat danh sach ra file");
            System.out.println("0. Thoat");
            boolean nhapThanhCong = false;
            do {
                System.out.print("Nhap chuc nang: ");
                // bắt lỗi người dùng nhập chữ
                try {
                    chucnang = sc.nextInt();
                    nhapThanhCong = true;
                    sc.nextLine(); // Xoá kí tự enter trong buffer
                } catch (InputMismatchException e) {
                    System.err.println("Vui long nhap so!!!");
                    sc.nextLine();// Xoá buffer trước khi người dùng nhập lại
                }
            } while (!nhapThanhCong);
            switch (chucnang) {
                case 1: {
                    ds1.xuat();
                    break;
                }
                case 2: {
                    ds1.nhap();
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
                    if (ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachKHmini();
                        System.out.print("Nhap ma khach hang can xoa: ");
                        String ma = sc.nextLine();
                        ds1.xoa(ma);
                    }
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 5: {
                    if (ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachKHmini();
                        ds1.sua();
                    }
                    break;
                }

                case 6: {
                    if (ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachKHmini();
                        DanhSachKhachHang kqtimkiem;
                        System.out.print("Nhap ma khach hang can tim(nhan enter de bo qua): ");
                        String ma = sc.nextLine();
                        if(!ma.equals("")) {
                            kqtimkiem = ds1.timkiemnangcao(ma, "", "");
                        } else {
                            System.out.print("Nhap ho va ten khach hang can tim(nhan enter de bo qua): ");
                            String hovaten = sc.nextLine();
                            System.out.print("Nhap so dien thoai khach hang can tim(nhan enter de bo qua): ");
                            String sdt = sc.nextLine();
                            kqtimkiem = ds1.timkiemnangcao(ma, hovaten, sdt);
                        }
                        
                        kqtimkiem.bolocKetqua();

                    }
                    break;
                }
                case 7: {
                    inputData();
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
