package com.service;

import com.list.DanhSachDonHang;
import com.model.DonHang;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuanLyDonHang implements serviceInterface.IMenu, serviceInterface.ILoadSaveData {
    private DanhSachDonHang ds1 = new DanhSachDonHang();
    private static final Scanner sc = new Scanner(System.in);

    public QuanLyDonHang() {
        inputData();
        /**
         * Không nên gọi phương thức trong constructor, vì các phương thức này
         * có thể bị ghi đè bởi class kế thừa, gây lỗi dữ liệu
         * Vì thế thêm final để phương thức inputData này không thể kế thừa
         */
    }

    @Override
    public final void inputData() {
        /**
         * Kiểm tra tính đúng đắn của file data khách hàng, nhân viên, smartphone
         * trước khi input data đơn hàng, vì đơn hàng sẽ tham chiếu trực tiếp đến
         * dữ liệu của các file data trên
         */
        DonHang checkDataNotFail = new DonHang();
        if (!checkDataNotFail.checkInputDataNotFail()) {
            System.out.println("Du lieu tu file khach hang, nhan vien, smartphone co the da bi loi!");
            System.out.println("Vui long kiem tra lai!!");
            System.out.println("Nhan enter de dong thong bao nay!!!");
            sc.nextLine();
            return;
        }

        // Kiểm tra dữ liệu có bị thay đổi không trước khi tải lên 1 dữ liệu mới
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
            try (BufferedReader input = new BufferedReader(new FileReader("src/com/repository/dataDonHang.txt"))) {
                String line = input.readLine();
                int maxSeedID = 0;
                // Tạo ds2 ghi dữ liệu vào ds2 trước nếu không có lỗi mới ghi vào ds1
                DanhSachDonHang ds2 = new DanhSachDonHang();
                while (line != null) {
                    // chia chuỗi thành các chuỗi con phân cách bởi dấu phẩy
                    String[] arr = line.split(",");
                    DonHang temp = new DonHang(
                            arr[0], // mã đơn hàng
                            arr[1], // ngày đặt
                            arr[2], // mã khách hàng
                            arr[3], // mã nhân viên
                            Integer.parseInt(arr[4]), // số sản phẩm
                            arr[5] // data sản phẩm VD: SP1&3|SP2&2
                    );

                    // Kiểm tra xem có mã đơn hàng nào bị trùng không
                    DonHang checkDH = ds2.timkiem(temp.getMaDH());
                    if (checkDH != null) {
                        System.out.println("Don hang " + temp.getMaDH() + " trong file bi trung lap!");
                        System.out.println("Vui long kiem tra lai file data!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        System.out.println("Nhan enter de dong thong bao nay!!!");
                        sc.nextLine();
                        return;
                    }
                    // Kiểm tra số sản phẩm có hợp lệ không
                    if (temp.getN() <= 0) {
                        System.out.println("Don hang " + temp.getMaDH() + " co so san pham be hon 0!");
                        System.out.println("Vui long kiem tra lai file data!!");
                        System.out.println("Du lieu cu se duoc khoi phuc!!!");
                        System.out.println("Nhan enter de dong thong bao nay!!!");
                        sc.nextLine();
                        return;
                    } else {
                        // Kiểm tra xem trong data sản phẩm, có số lượng nào bé hơn 0 không
                        for (int i = 0; i < temp.getN(); i++) {
                            if (temp.getSoLuongSP()[i] <= 0) {
                                System.out.println("Don hang " + temp.getMaDH() + " co so luong san pham be hon 0!");
                                System.out.println("Vui long kiem tra lai file data!!");
                                System.out.println("Du lieu cu se duoc khoi phuc!!!");
                                System.out.println("Nhan enter de dong thong bao nay!!!");
                                sc.nextLine();
                                return;
                            }
                        }
                    }
                    ds2.them(temp);
                    // Lấy seedID lớn nhất trong mảng để dành cho các thao tác thêm
                    if (Integer.parseInt(temp.getMaDH().substring(2)) > maxSeedID) {
                        maxSeedID = Integer.parseInt(temp.getMaDH().substring(2));
                    }
                    line = input.readLine();
                }
                // thêm ++ để tăng seedID hiện tại lên 1 để không trùng
                ds2.setSeedID(maxSeedID + 1);
                ds1 = ds2;
                ds1.setDataChange(false); // Vì dữ liệu đã được làm mới nên chuyển nó về thành false, tức là chưa qua
                                          // chỉnh sửa
                System.out.println("Tai du lieu tu file dataDonHang.txt thanh cong!!!");
                System.out.println("Nhan enter de dong thong bao nay!!!");
                sc.nextLine();
            }
            /**
             * Ở đây có 4 lỗi có thể xảy ra
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
            try (FileWriter fw = new FileWriter("src/com/repository/dataDonHang.txt")) {
                String dulieu = ds1.xuatChuoi();
                if (dulieu == null) {
                    System.out.println("Khong co gi de ghi vao file!!!");
                } else {
                    fw.write(dulieu);
                    System.out.println("Ghi du lieu vao file thanh cong!!!");
                    ds1.setDataChange(false); // Vì dữ liệu được sao lưu nên chuyển về thành change, tức là hiện tại
                                              // không có gì thay đổi
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
            System.out.println("--- QUAN LY DON HANG ---");
            System.out.println("1. Xem danh sach don hang");
            System.out.println("2. Them nhieu don hang");
            System.out.println("3. Them mot don hang");
            System.out.println("4. Xoa don hang");
            System.out.println("5. Sua don hang");
            System.out.println("6. Tim kiem don hang");
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
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }

                case 4: {
                    if (ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachDHmini();
                        System.out.print("Nhap ma don hang can xoa: ");
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
                        ds1.DanhSachDHmini();
                        ds1.sua();
                    }
                    break;
                }

                case 6: {
                    if (ds1.xuatN() == 0) {
                        System.out.println("Danh sach trong!!!");
                    } else {
                        ds1.DanhSachDHmini();
                        DanhSachDonHang kqtimkiem;
                        System.out.print("Nhap ma don hang can tim(nhan enter de bo qua): ");
                        String maSP = sc.nextLine();
                        if (!maSP.equals("")) {
                            kqtimkiem = ds1.timkiemnangcao(maSP, "", "");
                        } else {
                            System.out.print("Nhap ma khach hang can tim(nhan enter de bo qua): ");
                            String maKH = sc.nextLine();
                            System.out.print("Nhap ma nhan vien can tim(nhan enter de bo qua): ");
                            String maNV = sc.nextLine();
                            kqtimkiem = ds1.timkiemnangcao(maSP, maKH, maNV);
                        }

                        kqtimkiem.bolocKetqua();

                    }
                    break;
                }
                case 7: {
                    // Kiểm tra kết quả trả về của hàm
                    inputData();
                    break;
                }
                case 8: {
                    outputData();
                    System.out.println("Nhan Enter de quay lai!!!");
                    sc.nextLine();
                    break;
                }
                case 0: {
                    if(ds1.getDataChange()) {
                        String xacnhan;
                        System.out.println("Ban chua luu nhung thay doi, hay luu de cap nhat du lieu vao he thong!");
                        do {
                                System.out.print("Nhan 'y' de luu, 'n' de huy: ");
                                xacnhan = sc.nextLine();
                                switch (xacnhan) {
                                    case "n":
                                        return;
                                    case "y": {
                                        outputData();
                                        break;
                                    }
                                    default:
                                        System.out.println("Vui long nhan 'y' hoac 'n'!");
                                }
                            } while (!xacnhan.toLowerCase().equals("y") && !xacnhan.toLowerCase().equals("n"));
                    }
                    break;
                }
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
