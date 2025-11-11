package com.list;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.model.NhanVien;
import java.io.IOException;


public class DanhSachNhanVien implements listInterface.IList {
    NhanVien[] dsnv;
    int n;
    int seedID = 1;
    protected static final Scanner sc = new Scanner(System.in);
    
    public DanhSachNhanVien() {
        n = 0;
        dsnv = new NhanVien[0];
    }
    
    public DanhSachNhanVien(NhanVien[] dsnv, int n) {
        this.dsnv = dsnv;
        this.n = n;
    }
    
    public void DanhSachNVmini() {
        //Hiển thị danh sách nhân viên hàng thu gọn
        System.out.println("Ma nhan vien   Ho va ten           Tuoi   So dien thoai   Chuc vu      Luong");
        
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến H là 15, đoạn này tính toán để mã NV chiếm 15 ô
            System.out.print(dsnv[i].getMaNV());
            for(int j = 0; j < 15 - dsnv[i].getMaNV().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ H đến T là 20, đoạn này tính toán để tên KH chiếm 20 ô
            System.out.print(dsnv[i].getHoVaTen());
            for(int j = 0; j < 20 - dsnv[i].getHoVaTen().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ T đến S là 7, đoạn này tính toán để tên KH chiếm 7 ô
            System.out.print(dsnv[i].getTuoi());
            for(int j = 0; j < 7 - Integer.toString(dsnv[i].getTuoi()).length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ S đến C là 16, đoạn này tính toán để tên KH chiếm 16 ô
            System.out.print(dsnv[i].getSDT());
            for(int j = 0; j < 16 - (dsnv[i].getSDT()).length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ C đến L là 13, đoạn này tính toán để tên KH chiếm 13 ô
            System.out.print(dsnv[i].getChucVu());
            for(int j = 0; j < 13 - (dsnv[i].getSDT()).length();j++) {
                System.out.print(" ");
            }
            System.out.println(dsnv[i].getLuong());
        }
        System.out.println("SL: " + n);
    }
    
    public void DanhSachNVmini(NhanVien[] ds2) {
        //Hiển thị danh sách nhân viên hàng thu gọn
        System.out.println("Ma nhan vien   Ho va ten           Tuoi   So dien thoai   Chuc vu   Luong");
        
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến H là 15, đoạn này tính toán để mã NV chiếm 15 ô
            System.out.print(ds2[i].getMaNV());
            for(int j = 0; j < 15 - ds2[i].getMaNV().length();j++) {
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
            //Khoảng cách từ S đến C là 16, đoạn này tính toán để tên KH chiếm 16 ô
            System.out.print(ds2[i].getSDT());
            for(int j = 0; j < 16 - (ds2[i].getSDT()).length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ C đến L là 13, đoạn này tính toán để tên KH chiếm 13 ô
            System.out.print(ds2[i].getChucVu());
            for(int j = 0; j < 13 - (ds2[i].getSDT()).length();j++) {
                System.out.print(" ");
            }
            System.out.println(ds2[i].getLuong());
        }
        System.out.println("SL: " + n);
    }
    
    @Override public void nhap() {
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
        boolean nhapThanhCong = false;
        System.out.print("\nNhap so luong nhan vien: ");
        do {
            //bắt lỗi người dùng nhập chữ
            try {
                n = sc.nextInt();
                nhapThanhCong = true;
                sc.nextLine();
            } catch(InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                //xoá buffer trước khi người dùng nhập lại
                sc.nextLine();
            }
        } while(!nhapThanhCong);
        dsnv = new NhanVien[n];
        for(int i = 0; i < n; i++) {
            System.out.print("Nhan vien " + (i+1));
            dsnv[i] = new NhanVien();
            dsnv[i].setInfo(seedID);
            seedID++;
            System.out.println();
        }
    }
    
    @Override public void xuat() {
        System.out.println("\n--Danh sach nhan vien--");
        if(n == 0) {
            System.out.println("Khong co nhan vien nao!!!");
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
        //Hàm này xuất dữ liệu toàn bộ nhân viên thành chuỗi
        String xuatChuoi = null;
        for(int i = 0; i < n; i++) {
            String temp = String.join(",", dsnv[i].getMaNV(), dsnv[i].getHoVaTen(), Integer.toString(dsnv[i].getTuoi()), dsnv[i].getSDT(), dsnv[i].getChucVu(), Double.toString(dsnv[i].getLuong()));
            if(i == 0) xuatChuoi = temp;
            else {
                xuatChuoi = xuatChuoi + temp;
            }
            
            //Xuống dòng với mỗi dữ liệu nhân viên mới
            xuatChuoi = xuatChuoi + "\n";
        }
        //Xoá ký tự enter cuối chuỗi
        if(xuatChuoi != null && xuatChuoi.length() > 0 ) {
            xuatChuoi = xuatChuoi.substring(0, xuatChuoi.length() - 1);
        }
        return xuatChuoi;
    }
    
    @Override public void them() {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = new NhanVien();
        dsnv[n].setInfo(seedID);
        n++;
    }
    
    public void them(NhanVien a) {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = new NhanVien();
        dsnv[n] = a;
        n++;
    }
    
    @Override public void xoa(String ma) {
        boolean daXoa = false;
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV().equals(ma)) {
                for(int j = i; j < n - 1; j++) {
                    dsnv[j] = dsnv[j + 1];
                }
                dsnv = Arrays.copyOf(dsnv, n - 1);
                n--;
                daXoa = true;
                break;
            }
        }
        if(daXoa) {
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Khong tim thay nhan vien nay!");
        }
    }
    
    public void bolocKetqua(DanhSachNhanVien ds2) {
        int chucnang = 0;
        int bolocTuoi = 0;
        int sosanhTuoi = 0;
        double bolocLuong = 0;
        int sosanhLuong = 0;
        do {
            DanhSachNhanVien dsBoLoc = new DanhSachNhanVien();
            //Sao chép dữ liệu của danh sách truyền vào, những gì thay đổi trong bộ lọc sẽ không ảnh hưởng dữ liệu gốc
            for(int i = 0; i < ds2.n; i++) {
                dsBoLoc.them(ds2.dsnv[i]);
            }
            //Kiểm tra có bộ lọc nào được sử dụng không
            if(bolocTuoi != 0) {
                for(int i = 0; i < dsBoLoc.n; i++) {
                    if(sosanhTuoi == 1) {
                        if(dsBoLoc.dsnv[i].getTuoi() < bolocTuoi) {
                            dsBoLoc.xoa(dsBoLoc.dsnv[i].getMaNV());
                            //Thêm i-- để khi có phần tử bị xoá và dồn lên nó sẽ kiểm tra phần bị dồn đó
                            i--;
                        }
                    }
                    if(sosanhTuoi == 2) {
                        if(dsBoLoc.dsnv[i].getTuoi() > bolocTuoi) {
                            dsBoLoc.xoa(dsBoLoc.dsnv[i].getMaNV());
                            i--;
                        }
                    }
                    if(sosanhTuoi == 3) {
                        if(dsBoLoc.dsnv[i].getTuoi() != bolocTuoi) {
                            dsBoLoc.xoa(dsBoLoc.dsnv[i].getMaNV());
                            i--;
                        }
                    }
                }
            }
            if(bolocLuong != 0) {
                for(int i = 0; i < dsBoLoc.n; i++) {
                    if(sosanhLuong == 1) {
                        if(dsBoLoc.dsnv[i].getLuong() < bolocLuong) {
                            dsBoLoc.xoa(dsBoLoc.dsnv[i].getMaNV());
                            //Thêm i-- để khi có phần tử bị xoá và dồn lên nó sẽ kiểm tra phần bị dồn đó
                            i--;
                        }
                    }
                    if(sosanhLuong == 2) {
                        if(dsBoLoc.dsnv[i].getLuong() > bolocLuong) {
                            dsBoLoc.xoa(dsBoLoc.dsnv[i].getMaNV());
                            i--;
                        }
                    }
                    if(sosanhLuong == 3) {
                        if(dsBoLoc.dsnv[i].getLuong() != bolocLuong) {
                            dsBoLoc.xoa(dsBoLoc.dsnv[i].getMaNV());
                            i--;
                        }
                    }
                }
            }
            dsBoLoc.DanhSachNVmini();
            System.out.println();
            System.out.println("-Bo loc-");
            System.out.println("1. Tuoi");
            System.out.println("2. Luong");
            System.out.println("3. Xoa bo loc");
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
                            case 2: {
                                bolocTuoi = 0;
                                sosanhTuoi = 0;
                                bolocLuong = 0;
                                sosanhLuong = 0;
                                break;
                            }
                            case 3: break;
                            case 0: {
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
                    do {
                        System.out.print("Nhap luong: ");
                        nhapThanhCong = false;
                        do {
                            //bắt lỗi người dùng nhập chữ
                            try {
                                bolocLuong = sc.nextDouble();
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
                                sosanhLuong = sc.nextInt();
                                nhapThanhCong = true;
                                sc.nextLine(); //Xoá kí tự enter trong buffer
                            } catch (InputMismatchException e) {
                                System.err.println("Vui long nhap so!!!");
                                sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                            }
                        } while(!nhapThanhCong);
                        switch(sosanhLuong) {
                            case 1: break;
                            case 2: break;
                            case 3: break;
                            case 0: {
                                bolocLuong = 0;
                                break;
                            }
                            default: {
                                System.out.println("Chuc nang khong hop le!!!");
                                System.out.println("Nhan enter de nhap lai!!!");
                                sc.nextLine();
                            }
                        }
                    } while(sosanhLuong > 3);
                    break;
                }
                case 3: {
                    bolocTuoi = 0;
                    sosanhTuoi = 0;
                    bolocLuong = 0;
                    sosanhLuong = 0;
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
    
    public NhanVien timkiem(String ma) {
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV().equals(ma)) {
                return dsnv[i];
            }
        }
        return null;
    }
    
    public DanhSachNhanVien timkiemnangcao(String ma, String hovaten, String sdt, String chucvu) {
        DanhSachNhanVien kqtimkiem = new DanhSachNhanVien();
        for(int i = 0; i < n; i++) {
            if(!dsnv[i].getMaNV().contains(ma) && !ma.equals("\n")) 
                continue;
            if(!dsnv[i].getHoVaTen().toLowerCase().contains(hovaten.toLowerCase()) && !hovaten.equals("\n"))
                continue;
            if(!dsnv[i].getSDT().contains(sdt) && !sdt.equals("\n"))
                continue;
            if(!dsnv[i].getChucVu().toLowerCase().contains(chucvu.toLowerCase()) && !sdt.equals("\n"))
                continue;
            kqtimkiem.them(dsnv[i]);
        }
        return kqtimkiem;
        
    }
    
    @Override public void sua() {
        String ma;
        System.out.print("Nhap ma nhan vien can sua: ");
        ma = sc.nextLine();
        boolean suaThanhCong = false;
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV().equals(ma)) {
                int chucnang = 0;
                do {
                    clearScreen();
                    System.out.println("1. Sua ma nhan vien");
                    System.out.println("2. Sua ten");
                    System.out.println("3. Sua tuoi");
                    System.out.println("4. Sua so dien thoai");
                    System.out.println("5. Sua chuc vu");
                    System.out.println("6. Sua luong");
                    System.out.println("7. Sua tat ca");
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
                            dsnv[i].setMaNV();
                            break;
                        case 2:
                            dsnv[i].setHoVaTen();
                            break;
                        case 3:
                            dsnv[i].setTuoi();
                            break;
                        case 4:
                            dsnv[i].setSDT();
                            break;
                        case 5:
                            dsnv[i].setChucVu();
                            break;
                        case 6: 
                            dsnv[i].setLuong();
                            break;
                        case 7:
                            dsnv[i].setInfo();
                            break;
                        case 0:
                            break;
                        default: {
                            System.out.println("Chuc nang khong hop le!!!");
                            System.out.println("Nhan enter de nhap lai!!!");
                            sc.nextLine();
                        }
                            
                    }
                } while(chucnang != 0);
                suaThanhCong = true;
                break;
            }
        }
        if(!suaThanhCong) {
            System.out.println("Khong co nhan vien nay!!!");
        } else {
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
