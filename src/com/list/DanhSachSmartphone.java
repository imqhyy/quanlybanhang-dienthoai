package com.list;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


import com.model.SmartPhone;
import java.io.IOException;


public class DanhSachSmartphone {
    SmartPhone[] dsdt; // Mảng lưu SmartPhone
    int n; // Số lượng smartphone hiện có
    int seedID = 1;
    protected static final Scanner sc = new Scanner(System.in);

    // Constructor ko có tham số
    public DanhSachSmartphone() {
        n = 0;
        dsdt = new SmartPhone[0]; // Khởi tạo mảng rỗng
    }

    // Constructor có tham số
    public DanhSachSmartphone(SmartPhone[] dsdt, int n) {
        this.dsdt = dsdt;
        this.n = n;
    }
    
    public void DanhSachSPmini() {
        //Hiển thị danh sách khách hàng thu gọn
        System.out.println("Ma san pham   Ten san pham                  Thuong hieu   Gia ban");
        if(n == 0) {
            System.out.println("Khong co ket qua phu hop");
            return;
        }
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến T là 14, đoạn này tính toán để mã Sản phẩm chiếm 14 ô
            System.out.print(dsdt[i].getMaSP());
            for(int j = 0; j < 14 - dsdt[i].getMaSP().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ T đến T là 30, đoạn này tính toán để tên Sản phẩm chiếm 30 ô
            System.out.print(dsdt[i].getTenSP());
            for(int j = 0; j < 30 - dsdt[i].getTenSP().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ T đến G là 14, đoạn này tính toán để tên Thương hiệu chiếm 14 ô
            System.out.print(dsdt[i].getThuongHieu());
            for(int j = 0; j < 14 - dsdt[i].getThuongHieu().length();j++) {
                System.out.print(" ");
            }
            System.out.println(dsdt[i].getGiaBan());
        }
        System.out.println("SL: " + n);
    }

    public void DanhSachSPmini(SmartPhone[] ds2) {
        //Hiển thị danh sách khách hàng thu gọn
        System.out.println("--Danh sach smartphone--");
        System.out.println("Ma san pham   Ten san pham           Thuong hieu   Gia ban");
        if(n == 0) {
            System.out.println("Khong co ket qua phu hop");
            return;
        }
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến T là 14, đoạn này tính toán để mã Sản phẩm chiếm 14 ô
            System.out.print(ds2[i].getMaSP());
            for(int j = 0; j < 14 - ds2[i].getMaSP().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ T đến T là 23, đoạn này tính toán để tên Sản phẩm chiếm 23 ô
            System.out.print(ds2[i].getTenSP());
            for(int j = 0; j < 23 - ds2[i].getTenSP().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ T đến G là 14, đoạn này tính toán để tên Thương hiệu chiếm 14 ô
            System.out.print(ds2[i].getThuongHieu());
            for(int j = 0; j < 14 - ds2[i].getThuongHieu().length();j++) {
                System.out.print(" ");
            }
            System.out.println(ds2[i].getGiaBan());
        }
        System.out.println("SL: " + n);
    }

    public void nhap() {
        //Kiểm tra xem có dữ liệu cũ nào được lưu không vì nhập sẽ xoá toàn bộ dữ liệu cũ
        if(n != 0) {
            String xacnhan;
            System.out.println("Hanh dong nay se xoa du lieu cu!!!");
            System.out.print("Nhan 'y' de xac nhan, 'n' de quay lai: ");
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
        System.out.print("Nhap so luong smartphone: ");
        boolean nhapThanhCong = false;
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
        dsdt = new SmartPhone[n];
        for(int i = 0; i < n; i++) {
            System.out.print("Smartphone " + (i + 1));
            dsdt[i] = new SmartPhone();
            dsdt[i].setInfo(seedID);
            seedID++;
            System.out.println();
        }
    }

    // Xuất danh sách
    public void xuat() {
        System.out.println("\n--Danh sach smartphone--");
        //Kiểm tra xem danh sách nhân viên có rỗng không
        if(n == 0) {
            System.out.println("Khong co smartphone nao nao!!!");
        } else {
            bolocKetqua(this);
        }
    }

    public int xuatN() {
        return n;
    }
    
    public int getSeedID() {
        return seedID;
    }

    public void setSeedID(int seedID) {
        this.seedID = seedID;
    }
    
    // Xuất ra chuỗi để ghi file
    public String xuatChuoi() {
        // Hàm này xuất dữ liệu toàn bộ smartphone thành chuỗi
        String xuatChuoi = null;
        for (int i = 0; i < n; i++) {
            // Nối tất cả thuộc tính lại, cách nhau bởi dấu phẩy
            String temp = String.join(",",
                    dsdt[i].getMaSP(),
                    dsdt[i].getTenSP(),
                    dsdt[i].getThuongHieu(),
                    Double.toString(dsdt[i].getGiaBan()), // Chuyển double sang String
                    dsdt[i].getChipset(),
                    dsdt[i].getRam(),
                    dsdt[i].getRom(),
                    dsdt[i].getManHinh(),
                    dsdt[i].getChiTiet());

            if (i == 0)
                xuatChuoi = temp;
            else {
                xuatChuoi = xuatChuoi + temp;
            }

            // Xuống dòng với mỗi dữ liệu smartphone mới
            xuatChuoi = xuatChuoi + "\n";
        }
        // Xoá ký tự enter cuối chuỗi
        if (xuatChuoi != null && xuatChuoi.length() > 0) {
            xuatChuoi = xuatChuoi.substring(0, xuatChuoi.length() - 1);
        }
        return xuatChuoi;
    }

    // Thêm (Tự nhập)
    public void them() {
        // Nới rộng mảng
        dsdt = Arrays.copyOf(dsdt, n + 1);

        // Tạo đối tượng mới và nhập thông tin
        dsdt[n] = new SmartPhone();
        dsdt[n].setInfo(seedID);

        // Tăng số lượng
        n++;
    }

    // Thêm (Đối tượng có sẵn)
    public void them(SmartPhone a) {
        dsdt = Arrays.copyOf(dsdt, n + 1);
        dsdt[n] = new SmartPhone();
        dsdt[n] = a;
        n++;
    }

    // Xóa
    public void xoa(String ma) {
        boolean daXoa = false;
        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaSP().equals(ma)) {
                for (int j = i; j < n - 1; j++) {
                    dsdt[j] = dsdt[j + 1];
                }
                dsdt = Arrays.copyOf(dsdt, n - 1);
                n--;
                daXoa = true;
                break;
            }
        }
        if(daXoa) {
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay san pham nay!");
        }
    }

    public void bolocKetqua(DanhSachSmartphone ds2) {
        int chucnang = 0;
        double bolocGia = 0;
        int sosanhGia = 0;
        do {
            DanhSachSmartphone dsBoLoc = new DanhSachSmartphone();
            //Sao chép dữ liệu của danh sách truyền vào, những gì thay đổi trong bộ lọc sẽ không ảnh hưởng dữ liệu gốc
            for(int i = 0; i < ds2.n; i++) {
                dsBoLoc.them(ds2.dsdt[i]);
            }
            //Kiểm tra có bộ lọc nào được sử dụng không
            if(bolocGia != 0) {
                for(int i = 0; i < dsBoLoc.n; i++) {
                    if(sosanhGia == 1) {
                        if(dsBoLoc.dsdt[i].getGiaBan() < bolocGia) {
                            dsBoLoc.xoa(dsBoLoc.dsdt[i].getMaSP());
                            //Thêm i-- để khi có phần tử bị xoá và dồn lên nó sẽ kiểm tra phần bị dồn đó
                            i--;
                        }
                    }
                    if(sosanhGia == 2) {
                        if(dsBoLoc.dsdt[i].getGiaBan() > bolocGia) {
                            dsBoLoc.xoa(dsBoLoc.dsdt[i].getMaSP());
                            i--;
                        }
                    }
                    if(sosanhGia == 3) {
                        if(dsBoLoc.dsdt[i].getGiaBan() != bolocGia) {
                            dsBoLoc.xoa(dsBoLoc.dsdt[i].getMaSP());
                            i--;
                        }
                    }
                }
            }
            dsBoLoc.DanhSachSPmini();
            System.out.println();
            System.out.println("-Bo loc-");
            System.out.println("1. Gia ban");
            System.out.println("2. Xoa bo loc");
            System.out.println("3. Xem chi tiet san pham");
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
                    do {
                        System.out.print("Nhap gia ban: ");
                        nhapThanhCong = false;
                        do {
                            //bắt lỗi người dùng nhập chữ
                            try {
                                bolocGia = sc.nextDouble();
                                nhapThanhCong = true;
                                sc.nextLine(); //Xoá kí tự enter trong buffer
                            } catch (InputMismatchException e) {
                                System.err.println("Vui long nhap so!!!");
                                sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                            }
                        } while(!nhapThanhCong);
                        System.out.println("1. Lon hon hoac bang");
                        System.out.println("2. Be hon hoac bang");
                        System.out.println("3. Bang");
                        System.out.println("0. Thoat");
                        System.out.print("Nhap chuc nang: ");
                        nhapThanhCong = false;
                        do {
                            //bắt lỗi người dùng nhập chữ
                            try {
                                sosanhGia = sc.nextInt();
                                nhapThanhCong = true;
                                sc.nextLine(); //Xoá kí tự enter trong buffer
                            } catch (InputMismatchException e) {
                                System.err.println("Vui long nhap so!!!");
                                sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                            }
                        } while(!nhapThanhCong);
                        switch(sosanhGia) {
                            case 1: break;
                            case 2: break;
                            case 3: break;
                            case 0: {
                                bolocGia = 0;
                                break;
                            }
                            default: {
                                System.out.println("Chuc nang khong hop le!!!");
                                System.out.println("Nhan enter de nhap lai!!!");
                                sc.nextLine();
                            }
                        }
                    } while(sosanhGia > 3);
                    break;
                }
                case 2: {
                    bolocGia = 0;
                    sosanhGia = 0;
                    break;
                }
                case 3: {
                    System.out.print("Nhap ma san pham muon xem chi tiet: ");
                    String ma = sc.nextLine();
                    boolean xemThanhCong = false;
                    for(int i = 0; i < n; i++) {
                        if(dsdt[i].getMaSP().equals(ma)) {
                            dsdt[i].getInfo();
                            xemThanhCong = true;
                            break;
                        }
                    }
                    if(!xemThanhCong) {
                        System.out.println("Khong tim thay san pham!");
                    }
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("Chuc nang khong hop le!!!");
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                }
                    
            }
        } while (chucnang != 0);
    }

    // tÌm kiếm
    public SmartPhone timkiem(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaSP().equals(ma)) {
                return dsdt[i];
            }
        }
        return null;
    }

    public DanhSachSmartphone timkiemnangcao(String maSP, String tenSP, String thuonghieu) {
        DanhSachSmartphone kqtimkiem = new DanhSachSmartphone();
        for(int i = 0; i < n; i++) {
            if(!dsdt[i].getMaSP().contains(maSP) && !maSP.equals("\n")) 
                continue;
            if(!dsdt[i].getTenSP().toLowerCase().contains(tenSP.toLowerCase()) && !tenSP.equals("\n"))
                continue;
            if(!dsdt[i].getThuongHieu().toLowerCase().contains(thuonghieu.toLowerCase()) && !thuonghieu.equals("\n"))
                continue;
            kqtimkiem.them(dsdt[i]);
        }
        return kqtimkiem;
    }
    public void sua() {
        String ma;
        System.out.print("Nhap ma smartphone can sua: ");
        ma = sc.nextLine();
        boolean suaThanhCong = false;

        for (int i = 0; i < n; i++) {
            if (dsdt[i].getMaSP().equals(ma)) {
                int chucnang = 0;
                do {
                    clearScreen();
                    System.out.println("--- Sua thong tin Smartphone (" + dsdt[i].getTenSP() + ") ---");
                    System.out.println("1. Sua ten san pham");
                    System.out.println("2. Sua thuong hieu");
                    System.out.println("3. Sua gia ban");
                    System.out.println("4. Sua chipset");
                    System.out.println("5. Sua RAM");
                    System.out.println("6. Sua ROM");
                    System.out.println("7. Sua man hinh");
                    System.out.println("8. Sua chi tiet");
                    System.out.println("9. Sua tat ca");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap tinh nang: ");

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
                        case 1:
                            dsdt[i].setTenSP();
                            break;
                        case 2:
                            dsdt[i].setThuongHieu();
                            break;
                        case 3:
                            dsdt[i].setGiaBan();
                            break;
                        case 4:
                            dsdt[i].setChipset();
                            break;
                        case 5:
                            dsdt[i].setRam();
                            break;
                        case 6:
                            dsdt[i].setRom();
                            break;
                        case 7:
                            dsdt[i].setManHinh();
                            break;
                        case 8:
                            dsdt[i].setChiTiet();
                            break;
                        case 9:
                            dsdt[i].setInfo(); // Gọi hàm setInfo của SmartPhone
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Chuc nang khong hop le!!!");
                            System.out.println("Nhan enter de quay lai!!!");
                            sc.nextLine();
                    }
                } while (chucnang != 0);

                suaThanhCong = true;
                break; // Thoát vòng lặp for
            }
        }

        if (!suaThanhCong) {
            System.err.println("Khong co smartphone nay!!!");
        } else {
            System.out.println("Sua thong tin thanh cong!!!");
        }
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