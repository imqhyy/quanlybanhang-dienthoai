package com.model;

import com.list.DanhSachKhachHang;
import com.list.DanhSachNhanVien;
import com.list.DanhSachSmartphone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Locale;
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
    private BigDecimal tongSP;
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
        //Tránh giá trị âm, nếu âm sẽ tự động chuyển thành 0 để tạo mảng rỗng
        if(n < 0) n = 0;
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

    //region get
    public void getInfo() {
        System.out.println("------Thong tin don hang------");
        System.out.println("Ma don hang: " + maDH);
        System.out.println("Ngay dat: " + ngayDat.format(DATE_FORMATTER));
        System.out.println("Ma khach hang: " + (KH != null ? KH.getMaKH() : "N/A"));
        System.out.println("Ho ten: " + (KH != null ? KH.getHoVaTen() : "N/A"));
        System.out.println("Ma nhan vien: " + (NV != null ? NV.getMaNV() : "N/A"));
        System.out.println("Ho ten: " + (NV != null ? NV.getHoVaTen() : "N/A"));
        System.out.println("STT   Ma san pham   Ten san pham                  Don gia            SL");
        for(int i = 0; i < n; i++) {
            //Khoảng cách từ S đến M là 6 ô, đoạn này tính toán để stt chiếm 6 ô
            System.out.print(i + 1);
            for(int j = 0; j < 6 - Integer.toString(i + 1).length();j++) {
                System.out.print(" ");
            }
            if(dsMua[i] != null)
            {
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
                //Khoảng cách từ D đến S là 19, đoạn này tính toán đơn giá chiếm 19 ô
                System.out.print(dsMua[i].getStringGiaBan());
                for(int j = 0; j < 19 - dsMua[i].getStringGiaBan().length();j++) {
                    System.out.print(" ");
                }
                System.out.println(soluongSP[i]);
            } else {
                System.out.println("San pham da bi xoa!");
            }
        }
        System.out.println("Tong san pham: " + getStringTongSP());
    }

    public String getMaDH() {
        return maDH;
    }
    //endregion 

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

    public BigDecimal getTongSP() {
        return tongSP;
    }

    public String getStringTongSP() {
        // 1. Định nghĩa mẫu định dạng (#,###) và Locale
        // Locale Việt Nam đảm bảo sử dụng dấu chấm phân cách hàng nghìn.
        Locale vietNam = Locale.of("vi", "VN");
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(vietNam);

        // 2. Đặt mẫu (Pattern) thủ công:
        // " VND" là tiền tố/hậu tố.
        df.applyPattern("#,### VND");
        return df.format(tongSP);
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
    //endregion

    
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
            
            boolean nhapThanhCong = false;
            do {
                System.out.print("So luong: ");
                //bắt lỗi người dùng nhập chữ
                try {
                    soluongSP[i] = sc.nextInt();
                    sc.nextLine(); //Xoá kí tự enter trong buffer
                    if(soluongSP[i] <= 0 ) {
                        System.out.println("So luong phai lon hon 0!!!");
                    } else
                        nhapThanhCong = true;
                    
                } catch (InputMismatchException e) {
                    System.err.println("Vui long nhap so!!!");
                    sc.nextLine();//Xoá buffer trước khi người dùng nhập lại
                }
            } while(!nhapThanhCong);
        }
        setTongSP();
    }

    public void setN() {
        boolean nhapThanhCong = false;
        do {
            System.out.print("Nhap so san pham: ");
            //bắt lỗi người dùng nhập chữ
            try {
                n = sc.nextInt();
                sc.nextLine(); //Xoá kí tự enter trong buffer
                if(n <= 0) {
                    System.out.println("So san pham phai lon hon 0!!!");
                    System.out.println("Vui long nhap lai!!!");
                } else
                    nhapThanhCong = true;
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
        tongSP = new BigDecimal(0);
        for(int i = 0; i < n; i++) {
            if(dsMua[i] != null)
                tongSP = tongSP.add(dsMua[i].getGiaBan().multiply(new BigDecimal(soluongSP[i])));
        }
    }

    //Hàm kiểm tra xem các hàm input kia có thành công không
    public boolean checkInputDataNotFail() {
        if(!inputDataKH() || !inputDataNV() || !inputDataSP())
            return false;
        return true;
    }
    //Hàm input dữ liệu từ file
    //trả về false nếu dữ liệu có lỗi, true nếu dữ liệu không lỗi
    public boolean inputDataKH() {
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
                                arr[3]
                    ); //Số điện thoại
                    
                    // Kiểm tra xem trong danh sách nhập có mã khách hàng nào bị trùng không
                    KhachHang checkKH = ds2.timkiem(temp.getMaKH());
                    if (checkKH != null) {
                        return false;
                    }
                    //Kiểm tra xem khách hàng temp có tuổi bé hơn hoặc bằng 0 không
                    if(temp.getTuoi() <= 0) {
                        return false;
                    }
                    //Kiểm tra xem số điện thoại có hợp lệ không
                    if(!temp.getSDT().matches("\\d+")) {
                        return false;
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
                dskh = ds2;
                dskh.setDataChange(false);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean inputDataNV() {
        try {
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataNhanVien.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                //Tạo ds2, ghi dữ liệu vào ds2 trước, nếu không lỗi mới ghi vào ds1
                DanhSachNhanVien ds2 = new DanhSachNhanVien();
                while(line != null) {
                    // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    NhanVien temp = new NhanVien(
                                arr[0], //mã nhân viên
                                arr[1], //họ tên
                                Integer.parseInt(arr[2]), //tuổi
                                arr[3], //số điện thoại
                                arr[4], //chức vụ
                                new BigDecimal(arr[5])   //lương
                    ); 
                    //Kiểm tra xem trong danh sách nhập có mã nhân viên nào bị trùng không
                    NhanVien checkNV = ds2.timkiem(temp.getMaNV());
                    if(checkNV != null) {
                        return false;
                    }            
                    //Kiểm tra xem khách hàng temp có tuổi bé hơn hoặc bằng 0 không
                    if(temp.getTuoi() <= 0) {
                        return false;
                    }
                    //Kiểm tra xem số điện thoại có hợp lệ không
                    if(!temp.getSDT().matches("\\d+")) {
                        return false;
                    }
                    //Kiểm tra lương
                    if((temp.getLuong().doubleValue() <= 0)) {
                        return false;
                    }
                    
                    ds2.them(temp);
                    //Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if(Integer.parseInt(temp.getMaNV().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaNV().substring(2));
                    }
                    line = input.readLine();
                }
                //thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID + 1);
                dsnv = ds2;
                dsnv.setDataChange(false);
            }
            
            
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean inputDataSP() {
        try {
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataSmartPhone.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                // Tạo ds2 ghi dữ liệu vào ds2 trước nếu không có lỗi mới ghi vào ds1
                DanhSachSmartphone ds2 = new DanhSachSmartphone();
                while (line != null) {
                    // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    SmartPhone temp = new SmartPhone(
                            arr[0], // maSP
                            arr[1], // tenSP
                            arr[2], // thuonghieu
                            new BigDecimal(arr[3]), // giaBan
                            arr[4], // chipset
                            arr[5], // ram
                            arr[6], // rom
                            arr[7], // manhinh
                            arr[8] // chitiet
                    );

                    //Kiểm tra xem trong danh sách nhập có mã sản phẩm nào bị trùng không
                    SmartPhone checkSP = ds2.timkiem(temp.getMaSP());
                    if(checkSP != null) {
                        return false;
                    }
                    //Kiểm tra giá bán
                    if((temp.getGiaBan().doubleValue() <= 0)) {
                        return false;
                    }
                    
                    ds2.them(temp);
                    // Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if (Integer.parseInt(temp.getMaSP().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaSP().substring(2));
                    }
                    line = input.readLine();
                }
                // thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID + 1);
                dssp = ds2;
                dssp.setDataChange(false);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    // Hàm này dùng để nhập ngày tháng năm
    public static LocalDate nhapNgayThangNam(Scanner sc) {
        LocalDate ngayNhap;
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