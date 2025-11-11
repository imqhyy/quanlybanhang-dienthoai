package com.model;

import com.list.DanhSachKhachHang;
import com.list.DanhSachNhanVien;
import com.list.DanhSachSmartphone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class DonHang {
    private String maDH;
    private LocalDate ngayDat;
    private KhachHang KH;
    private NhanVien NV;
    private DanhSachKhachHang dskh = new DanhSachKhachHang();
    private DanhSachNhanVien dsnv = new DanhSachNhanVien();
    private DanhSachSmartphone dssp = new DanhSachSmartphone();
    private int n; //Số sản phẩm
    private SmartPhone[] dsMua;
    private int[] soluongSP;
    private double tongSP;
    private static final Scanner sc = new Scanner(System.in);
    //Định dạng ngày nhập
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DonHang() {
        inputDataKH();
        inputDataNV();
        inputDataSP();
        maDH = "null";
        ngayDat = null;
        KH = null;
        NV = null;
        n = 0;
        dsMua = new SmartPhone[n];
        soluongSP = new int[n];
        setTongSP();
    }

    public DonHang(String maDH, String ngaydat, String maKH, String maNV, int n, String dataSP) {
        inputDataKH();
        inputDataNV();
        inputDataSP();
        this.maDH = maDH;
        this.ngayDat = LocalDate.parse(ngaydat, DATE_FORMATTER);
        this.KH = dskh.timkiem(maKH);
        this.NV = dsnv.timkiem(maNV);
        this.n = n;
        this.dsMua = new SmartPhone[n];
        this.soluongSP = new int[n];
        //Xử lí chuỗi sản phảm
        //data ban đầu: SP1&3|SP2&2
        String[] arr = dataSP.split("\\|"); //tách thành cụm String[] arr = {"SP1&3", "SP2&2"}
        for(int i = 0; i < n; i++) {
            String[] arr2 = arr[i].split("&"); //tách thành mã sản phẩm và số luọng String[] arr2 = {"SP1", "3"}
            dsMua[i] = dssp.timkiem(arr2[0]);
            soluongSP[i] = Integer.parseInt(arr2[1]);
        }
        setTongSP();
    }
    
    public void getInfo() {
        System.out.println("------Thong tin don hang------");
        System.out.println("Ma don hang: " + maDH);
        System.out.println("Ngay dat: " + ngayDat.format(DATE_FORMATTER));
        System.out.println("Ma khach hang: " + KH.getMaKH());
        System.out.println("Ho ten: " + KH.getHoVaTen());
        System.out.println("Ma nhan vien: " + NV.getMaNV());
        System.out.println("Ho ten: " + NV.getHoVaTen());
        System.out.println("Ma san pham   Ten san pham                  Don gia     SL");
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ M đến T là 14, đoạn này tính toán để mã Sản phẩm chiếm 14 ô
            System.out.print(dsMua[i].getMaSP());
            for(int j = 0; j < 14 - dsMua[i].getMaSP().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ T đến D là 30, đoạn này tính toán để tên Sản phẩm chiếm 30 ô
            System.out.print(dsMua[i].getTenSP());
            for(int j = 0; j < 30 - dsMua[i].getTenSP().length();j++) {
                System.out.print(" ");
            }
            //Khoảng cách từ D đến L là 12, đoạn này tính toán để tên Thương hiệu chiếm 12 ô
            System.out.print(dsMua[i].getGiaBan());
            for(int j = 0; j < 12 - Double.toString(dsMua[i].getGiaBan()).length();j++) {
                System.out.print(" ");
            }
            System.out.println(soluongSP[i]);
        }
        System.out.println("Tong san pham: " + tongSP);
    }

    public String getMaDH() {
        return maDH;
    }

    public String getNgayDat() {
        return ngayDat.format(DATE_FORMATTER);
    }

    public KhachHang getKH() {
        return KH;
    }

    public NhanVien getNV() {
        return NV;
    }

    public SmartPhone[] getDsMua() {
        return dsMua;
    }

    public int[] getSoLuongSP() {
        return soluongSP;
    }
    
    public int getN() {
        return n;
    }

    public double getTongSP() {
        return tongSP;
    }

    public String getDataSP() {
        String data = "";
        for(int i = 0; i < n; i++) {
            if(i != 0) data = data + "|";
            data = data + dsMua[i].getMaSP() + "&" + soluongSP[i];
        }

        return data;
    }
  

    public void setInfo(int seedID) {
        System.out.println("\n------Nhap thong tin don hang------");
        String ma = "DH";
        ma = ma + Integer.toString(seedID);
        maDH = ma;
        System.out.println("Ma don hang: " + maDH);
        setNgayDat();
        KH = setKH();
        NV = setNV();
        setSP();
    }

    public void setInfo() {
        System.out.println("\n------Nhap thong tin don hang------");
        System.out.println("Ma don hang: " + maDH);
        setNgayDat();
        KH = setKH();
        NV = setNV();
        setSP();
    }

    //Đảm bảo tính duy nhất của mã sản phẩm sẽ không có hàm setMaSP
    public void setNgayDat() {
        System.out.print("Nhap ngay dat hang");
        this.ngayDat = nhapNgayThangNam(sc);
    }

    public KhachHang setKH() {
        String ma;
        dskh.DanhSachKHmini();
        do {
            System.out.print("Ma khach hang: ");
            ma = sc.nextLine();
            KH = dskh.timkiem(ma);
            if(KH == null) {
                System.out.println("Ma khach hang khong hop le vui long kiem tra lai!");
                System.out.println("Vui long nhap lai!");
            }
        } while(KH == null);

        return KH;
    }


    public NhanVien setNV() {
        String ma;
        dsnv.DanhSachNVmini();
        do {
            System.out.print("Ma nhan vien: ");
            ma = sc.nextLine();
            NV = dsnv.timkiem(ma);
            if(NV == null) {
                System.out.println("Ma nhan vien khong hop le vui long kiem tra lai!");
                System.out.println("Vui long nhap lai!");
            }
        } while(NV == null);
        return NV;
    }
    
    //Trong setSP đã bao gồm set dsMua, set soluongSP, set n
    public void setSP() {
        String ma;
        dssp.DanhSachSPmini();
        System.out.println("--Nhap san pham--");
        setN();
        for(int i = 0; i < n; i++) {
            System.out.println("San pham " + (i + 1));
            do {
                System.out.print("Ma san pham: ");
                ma = sc.nextLine();
                dsMua[i] = dssp.timkiem(ma);
                if(dsMua[i] == null) {
                    System.out.println("Ma san pham khong hop le!");
                    System.out.println("Vui long nhap lai!");
                }
            } while(dsMua[i] == null);
            
            System.out.print("So luong: ");
            boolean nhapThanhCong = false;
            do {
                //bắt lỗi người dùng nhập chữ
                try {
                    soluongSP[i] = sc.nextInt();
                    nhapThanhCong = true;
                    sc.nextLine(); //Xoá kí tự enter trong buffer
                } catch (InputMismatchException e) {
                    System.err.println("Vui long nhap so!!!");
                    sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                }
            } while(!nhapThanhCong);
        }
        setTongSP();
    }

    public void setN() {
        System.out.print("Nhap so san pham: ");
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
        dsMua = new SmartPhone[n];
        soluongSP = new int[n];
    }

    public void setTongSP() {
        //Set giá trị 0 trước mỗi lần tính toán lại
        tongSP = 0;
        for(int i = 0; i < n; i++) {
            tongSP = tongSP + dsMua[i].getGiaBan() * soluongSP[i];
        }
    }

    //Hàm input dữ liệu từ file
    public void inputDataKH() {
        if(dskh.xuatN() != 0) {
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
        try {
            try(BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataKhachHang.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                //Tạo ds2, ghi dữ liệu vào ds2 trước, nếu không lỗi mới ghi vào ds1
                DanhSachKhachHang ds2 = new DanhSachKhachHang();
                while(line != null) {
                    //Chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    KhachHang temp = new KhachHang(arr[0], arr[1], Integer.parseInt(arr[2]), arr[3]);
                    //Kiểm tra xem trong danh sách nhập có mã khách hàng nào bị trùng không
                    KhachHang checkKH = ds2.timkiem(temp.getMaKH());
                    if(checkKH != null) {
                        System.out.println("Ma khach hang " + temp.getMaKH() + " trong file bi trung lap!");
                        System.out.println("Vui long kiem tra lai du lieu trong file data!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        return;
                    }
                    ds2.them(temp);
                    //Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if(Integer.parseInt(temp.getMaKH().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaKH().substring(2));
                    }
                    line = input.readLine();
                }
                //thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID++);
                dskh = ds2;
                // System.out.println("Tai du lieu tu file thanh cong!!!");
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Khong tim thay file!!!");
        }
    }

    public void inputDataNV() {
        if(dsnv.xuatN() != 0) {
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
        try {
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataNhanVien.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                //Tạo ds2, ghi dữ liệu vào ds2 trước, nếu không lỗi mới ghi vào ds1
                DanhSachNhanVien ds2 = new DanhSachNhanVien();
                while(line != null) {
                    // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    NhanVien temp = new NhanVien(arr[0], arr[1], Integer.parseInt(arr[2]), arr[3], arr[4], Double.parseDouble(arr[5]));
                    //Kiểm tra xem trong danh sách nhập có mã nhân viên nào bị trùng không
                    NhanVien checkNV = ds2.timkiem(temp.getMaNV());
                    if(checkNV != null) {
                        System.out.println("Ma nhan vien " + temp.getMaNV() + " trong file bi trung lap!");
                        System.out.println("Vui long kiem tra lai du lieu trong file data!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        return;
                    }
                    ds2.them(temp);
                    //Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if(Integer.parseInt(temp.getMaNV().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaNV().substring(2));
                    }
                    line = input.readLine();
                }
                //thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID++);
                dsnv = ds2;
                // System.out.println("Tai du lieu tu file thanh cong!!!");
            }
            
            
            
        } catch (IOException | NumberFormatException e) {
            System.err.println("Khong tim thay file!!!");
        }
    }

    public void inputDataSP() {
        if(dssp.xuatN() != 0) {
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
        try {
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataSmartPhone.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                //Tạo ds2 ghi dữ liệu vào ds2 trước nếu không có lỗi mới ghi vào ds1
                DanhSachSmartphone ds2 = new DanhSachSmartphone();
                while (line != null) {
                    // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
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
                    ds2.them(temp);
                    //Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if(Integer.parseInt(temp.getMaSP().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaSP().substring(2));
                    }
                    line = input.readLine();
                }
                //thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID++);
                dssp = ds2;
                // System.out.println("Tai du lieu tu file thanh cong!!!");
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Khong tim thay file!!!");
        }
    }
    // Hàm này dùng để nhập ngày tháng năm
    public static LocalDate nhapNgayThangNam(Scanner sc) {
        LocalDate ngayNhap = null;
        String inputString;

        do {
            System.out.print("(DD/MM/YYYY): ");
            inputString = sc.nextLine();
            try {
                ngayNhap = LocalDate.parse(inputString, DATE_FORMATTER);
                return ngayNhap;
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Error: Dinh dang ngay nhap khong hop le");
                ngayNhap = null;
            }
        } while (ngayNhap == null);

        return null;
    }
}