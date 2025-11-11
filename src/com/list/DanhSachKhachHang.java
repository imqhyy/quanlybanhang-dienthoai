package com.list;

import java.util.Scanner;
import com.model.KhachHang;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
public class DanhSachKhachHang implements listInterface.IList {
    KhachHang[] dskh;
    int n;
    int seedID = 1;
    protected static final Scanner sc = new Scanner(System.in);
    
    public DanhSachKhachHang() {
        n = 0;
        dskh = new KhachHang[0];
    }
    
    public DanhSachKhachHang(KhachHang[] dskh, int n) {
        this.n = n;
        this.dskh = dskh;
    }
    
    public void DanhSachKHmini() {
        //Hiển thị danh sách khách hàng thu gọn
        System.out.println("Ma khach hang   Ho va ten           Tuoi   So dien thoai");
        if(n == 0) {
            System.out.println("Khong co ket qua phu hop");
            return;
        }
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến H là 16, đoạn này tính toán để mã KH chiếm 16 ô
            System.out.print(dskh[i].getMaKH());
            for(int j = 0; j < 16 - dskh[i].getMaKH().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ H đến T là 20, đoạn này tính toán để tên KH chiếm 20 ô
            System.out.print(dskh[i].getHoVaTen());
            for(int j = 0; j < 20 - dskh[i].getHoVaTen().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ T đến S là 7, đoạn này tính toán để tên KH chiếm 7 ô
            System.out.print(dskh[i].getTuoi());
            for(int j = 0; j < 7 - Integer.toString(dskh[i].getTuoi()).length();j++) {
                System.out.print(" ");
            }
            System.out.println(dskh[i].getSDT());
        }
        System.out.println("SL: " + n);
    }
    
    public void DanhSachKHmini(KhachHang[] ds2) {
        //Hiển thị danh sách khách hàng thu gọn
        System.out.println("Ma khach hang   Ho va ten           Tuoi   So dien thoai");
        
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến H là 16, đoạn này tính toán để mã KH chiếm 16 ô
            System.out.print(ds2[i].getMaKH());
            for(int j = 0; j < 16 - ds2[i].getMaKH().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ H đến T là 20, đoạn này tính toán để tên KH chiếm 20 ô
            System.out.print(ds2[i].getHoVaTen());
            for(int j = 0; j < 20 - ds2[i].getHoVaTen().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ T đến S là 7, đoạn này tính toán để tên KH chiếm 7 ô
            System.out.print(ds2[i].getTuoi());
            for(int j = 0; j < 7 - Integer.toString(ds2[i].getTuoi()).length();j++) {
                System.out.print(" ");
            }
            System.out.println(ds2[i].getSDT());
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
            System.out.print("Khach hang " + (i + 1));
            dskh[i] = new KhachHang();
            dskh[i].setInfo(seedID);
            seedID++;
            System.out.println();
        }
    }
    
    @Override public void xuat() {
        System.out.println("\n--Danh sach khach hang--");
        //Kiểm tra xem danh sách nhân viên có rỗng không
        if(n == 0) {
            System.out.println("Khong co khach hang nao!!!");
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
        dskh[n].setInfo(seedID);
        seedID++;
        n++;
    }
    
    public void them(KhachHang a) {
        dskh = Arrays.copyOf(dskh, n + 1);
        dskh[n] = new KhachHang();
        dskh[n] = a;
        n++;
        seedID++;
    }
    
    @Override public void xoa(String ma) {
        boolean daXoa = false;
        for(int i = 0; i < n; i++) {
            if(dskh[i].getMaKH().equals(ma)) {
                for(int j = i; j < n - 1; j++) {
                    dskh[j] = dskh[j + 1];
                }
                dskh = Arrays.copyOf(dskh, n - 1);
                n--;
                daXoa = true;
                break;
            }
        }
        if(daXoa) {
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay khach hang nay!");
        }
    }
    
    public void bolocKetqua(DanhSachKhachHang ds2) {
        int chucnang = 0;
        int bolocTuoi = 0;
        int sosanhTuoi = 0;
        do {
            DanhSachKhachHang dsBoLoc = new DanhSachKhachHang();
            //Sao chép dữ liệu của danh sách truyền vào, những gì thay đổi trong bộ lọc sẽ không ảnh hưởng dữ liệu gốc
            for(int i = 0; i < ds2.n; i++) {
                dsBoLoc.them(ds2.dskh[i]);
            }
            //Kiểm tra có bộ lọc nào được sử dụng không
            if(bolocTuoi != 0) {
                for(int i = 0; i < dsBoLoc.n; i++) {
                    if(sosanhTuoi == 1) {
                        if(dsBoLoc.dskh[i].getTuoi() < bolocTuoi) {
                            dsBoLoc.xoa(dsBoLoc.dskh[i].getMaKH());
                            //Thêm i-- để khi có phần tử bị xoá và dồn lên nó sẽ kiểm tra phần bị dồn đó
                            i--;
                        }
                    }
                    if(sosanhTuoi == 2) {
                        if(dsBoLoc.dskh[i].getTuoi() > bolocTuoi) {
                            dsBoLoc.xoa(dsBoLoc.dskh[i].getMaKH());
                            i--;
                        }
                    }
                    if(sosanhTuoi == 3) {
                        if(dsBoLoc.dskh[i].getTuoi() != bolocTuoi) {
                            dsBoLoc.xoa(dsBoLoc.dskh[i].getMaKH());
                            i--;
                        }
                    }
                }
            }
            dsBoLoc.DanhSachKHmini();
            System.out.println();
            System.out.println("-Bo loc-");
            System.out.println("1. Tuoi");
            System.out.println("2. Xoa bo loc");
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
                        System.out.print("Nhap so tuoi: ");
                        nhapThanhCong = false;
                        do {
                            //bắt lỗi người dùng nhập chữ
                            try {
                                bolocTuoi = sc.nextInt();
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
                                sosanhTuoi = sc.nextInt();
                                nhapThanhCong = true;
                                sc.nextLine(); //Xoá kí tự enter trong buffer
                            } catch (InputMismatchException e) {
                                System.err.println("Vui long nhap so!!!");
                                sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                            }
                        } while(!nhapThanhCong);
                        switch(sosanhTuoi) {
                            case 1: break;
                            case 2: break;
                            case 3: break;
                            case 0: {
                                bolocTuoi = 0;
                                break;
                            }
                            default: {
                                System.out.println("Chuc nang khong hop le!!!");
                                System.out.println("Nhan enter de nhap lai!!!");
                                sc.nextLine();
                            }
                        }
                    } while(sosanhTuoi > 3);
                    break;
                }
                case 2: {
                    bolocTuoi = 0;
                    sosanhTuoi = 0;
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
    
    public KhachHang timkiem(String ma) {
        for(int i = 0; i < n; i++) {
            if(dskh[i].getMaKH().equals(ma)) {
                return dskh[i];
            }
        }
        return null;
    }
    
    public DanhSachKhachHang timkiemnangcao(String ma, String hovaten, String sdt) {
        DanhSachKhachHang kqtimkiem = new DanhSachKhachHang();
        for(int i = 0; i < n; i++) {
            if(!dskh[i].getMaKH().contains(ma) && !ma.equals("\n")) 
                continue;
            if(!dskh[i].getHoVaTen().toLowerCase().contains(hovaten.toLowerCase()) && !hovaten.equals("\n"))
                continue;
            if(!dskh[i].getSDT().contains(sdt) && !sdt.equals("\n"))
                continue;
            kqtimkiem.them(dskh[i]);
        }
        return kqtimkiem;
        
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
                    System.out.println("1. Sua ten");
                    System.out.println("2. Sua tuoi");
                    System.out.println("3. Sua so dien thoai");
                    System.out.println("4. Sua tat ca");
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
                    switch(chucnang) {
                        case 1:
                            dskh[i].setHoVaTen();
                            break;
                        case 2:
                            dskh[i].setTuoi();
                            break;
                        case 3:
                            dskh[i].setSDT();
                            break;
                        case 4:
                            dskh[i].setInfo();
                            break;
                        case 0:
                            break;
                        default: {
                            System.out.println("Chuc nang khong hop le!!!");
                            System.out.println("Nhan enter de nhap lai!!!");
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
