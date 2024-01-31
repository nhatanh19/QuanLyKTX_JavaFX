package filefxml.quanlyktx_fx_version2;

import filefxml.quanlyktx_fx_version2.Database.ConnectDB;
import filefxml.quanlyktx_fx_version2.API.ConnectGmail;
import filefxml.quanlyktx_fx_version2.Model.NguoiDung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class DangKyNguoiDungController implements Initializable {
    @FXML
    private ChoiceBox<String> CBox_DayNha;

    @FXML
    private ChoiceBox<String> CBox_Phong;

    @FXML
    private ChoiceBox<String> CBox_LoaiPhong;

    @FXML
    private ChoiceBox<String> CBox_Tang;

    @FXML
    private Button bt_DangKyThongTin;

    @FXML
    private Button bt_TimKiemMaDinhDanh;

    @FXML
    private Button bt_XoaDuLieuTrenGiaoDien;

    @FXML
    private CheckBox checkbox_Nam;

    @FXML
    private CheckBox checkbox_Nu;

    @FXML
    private CheckBox checkbox_TrangThaiThanhToan;

    @FXML
    private DatePicker date_NgaySinh;

    @FXML
    private DatePicker date_NgayThuePhong;

    @FXML
    private DatePicker date_NgayTraPhong;

    @FXML
    private Label lb_TienPhong1Thang;

    @FXML
    private Label lb_TongTienThanhToan;

    @FXML
    private TextField tf_Email;

    @FXML
    private TextField tf_HoVaTen;

    @FXML
    private TextField tf_MaDinhDanh;




    @FXML
    private TextField tf_QueQuan;

    @FXML
    private TextField tf_SDT;


    private String Day;
    private String Tang;
    private String LoaiPhong;
    private String Phong;
    private int GiaPhong;
    private String textTongTien = "0";
    private ArrayList<String> listDayKTX;
    boolean checkThongTinNguoiDung;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkThongTinNguoiDung = true;
        this.listDayKTX = new ArrayList<>();
        ArrayList<String> listTangKTX = new ArrayList<>();
        ArrayList<String> listPhongKTX = new ArrayList<>();
        ArrayList<String> listLoaiPhongKTX = new ArrayList<>();

        //Lấy dữ liệu về dãy nhà khi mà giao diện được khởi động
        LayDuLieuVeDayNha();


        CBox_DayNha.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                listTangKTX.clear();
                CBox_Tang.getItems().clear();
                setDay(CBox_DayNha.getValue());
                try {
                    String queryTang = "SELECT DISTINCT `Tang` FROM `phong` WHERE Day = '"+getDay()+"'";
                    //System.out.println(queryTang);
                    ResultSet TangKTX = new ConnectDB().getStmt().executeQuery(queryTang);
                    while(TangKTX.next()){
                        listTangKTX.add(TangKTX.getString("Tang"));
                    }
                    CBox_Tang.getItems().addAll(listTangKTX);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        CBox_Tang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listLoaiPhongKTX.clear();
                CBox_LoaiPhong.getItems().clear();
                setTang(CBox_Tang.getValue());
                try {
                    String queryLoaiPhong = "SELECT DISTINCT `LoaiPhong` FROM `phong` WHERE Day = '"+getDay()+"' AND Tang = '"+getTang()+"' ";
                    //System.out.println(queryLoaiPhong);
                    ResultSet LoaiPhongKTX = new ConnectDB().getStmt().executeQuery(queryLoaiPhong);
                    while(LoaiPhongKTX.next()){
                        listLoaiPhongKTX.add(LoaiPhongKTX.getString("LoaiPhong"));
                    }
                    CBox_LoaiPhong.getItems().addAll(listLoaiPhongKTX);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        CBox_LoaiPhong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listPhongKTX.clear();
                CBox_Phong.getItems().clear();
                setLoaiPhong(CBox_LoaiPhong.getValue());
                try {
                    String queryMaPhong = "SELECT DISTINCT `MaSoPhong`FROM `phong` WHERE Day = '"+getDay()+"' AND Tang = '"+getTang()+"' AND TinhTrangSuDung = 'true' AND LoaiPhong = '"+getLoaiPhong()+"' ";
                    //System.out.println(queryMaPhong);
                    ResultSet PhongKTX = new ConnectDB().getStmt().executeQuery(queryMaPhong);
                    while(PhongKTX.next()){
                        listPhongKTX.add(PhongKTX.getString("MaSoPhong"));
                    }
                    CBox_Phong.getItems().addAll(listPhongKTX);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        CBox_Phong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ClearDateThuePhong(); //Xoa du lieu ngay thang

                String GiaPhong1Thang = "0";
                setPhong(CBox_Phong.getValue());
                try {
                    String queryTienPhong = "SELECT DISTINCT `GiaPhong` FROM `phong` WHERE Day = '"+getDay()+"' AND Tang = '"+getTang()+"' AND MaSoPhong = '"+ getPhong()+"' ";
                    //System.out.println(queryTienPhong);
                    ResultSet GiaPhong = new ConnectDB().getStmt().executeQuery(queryTienPhong);
                    while(GiaPhong.next()){
                        GiaPhong1Thang = GiaPhong.getString("GiaPhong");
                    }
                    lb_TienPhong1Thang.setText(GiaPhong1Thang + ".000 VND");
                    setGiaPhong(Integer.parseInt(GiaPhong1Thang));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        checkbox_Nam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                if(checkbox_Nam.isSelected()){
//                    checkbox_Nu.setSelected(false);
//                }
                checkbox_Nu.setSelected(false);
            }
        });

        checkbox_Nu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                if(checkbox_Nu.isSelected()){
//                    checkbox_Nam.setSelected(false);
//                }
                checkbox_Nam.setSelected(false);
            }
        });

        bt_XoaDuLieuTrenGiaoDien.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearGiaoDien();
            }
        });

        date_NgayThuePhong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(date_NgayTraPhong.getValue() != null && date_NgayThuePhong.getValue() != null){
                    String textTongTien = String.valueOf((TinhNgayThanhToan(date_NgayThuePhong, date_NgayTraPhong))*1000);
                    setTextTongTien(textTongTien);
                    lb_TongTienThanhToan.setText(textTongTien + " VND");
                }
            }
        });
        date_NgayTraPhong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(date_NgayTraPhong.getValue() != null && date_NgayThuePhong.getValue() != null){
                    String textTongTien = String.valueOf((TinhNgayThanhToan(date_NgayThuePhong, date_NgayTraPhong))*1000);
                    setTextTongTien(textTongTien);
                    lb_TongTienThanhToan.setText(textTongTien + " VND");
                }
            }
        });


        bt_DangKyThongTin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NguoiDung TTNguoiDung = InputDuLieuOfGiaoDien();
                if(TTNguoiDung.getHoVaTen() == null || date_NgayThuePhong.getValue() == null || date_NgayTraPhong.getValue() == null){
                    DBUtils.printAlertMsg("THONG BAO", "Vui lòng nhập đầy đủ thông tin vào các ô dữ liệu!\nKhông được để trống!");

                }else {
                    String sqlQueryInsertDataNguoiDung = "INSERT INTO `thongtinsinhvien`(`HoVaTen`, `MaSoDinhDanh`, `SoDienThoai`, `NgaySinh`, `GioiTinh`, `QueQuan`, `Email`) VALUES " +
                            "('"+TTNguoiDung.getHoVaTen()+"','"+TTNguoiDung.getMaDinhDanh()+"','"+TTNguoiDung.getSDT()+"','"+TTNguoiDung.getNgaySinh()+"','"+TTNguoiDung.getGioiTinh()+"','"+TTNguoiDung.getQueQuan()+"','"+TTNguoiDung.getEmail()+"')";
                    //System.out.println(sqlQueryInsertDataNguoiDung); // Test
                    String idHoaDon = maHoaDon();

                    String sqlQueryDangKyHoatDongThuePhong = "INSERT INTO `quanlyhoatdongthuephong`(`MaSoDinhDanh`, `MaSoPhong`, `Tang`, `Day`, `IdHoaDon`, `NgayNhanPhong`, `NgayTraPhong`) " +
                            "VALUES ('"+tf_MaDinhDanh.getText()+"','"+getPhong()+"','"+getTang()+"','"+getDay()+"','"+ idHoaDon +"','"+date_NgayThuePhong.getValue()+"','"+date_NgayTraPhong.getValue()+"')";
                    System.out.println(sqlQueryDangKyHoatDongThuePhong); //Test
                    String TrangThaiThanhToan = "NO";
                    if(checkbox_TrangThaiThanhToan.isSelected()){
                        TrangThaiThanhToan = "YES";
                    }
                    String sqlQueryHoaDonThuePhong = "INSERT INTO `hoadonthuephong`(`IdHoaDon`, `TongTienPhong`, `DaThanhToan`) " +
                            "VALUES ('"+idHoaDon+"','"+getTextTongTien()+"','"+TrangThaiThanhToan+"')";

                    try {
                        int checkQueryDangKyNguoiDung = 0;
                        try {
                            if(checkThongTinNguoiDung){
                                checkQueryDangKyNguoiDung = new ConnectDB().getStmt().executeUpdate(sqlQueryInsertDataNguoiDung); // Đăng ký thông tin người dùng
                            }else checkQueryDangKyNguoiDung = 1; // Cho chương trình tiếp tục chạy

                        }catch (SQLException e){
                            DBUtils.printAlertMsg("THÔNG BÁO", "Đã tồn tại hồ sơ đăng ký này!");
                            return;
                        }

                        int checkQueryDangKyHoatDong = 0; // Đăng ký phòng
                        try {
                            checkQueryDangKyHoatDong = new ConnectDB().getStmt().executeUpdate(sqlQueryDangKyHoatDongThuePhong);
                        }catch (SQLException e){
                            DBUtils.printAlertMsg("THÔNG BÁO", "Phòng đã được đăng ký!\nVui lòng chọn lại phòng.");
                            CBox_Phong.setValue(null);
                        }

                        // Truy vấn lên các bảng
                        int checkQueryDangKyHoaDonThuePhong =  new ConnectDB().getStmt().executeUpdate(sqlQueryHoaDonThuePhong); // Đăng ký hoá đơn, thiết lập mã hoá đơn cho đơn đăng ký
                        int checkQueryDangKyHoaDonTienNuoc = new ConnectDB().getStmt().executeUpdate("INSERT INTO `hoadontiennuoc`(`IdHoaDon`, `TongTienNuoc`) VALUES ('"+idHoaDon+"','"+TinhHoaDonTienNuoc(date_NgayThuePhong, date_NgayTraPhong)+"')");

                        //Kiểm tra các phòng đã đủ người chưa , nếu đủ, khoá phòng
                        int checkQuerySetTrangThaiPhong = 0;
                        ResultSet checkPhong = new ConnectDB().getStmt().executeQuery("SELECT `SoNguoiTrongPhong`, `LoaiPhong` FROM `phong` WHERE MaSoPhong = '"+getPhong()+"' AND Day = '"+getDay()+"' AND Tang = '"+getTang()+"'");
                        // Đọc dữ liệu của object checkPhong
                        int SoNguoiTrongPhong = 0;
                        int LoaiPhong = 0;
                        while (checkPhong.next()){
                            SoNguoiTrongPhong = checkPhong.getInt("SoNguoiTrongPhong");
                            LoaiPhong = checkPhong.getInt("LoaiPhong");
                        }

                        int New_SoNguoiTrongPhong = (SoNguoiTrongPhong + 1 );
                        //Tăng số người trong phòng lên rồi kiểm ra
                        new ConnectDB().getStmt().executeUpdate("UPDATE `phong` SET `SoNguoiTrongPhong`='"+New_SoNguoiTrongPhong+"' WHERE MaSoPhong = '"+getPhong()+"' AND Day = '"+getDay()+"' AND Tang = '"+getTang()+"' ");
                        System.out.println("SoNguoiTrongPhong = " + New_SoNguoiTrongPhong + " && LoaiPhong = " + LoaiPhong + "\n");
                        if(New_SoNguoiTrongPhong == LoaiPhong){
                            checkQuerySetTrangThaiPhong = new ConnectDB().getStmt().executeUpdate("UPDATE `phong` SET `TinhTrangSuDung`='false' WHERE MaSoPhong = '"+getPhong()+"' AND Day = '"+getDay()+"' AND Tang = '"+getTang()+"'");
                        }
                        if(checkQueryDangKyNguoiDung > 0 && checkQueryDangKyHoatDong > 0 && checkQueryDangKyHoaDonThuePhong >0 && checkQueryDangKyHoaDonTienNuoc > 0 ){
                            DBUtils.printAlertMsg("THÔNG BÁO", "Đăng ký thành công!\nThông tin xác nhận đã được gửi đến email của bạn.");
                            //Gửi email thông báo xác nhận chi tiết
                            String TextEmail = "Chào "+TTNguoiDung.getHoVaTen()+",\n" +
                                    "Bạn đã hoàn thành thủ tục đăng ký phòng ký túc xá thành công.\n" +
                                    "\n" +
                                    "THÔNG TIN BẢN THÂN:\n" +
                                    "Họ và tên: " +TTNguoiDung.getHoVaTen()+
                                    "\nNgày sinh: " +TTNguoiDung.getNgaySinh()+
                                    "\nGiới tính: " +TTNguoiDung.getGioiTinh() +
                                    "\nQuê quán: " + TTNguoiDung.getQueQuan()+
                                    "\nSố CMND/CCCD:  "+ TTNguoiDung.getMaDinhDanh() +
                                    "\nĐiện thoại: " + TTNguoiDung.getSDT() +
                                    "\n" +
                                    "THÔNG TIN PHÒNG:\n" +
                                    "Dãy: " + getDay()+
                                    "\nTầng: " + getTang() +
                                    "\nMã phòng: " + getPhong() +
                                    "\nLoại phòng: "+getLoaiPhong()+ " người\n" +
                                    "Giá thuê 1 tháng: "+getGiaPhong()+".000 VND\n" +
                                    "\n" +
                                    "THÔNG TIN THANH TOÁN TIỀN PHÒNG:\n" +
                                    "Mã hoá đơn: " + idHoaDon +
                                    "\nTổng tiền phải thanh toán: " + getTextTongTien() +
                                    "\nĐã thanh toán: " + TrangThaiThanhToan +
                                    "\nThời gian đăng ký phòng: " + date_NgayThuePhong.getValue() +
                                    "\nThời gian trả phòng: " + date_NgayTraPhong.getValue();
                            new ConnectGmail().SendEmail(TTNguoiDung.getEmail() ,"Đăng ký phòng ký túc xá thành công", TextEmail);
                            clearGiaoDien();
                            LayDuLieuVeDayNha();
                        }else {
                            DBUtils.printAlertMsg("THÔNG BÁO", "Đăng ký không thành công!");
                        }
                    } catch (SQLException e) {
                        DBUtils.printAlertMsg("THÔNG BÁO", "Mã hoá đơn bị trùng!\nVui lòng nhấn nút 'Đăng ký' lại!");
                        throw new RuntimeException(e);

                    }
                }
            }
        });

        bt_TimKiemMaDinhDanh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String querySelectDataOfTableSinhVien = "SELECT `HoVaTen`, `SoDienThoai`, `NgaySinh`, `GioiTinh`, `QueQuan`, `Email` FROM `thongtinsinhvien` WHERE MaSoDinhDanh = '"+tf_MaDinhDanh.getText()+"'";
                try {
                    ResultSet dataSinhVien = new ConnectDB().getStmt().executeQuery(querySelectDataOfTableSinhVien);
                    if(dataSinhVien.next()){
                        checkThongTinNguoiDung = false;
                        tf_Email.setText(dataSinhVien.getString("Email"));
                        tf_HoVaTen.setText(dataSinhVien.getString("HoVaTen"));
                        tf_SDT.setText(dataSinhVien.getString("SoDienThoai"));
                        tf_QueQuan.setText(dataSinhVien.getString("QueQuan"));
                        date_NgaySinh.setValue(dataSinhVien.getDate("NgaySinh").toLocalDate());
                        if(dataSinhVien.getString("GioiTinh").equals("Nam")){
                            checkbox_Nam.setSelected(true);
                        }else {
                            checkbox_Nu.setSelected(true);
                        }
                    }else {
                        DBUtils.printAlertMsg("THÔNG BÁO", "Mã định danh này chưa được đăng ký trên hệ thống!");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
    public void ClearDateThuePhong(){
        date_NgayThuePhong.setValue(null);
        date_NgayTraPhong.setValue(null);
    }
    public float TinhHoaDonTienNuoc(DatePicker ngayDangKy, DatePicker ngayTraPhong){
        float TongTien = 0.0F;
        int year = ngayTraPhong.getValue().getYear() - ngayDangKy.getValue().getYear();
        int month = ngayTraPhong.getValue().getMonthValue() - ngayDangKy.getValue().getMonthValue();
        int day = ngayTraPhong.getValue().getDayOfMonth() - ngayDangKy.getValue().getDayOfMonth();
        TongTien = ((year*12 + month)*30 + day)*1000;
        return TongTien;
    }
    public float TinhNgayThanhToan(DatePicker ngayDangKy, DatePicker ngayTraPhong){
        float TongTien = 0.0F;
        int year = ngayTraPhong.getValue().getYear() - ngayDangKy.getValue().getYear();
        int month = ngayTraPhong.getValue().getMonthValue() - ngayDangKy.getValue().getMonthValue();
        int day = ngayTraPhong.getValue().getDayOfMonth() - ngayDangKy.getValue().getDayOfMonth();
        TongTien = (year*12 + month)*getGiaPhong() + day*(getGiaPhong()/30);
        return TongTien;
    }
    public void clearGiaoDien(){
        tf_HoVaTen.clear();
        tf_Email.clear();
        tf_SDT.clear();
        tf_MaDinhDanh.clear();
        tf_QueQuan.clear();
        date_NgaySinh.setValue(null);
        date_NgayThuePhong.setValue(null);
        date_NgayTraPhong.setValue(null);
        CBox_Phong.getItems().clear();
        CBox_Tang.getItems().clear();
        CBox_LoaiPhong.getItems().clear();
        CBox_DayNha.getItems().clear();
        checkbox_Nam.setSelected(false);
        checkbox_Nu.setSelected(false);
        lb_TongTienThanhToan.setText("0.00 VND");
        lb_TienPhong1Thang.setText("0.00 VND");
        checkbox_TrangThaiThanhToan.setSelected(false);
    }

    public void LayDuLieuVeDayNha(){
        this.listDayKTX.clear();
        try {
            ResultSet dayNhaKTX = new ConnectDB().getStmt().executeQuery("SELECT DISTINCT `Day` FROM `phong`");
            while(dayNhaKTX.next()){
                this.listDayKTX.add(dayNhaKTX.getString("Day"));
            }
            CBox_DayNha.getItems().addAll(this.listDayKTX);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public NguoiDung InputDuLieuOfGiaoDien(){
        String GioiTinh = null;
        if(checkbox_Nam.isSelected() == true){
            GioiTinh = "Nam";
        }else GioiTinh = "Nữ";
        if(tf_HoVaTen.getText() != null && tf_Email.getText() != null && tf_SDT.getText() != null && tf_MaDinhDanh.getText() != null && tf_QueQuan.getText() != null  && date_NgaySinh.getValue() != null && checkbox_Nam.isSelected() == true || checkbox_Nu.isSelected() == true){
            return new NguoiDung(tf_HoVaTen.getText() , tf_Email.getText(), tf_SDT.getText(), tf_MaDinhDanh.getText(), tf_QueQuan.getText(), date_NgaySinh.getValue(), GioiTinh );
        }
        return new NguoiDung();
    }

    public String maHoaDon(){
        //int year = date_NgayThuePhong.getValue().getYear();
        int year = LocalDate.now().getYear();
        int month = date_NgayThuePhong.getValue().getMonthValue();
        Random random = new Random();
        // Khởi tạo một chuỗi để lưu trữ dãy mã
        StringBuilder code = new StringBuilder();
        // Sinh ngẫu nhiên 10 chữ số và thêm vào chuỗi mã
        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10); // Sinh số nguyên từ 0 đến 9
            code.append(digit);
        }
        return year +"KTX"+ month + code;
    }
    public String getPhong() {
        return Phong;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }



    public String getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        LoaiPhong = loaiPhong;
    }

    public String getDay() {
        return Day;
    }

    public String getTang() {
        return Tang;
    }

    public void setDay(String day) {
        Day = day;
    }

    public void setTang(String tang) {
        Tang = tang;
    }

    public int getGiaPhong() {
        return GiaPhong;
    }

    public void setGiaPhong(int giaPhong) {
        GiaPhong = giaPhong;
    }

    public String getTextTongTien() {
        return textTongTien;
    }

    public void setTextTongTien(String textTongTien) {
        this.textTongTien = textTongTien;
    }

}
