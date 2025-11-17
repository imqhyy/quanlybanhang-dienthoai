package com.list;

import java.util.Scanner;
import com.model.KhachHang;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
public class DanhSachKhachHang implements listInterface.IList {
    private KhachHang[] dskh;
    private int n;
    private int seedID = 1;
    private boolean dataChange = false;
    private static final Scanner sc = new Scanner(System.in);
    
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
        System.out.println("STT   Ma khach hang   Ho va ten           Tuoi   So dien thoai");
        if(n == 0) {
            System.out.println("Khong co ket qua phu hop");
            return;
        }
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ S đến M là 6 ô, đoạn này tính toán để stt chiếm 6 ô
            System.out.print(i + 1);
            for(int j = 0; j < 6 - Integer.toString(i + 1).length();j++) {
                System.out.print(" ");
            }
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
    
    
    @Override public void nhap() {
        int n_temp = 0;
        boolean nhapThanhCong = false;
        do {
            System.out.print("Nhap so luong khach hang: ");
            //bắt lỗi người dùng nhập chữ
            try {
                n_temp = sc.nextInt();
                sc.nextLine();
                if(n_temp > 0) {
                    nhapThanhCong = true;
                } else {
                    System.out.println("So luong khach hang phai lon hon 0!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
            }
        } while(!nhapThanhCong);
        dskh = Arrays.copyOf(dskh, n + n_temp);
        for(int i = n; i < n + n_temp; i++) {
            System.out.print("Khach hang " + (i + 1));
            dskh[i] = new KhachHang();
            dskh[i].setInfo(seedID);
            seedID++;
            System.out.println();
        }
        n = n + n_temp;
        System.out.println("Nhap thanh cong!");
        dataChange = true;
        System.out.println("Nhan enter de quay lai!!!");
        sc.nextLine();
    }
    
    @Override public void xuat() {
        System.out.println("\n--Danh sach khach hang--");
        //Kiểm tra xem danh sách nhân viên có rỗng không
        if(n == 0) {
            System.out.println("Khong co khach hang nao!!!");
            System.out.println("\nNhan enter de quay lai!");
            sc.nextLine();
        } else {
            bolocKetqua();
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
            //Xoá kí tự enter cuối chuỗi
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
        dataChange = true;
    }
    
    public void them(KhachHang a) {
        dskh = Arrays.copyOf(dskh, n + 1);
        dskh[n] = new KhachHang();
        dskh[n] = a;
        n++;
        seedID++;
        dataChange = true;
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
            dataChange = true;
        } else {
            System.out.println("Khong tim thay khach hang nay!");
        }
    }

    public void xoaKhongOutput(String ma) {
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
    
    public void bolocKetqua() {
        int chucnang = 0;
        int bolocTuoi = 0;
        int sosanhTuoi = 0;
        do {
            DanhSachKhachHang dsBoLoc = new DanhSachKhachHang();
            //Sao chép dữ liệu của danh sách truyền vào, những gì thay đổi trong bộ lọc sẽ không ảnh hưởng dữ liệu gốc
            for(int i = 0; i < this.n; i++) {
                dsBoLoc.them(this.dskh[i]);
            }
            //Kiểm tra có bộ lọc nào được sử dụng không
            if(bolocTuoi != 0) {
                for(int i = 0; i < dsBoLoc.n; i++) {
                    if(sosanhTuoi == 1) {
                        if(dsBoLoc.dskh[i].getTuoi() < bolocTuoi) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dskh[i].getMaKH());
                            //Thêm i-- để khi có phần tử bị xoá và dồn lên nó sẽ kiểm tra phần bị dồn đó
                            i--;
                        }
                    }
                    if(sosanhTuoi == 2) {
                        if(dsBoLoc.dskh[i].getTuoi() > bolocTuoi) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dskh[i].getMaKH());
                            i--;
                        }
                    }
                    if(sosanhTuoi == 3) {
                        if(dsBoLoc.dskh[i].getTuoi() != bolocTuoi) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dskh[i].getMaKH());
                            i--;
                        }
                    }
                }
            }
            clearScreen();
            dsBoLoc.DanhSachKHmini();
            System.out.println();
            System.out.println("-Bo loc-");
            System.out.println("1. Tuoi");
            System.out.println("2. Xoa bo loc");
            System.out.println("0. Thoat");
            boolean nhapThanhCong = false;
            do {
                System.out.print("Nhap chuc nang: ");
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
                        
                        nhapThanhCong = false;
                        do {
                            System.out.print("Nhap so tuoi: ");
                            //bắt lỗi người dùng nhập chữ
                            try {
                                bolocTuoi = sc.nextInt();
                                sc.nextLine(); //Xoá kí tự enter trong buffer
                                if(bolocTuoi > 0 && bolocTuoi < 100) {
                                    nhapThanhCong = true;
                                } else {
                                    System.out.println("Tuoi khach hang phai lon hon 0 va be hon 100");
                                }
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
                            case 0: break;
                            default: {
                                System.out.println("Chuc nang khong hop le!!!");
                                System.out.println("Nhan enter de quay lai!!!");
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
            if(!dskh[i].getMaKH().equals(ma) && !ma.isBlank()) 
                continue;
            if(!dskh[i].getHoVaTen().toLowerCase().contains(hovaten.toLowerCase()) && !hovaten.isBlank())
                continue;
            if(!dskh[i].getSDT().contains(sdt) && !sdt.isBlank())
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
        boolean timKH = false;
        for(int i = 0; i < n; i++) {
            if(dskh[i].getMaKH().equals(ma)) {
                timKH = true;
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
                            suaThanhCong = true;
                            break;
                        case 2:
                            dskh[i].setTuoi();
                            suaThanhCong = true;
                            break;
                        case 3:
                            dskh[i].setSDT();
                            suaThanhCong = true;
                            break;
                        case 4:
                            dskh[i].setInfo();
                            suaThanhCong = true;
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
                if(suaThanhCong) {
                    dataChange = true;
                    System.out.println("Sua thanh cong!!!");
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                }
                break;
            }
        }
        if(!timKH) {
            System.out.println("Khong tim thay khach hang nay!!!");
            System.out.println("Nhan enter de quay lai!!!");
            sc.nextLine();
        }

    }

    public boolean getDataChange() {
        return dataChange;
    }

    public void setDataChange(boolean x) {
        dataChange = x;
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
