package com.list;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.model.NhanVien;
import java.io.IOException;


public class DanhSachNhanVien implements listInterface.IList {
    private NhanVien[] dsnv;
    private int n;
    private int seedID = 1;
    private boolean dataChange = false;
    private  static final Scanner sc = new Scanner(System.in);
    
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
        System.out.println("STT   Ma nhan vien   Ho va ten           Tuoi   So dien thoai   Chuc vu      Luong");
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
            for(int j = 0; j < 13 - (dsnv[i].getChucVu()).length();j++) {
                System.out.print(" ");
            }
            System.out.println(dsnv[i].getStringLuong());
        }
        System.out.println("SL: " + n);
    }
    
    @Override public void nhap() {
        int n_temp = 0;
        boolean nhapThanhCong = false;
        do {
            System.out.print("\nNhap so luong nhan vien: ");
            //bắt lỗi người dùng nhập chữ
            try {
                n_temp = sc.nextInt();
                sc.nextLine();
                if(n_temp > 0) {
                    nhapThanhCong = true;
                } else {
                    System.out.println("So luong nhan vien phai lon hon 0!");
                }
            } catch(InputMismatchException e) {
                System.err.println("Vui long nhap so!!!");
                //xoá buffer trước khi người dùng nhập lại
                sc.nextLine();
            }
        } while(!nhapThanhCong);
        dsnv = Arrays.copyOf(dsnv, n + n_temp);
        for(int i = n; i < n + n_temp; i++) {
            System.out.print("Nhan vien " + (i+1));
            dsnv[i] = new NhanVien();
            dsnv[i].setInfo(seedID);
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
        System.out.println("\n--Danh sach nhan vien--");
        if(n == 0) {
            System.out.println("Khong co nhan vien nao!!!");
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
        //Hàm này xuất dữ liệu toàn bộ nhân viên thành chuỗi
        String xuatChuoi = null;
        for(int i = 0; i < n; i++) {
            String temp = String.join(",", 
                    dsnv[i].getMaNV(), 
                    dsnv[i].getHoVaTen(), 
                    Integer.toString(dsnv[i].getTuoi()), 
                    dsnv[i].getSDT(), 
                    dsnv[i].getChucVu(), 
                    dsnv[i].getLuong().toString());
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
        seedID++;
        n++;
        dataChange = true;
    }
    
    public void them(NhanVien a) {
        dsnv = Arrays.copyOf(dsnv, n + 1);
        dsnv[n] = new NhanVien();
        dsnv[n] = a;
        n++;
        seedID++;
        dataChange = true;
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
            dataChange = true;
        } else {
            System.out.println("Khong tim thay nhan vien nay!");
        }
    }

    private void xoaKhongOutput(String ma) {
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV().equals(ma)) {
                for(int j = i; j < n - 1; j++) {
                    dsnv[j] = dsnv[j + 1];
                }
                dsnv = Arrays.copyOf(dsnv, n - 1);
                n--;
                break;
            }
        }
    }
    
    public void bolocKetqua() {
        int chucnang = 0;
        int bolocTuoi = 0;
        int sosanhTuoi = 0;
        double bolocLuong = 0;
        int sosanhLuong = 0;
        do {
            DanhSachNhanVien dsBoLoc = new DanhSachNhanVien();
            //Sao chép dữ liệu của danh sách truyền vào, những gì thay đổi trong bộ lọc sẽ không ảnh hưởng dữ liệu gốc
            for(int i = 0; i < this.n; i++) {
                dsBoLoc.them(this.dsnv[i]);
            }
            //Kiểm tra có bộ lọc nào được sử dụng không
            if(bolocTuoi != 0) {
                for(int i = 0; i < dsBoLoc.n; i++) {
                    if(sosanhTuoi == 1) {
                        if(dsBoLoc.dsnv[i].getTuoi() < bolocTuoi) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dsnv[i].getMaNV());
                            //Thêm i-- để khi có phần tử bị xoá và dồn lên nó sẽ kiểm tra phần bị dồn đó
                            i--;
                        }
                    }
                    if(sosanhTuoi == 2) {
                        if(dsBoLoc.dsnv[i].getTuoi() > bolocTuoi) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dsnv[i].getMaNV());
                            i--;
                        }
                    }
                    if(sosanhTuoi == 3) {
                        if(dsBoLoc.dsnv[i].getTuoi() != bolocTuoi) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dsnv[i].getMaNV());
                            i--;
                        }
                    }
                }
            }
            if(bolocLuong != 0) {
                for(int i = 0; i < dsBoLoc.n; i++) {
                    if(sosanhLuong == 1) {
                        if(dsBoLoc.dsnv[i].getLuong().doubleValue() < bolocLuong) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dsnv[i].getMaNV());
                            //Thêm i-- để khi có phần tử bị xoá và dồn lên nó sẽ kiểm tra phần bị dồn đó
                            i--;
                        }
                    }
                    if(sosanhLuong == 2) {
                        if(dsBoLoc.dsnv[i].getLuong().doubleValue() > bolocLuong) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dsnv[i].getMaNV());
                            i--;
                        }
                    }
                    if(sosanhLuong == 3) {
                        if(dsBoLoc.dsnv[i].getLuong().doubleValue() != bolocLuong) {
                            dsBoLoc.xoaKhongOutput(dsBoLoc.dsnv[i].getMaNV());
                            i--;
                        }
                    }
                }
            }
            clearScreen();
            dsBoLoc.DanhSachNVmini();
            System.out.println();
            System.out.println("-Bo loc-");
            System.out.println("1. Tuoi");
            System.out.println("2. Luong");
            System.out.println("3. Xoa tat ca bo loc");
            System.out.println("0. Thoat");
            boolean nhapThanhCong = false;
            do {
                //bắt lỗi người dùng nhập chữ
                try {
                    System.out.print("Nhap chuc nang: ");
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
                            //bắt lỗi người dùng nhập chữ
                            try {
                                System.out.print("Nhap so tuoi: ");
                                bolocTuoi = sc.nextInt();
                                sc.nextLine(); //Xoá kí tự enter trong buffer
                                if(bolocTuoi >= 18 && bolocTuoi <= 32) {
                                    nhapThanhCong = true;
                                } else {
                                    System.out.println("Tuoi nhan vien phai tu 18 den 32 tuoi!");
                                }
                            } catch (InputMismatchException e) {
                                System.err.println("Vui long nhap so!!!");
                                sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                            }
                        } while(!nhapThanhCong);
                        System.out.println("1. Lon hon hoac bang");
                        System.out.println("2. Be hon hoac bang");
                        System.out.println("3. Bang");
                        System.out.println("4. Xoa bo loc");
                        System.out.println("0. Thoat");
                        nhapThanhCong = false;
                        do {
                            //bắt lỗi người dùng nhập chữ
                            try {
                                System.out.print("Nhap chuc nang: ");
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
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4: {
                                bolocTuoi = 0;
                                sosanhTuoi = 0;
                                break;
                            }
                            case 0:
                                break;
                            default: {
                                System.out.println("Chuc nang khong hop le!!!");
                                System.out.println("Nhan enter de nhap lai!!!");
                                sc.nextLine();
                            }
                        }
                    } while(sosanhTuoi > 4);
                    break;
                }
                case 2: {
                    do {
                        
                        nhapThanhCong = false;
                        do {
                            //bắt lỗi người dùng nhập chữ
                            try {
                                System.out.print("Nhap luong: ");
                                bolocLuong = sc.nextDouble();
                                sc.nextLine(); //Xoá kí tự enter trong buffer
                                if(bolocLuong > 0) {
                                    nhapThanhCong = true;
                                } else {
                                    System.out.println("Luong phai lon hon 0!");
                                }
                            } catch (InputMismatchException e) {
                                System.err.println("Vui long nhap so!!!");
                                sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                            }
                        } while(!nhapThanhCong);
                        System.out.println("1. Lon hon hoac bang");
                        System.out.println("2. Be hon hoac bang");
                        System.out.println("3. Bang");
                        System.out.println("4. Xoa bo loc");
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
                            case 4: {
                                bolocLuong = 0;
                                sosanhLuong = 0;
                            }
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
            if(!dsnv[i].getMaNV().equals(ma) && !ma.isBlank()) 
                continue;
            if(!dsnv[i].getHoVaTen().toLowerCase().contains(hovaten.toLowerCase()) && !hovaten.isBlank())
                continue;
            if(!dsnv[i].getSDT().contains(sdt) && !sdt.isBlank())
                continue;
            if(!dsnv[i].getChucVu().toLowerCase().contains(chucvu.toLowerCase()) && !chucvu.isBlank())
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
        boolean timNV = false;
        for(int i = 0; i < n; i++) {
            if(dsnv[i].getMaNV().equals(ma)) {
                timNV = true;
                int chucnang = 0;
                do {
                    clearScreen();
                    System.out.println("1. Sua ten");
                    System.out.println("2. Sua tuoi");
                    System.out.println("3. Sua so dien thoai");
                    System.out.println("4. Sua chuc vu");
                    System.out.println("5. Sua luong");
                    System.out.println("6. Sua tat ca");
                    System.out.println("0. Thoat");
                    boolean nhapThanhCong = false;
                    do {
                        //bắt lỗi người dùng nhập chữ
                        try {
                            System.out.print("Nhap tinh nang: ");
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
                            dsnv[i].setHoVaTen();
                            suaThanhCong = true;
                            break;
                        case 2:
                            dsnv[i].setTuoi();
                            suaThanhCong = true;
                            break;
                        case 3:
                            dsnv[i].setSDT();
                            suaThanhCong = true;
                            break;
                        case 4:
                            dsnv[i].setChucVu();
                            suaThanhCong = true;
                            break;
                        case 5: 
                            dsnv[i].setLuong();
                            suaThanhCong = true;
                            break;
                        case 6:
                            dsnv[i].setInfo();
                            suaThanhCong = true;
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
                if(suaThanhCong) {
                    dataChange = true;
                    System.out.println("Sua thanh cong!!!");
                    System.out.println("Nhan enter de quay lai!!!");
                    sc.nextLine();
                }
                break;
            }
        }
        if(!timNV) {
            System.out.println("Khong tim thay nhan vien nay!!!");
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
