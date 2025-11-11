package com.list;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.model.DonHang;
import java.util.Arrays;
public class DanhSachDonHang implements listInterface.IList {
    DonHang[] dsdh;
    int n;
    int seedID = 1;
    protected static final Scanner sc = new Scanner(System.in);

    //Constructor không tham số
    public DanhSachDonHang() {
        n = 0;
        dsdh = new DonHang[n];
    }

    //Constructor có tham số
    public DanhSachDonHang(DonHang[] dsdh, int n) {
        this.dsdh = dsdh;
        this.n = n;
    }

    public void DanhSachDHmini() {
        //Hiển thị danh sách khách hàng thu gọn
        System.out.println("Ma don hang   Ngay dat       Ma khach hang   Ma nhan vien   Tong san pham");
        if(n == 0) {
            System.out.println("Khong co ket qua phu hop");
            return;
        }
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến T là 14, đoạn này tính toán để mã Đơn hàng chiếm 14 ô
            System.out.print(dsdh[i].getMaDH());
            for(int j = 0; j < 14 - dsdh[i].getMaDH().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ N đến M là 15, đoạn này tính toán để Ngày đặt chiếm 15 ô
            System.out.print(dsdh[i].getNgayDat());
            for(int j = 0; j < 15 - dsdh[i].getNgayDat().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ M đến M là 16, đoạn này tính toán để Mã khách hàng hiệu chiếm 16 ô
            System.out.print(dsdh[i].getKH().getMaKH());
            for(int j = 0; j < 16 - dsdh[i].getKH().getMaKH().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ M đến M là 15, đoạn này tính toán để Mã nhân viên hiệu chiếm 15 ô
            System.out.print(dsdh[i].getNV().getMaNV());
            for(int j = 0; j < 15 - dsdh[i].getNV().getMaNV().length();j++) {
                System.out.print(" ");
            }
            System.out.println(dsdh[i].getTongSP());
        }
        System.out.println("SL: " + n);
    }

    public void DanhSachDHmini(DonHang[] ds2) {
        //Hiển thị danh sách khách hàng thu gọn
        System.out.println("Ma don hang   Ngay dat       Ma khach hang   Ma nhan vien   Tong san pham");
        if(n == 0) {
            System.out.println("Khong co ket qua phu hop");
            return;
        }
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến T là 14, đoạn này tính toán để mã Đơn hàng chiếm 14 ô
            System.out.print(ds2[i].getMaDH());
            for(int j = 0; j < 14 - ds2[i].getMaDH().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ N đến M là 11, đoạn này tính toán để Ngày đặt chiếm 11 ô
            System.out.print(ds2[i].getNgayDat());
            for(int j = 0; j < 15 - ds2[i].getNgayDat().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ M đến M là 16, đoạn này tính toán để Mã khách hàng chiếm 16 ô
            System.out.print(ds2[i].getKH().getMaKH());
            for(int j = 0; j < 16 - ds2[i].getKH().getMaKH().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ M đến M là 15, đoạn này tính toán để Mã nhân viên chiếm 15 ô
            System.out.print(ds2[i].getNV().getMaNV());
            for(int j = 0; j < 15 - ds2[i].getNV().getMaNV().length();j++) {
                System.out.print(" ");
            }
        }
        System.out.println("SL: " + n);
    }

    @Override public void nhap() {
        //Kiểm tra xem có dữ liệu cũ nào được lưu không vì nhập sẽ xoá toàn bộ dữ liệu cũ
        if(n != 0) {
            String xacnhan;
            System.out.println("Hanh dong nay se xoa du lieu cu!!!");
            System.out.print("Nhan 'y' de xac nhan, 'n' de huy lai: ");
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
        System.out.print("Nhap so luong don hang: ");
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
        dsdh = new DonHang[n];
        for(int i = 0; i < n; i++) {
            System.out.print("Don hang " + (i + 1));
            dsdh[i] = new DonHang();
            dsdh[i].setInfo(seedID);
            seedID++;
            System.out.println();
        }
    }

    // Xuất danh sách
    @Override public void xuat() {
        System.out.println("\n--Danh sach don hang--");
        //Kiểm tra xem danh sách nhân viên có rỗng không
        if(n == 0) {
            System.out.println("Khong co don hang nao nao!!!");
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
        // Hàm này xuất dữ liệu toàn bộ đơn hàng thành chuỗi
        String xuatChuoi = null;
        for (int i = 0; i < n; i++) {
            // Nối tất cả thuộc tính lại, cách nhau bởi dấu phẩy
            String temp = String.join(",",
                dsdh[i].getMaDH(),
                dsdh[i].getNgayDat(),
                dsdh[i].getKH().getMaKH(),
                dsdh[i].getNV().getMaNV(),
                Integer.toString(dsdh[i].getN()),
                dsdh[i].getDataSP()
            );

            

            if (i == 0)
                xuatChuoi = temp;
            else {
                xuatChuoi = xuatChuoi + temp;
            }

            // Xuống dòng với mỗi dữ liệu đơn hàng mới
            xuatChuoi = xuatChuoi + "\n";
        }
        // Xoá ký tự enter cuối chuỗi
        if (xuatChuoi != null && xuatChuoi.length() > 0) {
            xuatChuoi = xuatChuoi.substring(0, xuatChuoi.length() - 1);
        }
        return xuatChuoi;
    }
    
    // Thêm (Tự nhập)
    @Override public void them() {
        // Nới rộng mảng
        dsdh = Arrays.copyOf(dsdh, n + 1);

        // Tạo đối tượng mới và nhập thông tin
        dsdh[n] = new DonHang();
        dsdh[n].setInfo(seedID);

        // Tăng số lượng
        n++;
    }

    // Thêm (Đối tượng có sẵn), hàm này được dùng trong quản lý, hãy cẩn thận vì bạn phải tự set lại seedID
    public void them(DonHang a) {
        dsdh = Arrays.copyOf(dsdh, n + 1);
        dsdh[n] = new DonHang();
        dsdh[n] = a;
        n++;
    }

    // Xóa
    @Override public void xoa(String ma) {
        boolean daXoa = false;
        for (int i = 0; i < n; i++) {
            if (dsdh[i].getMaDH().equals(ma)) {
                for (int j = i; j < n - 1; j++) {
                    dsdh[j] = dsdh[j + 1];
                }
                dsdh = Arrays.copyOf(dsdh, n - 1);
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

    public void bolocKetqua(DanhSachDonHang ds2) {
        int chucnang = 0;
        double bolocGia = 0;
        int sosanhGia = 0;
        do {
            DanhSachDonHang dsBoLoc = new DanhSachDonHang();
            //Sao chép dữ liệu của danh sách truyền vào, những gì thay đổi trong bộ lọc sẽ không ảnh hưởng dữ liệu gốc
            for(int i = 0; i < ds2.n; i++) {
                dsBoLoc.them(ds2.dsdh[i]);
            }
            //Kiểm tra có bộ lọc nào được sử dụng không
            if(bolocGia != 0) {
                for(int i = 0; i < dsBoLoc.n; i++) {
                    if(sosanhGia == 1) {
                        if(dsBoLoc.dsdh[i].getTongSP() < bolocGia) {
                            dsBoLoc.xoa(dsBoLoc.dsdh[i].getMaDH());
                            //Thêm i-- để khi có phần tử bị xoá và dồn lên nó sẽ kiểm tra phần bị dồn đó
                            i--;
                        }
                    }
                    if(sosanhGia == 2) {
                        if(dsBoLoc.dsdh[i].getTongSP() > bolocGia) {
                            dsBoLoc.xoa(dsBoLoc.dsdh[i].getMaDH());
                            i--;
                        }
                    }
                    if(sosanhGia == 3) {
                        if(dsBoLoc.dsdh[i].getTongSP() != bolocGia) {
                            dsBoLoc.xoa(dsBoLoc.dsdh[i].getMaDH());
                            i--;
                        }
                    }
                }
            }
            dsBoLoc.DanhSachDHmini();
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
                            case 2:{
                                bolocGia = 0;
                                sosanhGia = 0;
                                break;
                            }
                            case 3: break;
                            case 0:
                                break;
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
                        if(dsdh[i].getMaDH().equals(ma)) {
                            dsdh[i].getInfo();
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
    public DonHang timkiem(String ma) {
        for (int i = 0; i < n; i++) {
            if (dsdh[i].getMaDH().equals(ma)) {
                return dsdh[i];
            }
        }
        return null;
    }

    public DanhSachDonHang timkiemnangcao(String maDH, String maKH, String maNV) {
        DanhSachDonHang kqtimkiem = new DanhSachDonHang();
        for(int i = 0; i < n; i++) {
            if(!dsdh[i].getMaDH().contains(maDH) && !maDH.equals("\n")) 
                continue;
            if(!dsdh[i].getKH().getMaKH().contains(maKH) && !maKH.equals("\n"))
                continue;
            if(!dsdh[i].getNV().getMaNV().contains(maNV) && !maNV.equals("\n"))
                continue;
            kqtimkiem.them(dsdh[i]);
        }
        return kqtimkiem;
    }
    @Override public void sua() {
        String ma;
        System.out.print("Nhap ma don hang can sua: ");
        ma = sc.nextLine();
        boolean suaThanhCong = false;

        for (int i = 0; i < n; i++) {
            if (dsdh[i].getMaDH().equals(ma)) {
                int chucnang = 0;
                do {
                    clearScreen();
                    System.out.println("--- Sua thong tin don hang (" + dsdh[i].getMaDH() + ") ---");
                    System.out.println("1. Sua ngay dat");
                    System.out.println("2. Sua ma khach hang");
                    System.out.println("3. Sua nhan vien");
                    System.out.println("4. Sua san pham");
                    System.out.println("5. Sua tat ca");
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
                            dsdh[i].setNgayDat();
                            break;
                        case 2:
                            dsdh[i].setKH();
                            break;
                        case 3:
                            dsdh[i].setNV();
                            break;
                        case 4:
                            dsdh[i].setSP();
                            break;
                        case 5:
                            dsdh[i].setInfo();
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Chuc nang khong hop le!!!");
                            System.out.println("Nhan enter de nhap lai!!!");
                            sc.nextLine();
                    }
                } while (chucnang != 0);

                suaThanhCong = true;
                break; // Thoát vòng lặp for
            }
        }

        if (!suaThanhCong) {
            System.err.println("Khong co don hang nay!!!");
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
