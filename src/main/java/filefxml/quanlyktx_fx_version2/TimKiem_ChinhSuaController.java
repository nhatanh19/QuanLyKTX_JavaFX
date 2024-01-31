package filefxml.quanlyktx_fx_version2;

import filefxml.quanlyktx_fx_version2.Database.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import filefxml.quanlyktx_fx_version2.Model.HoatDongThuePhong;
import javafx.scene.control.cell.PropertyValueFactory;


public class TimKiem_ChinhSuaController implements Initializable {

    @FXML
    private Button bt_ChinhSuaThongTin;
    @FXML
    private CheckBox checkbox_Nam;

    @FXML
    private CheckBox checkbox_Nu;

    @FXML
    private DatePicker date_NgaySinh;

    @FXML
    private TableView<HoatDongThuePhong> table_HienThiThongTin;
    @FXML
    private TableColumn<HoatDongThuePhong,String> tb_cl_Day;

    @FXML
    private TableColumn<HoatDongThuePhong, String> tb_cl_MaHoaDon;

    @FXML
    private TableColumn<HoatDongThuePhong, String> tb_cl_MaPhong;

    @FXML
    private TableColumn<HoatDongThuePhong, String> tb_cl_NgayNhanPhong;

    @FXML
    private TableColumn<HoatDongThuePhong, String> tb_cl_NgayTraPhong;

    @FXML
    private TableColumn<HoatDongThuePhong, String> tb_cl_Tang;

    @FXML
    private TableColumn<HoatDongThuePhong, String> tb_cl_TrangThai;

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

    @FXML
    private Button bt_TimKiem;
    @FXML
    private TextField tf_MaDinhDanhTimKiem;

    private ObservableList<HoatDongThuePhong> ListHoatDongThuePhong = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bt_TimKiem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ListHoatDongThuePhong.clear();
                String querySelectDataOfTableSinhVien = "SELECT `HoVaTen`, `MaSoDinhDanh`, `SoDienThoai`, `NgaySinh`, `GioiTinh`, `QueQuan`, `Email` FROM `thongtinsinhvien` WHERE MaSoDinhDanh = '"+tf_MaDinhDanhTimKiem.getText()+"'";
                String querySelectDataOfTableHoatDongThuePhong = "SELECT `MaSoDinhDanh`, `MaSoPhong`, `Tang`, `Day`, `IdHoaDon`, `NgayNhanPhong`, `NgayTraPhong` FROM `quanlyhoatdongthuephong` WHERE MaSoDinhDanh = '"+tf_MaDinhDanhTimKiem.getText()+"' ";
                try {
                    //Truy vấn đến cơ sở dữ liệu lấy về dữ liệu trong 2 bảng là 'quanlyhoatdongthuephong' và 'thongtinsinhvien'
                    ResultSet data = new ConnectDB().getStmt().executeQuery(querySelectDataOfTableHoatDongThuePhong);
                    ResultSet dataSinhVien = new ConnectDB().getStmt().executeQuery(querySelectDataOfTableSinhVien);

                    //Check xem có tồn tại dữ liệu của mã số định danh hay ko?
                    if(!dataSinhVien.next()){
                        DBUtils.printAlertMsg("THÔNG BÁO", "Không tồn tại mã định danh này trên hệ thống!");
                        return;
                    }else {
                        tf_MaDinhDanh.setText(dataSinhVien.getString("MaSoDinhDanh"));
                        tf_Email.setText(dataSinhVien.getString("Email"));
                        tf_HoVaTen.setText(dataSinhVien.getString("HoVaTen"));
                        tf_SDT.setText(dataSinhVien.getString("SoDienThoai"));
                        tf_QueQuan.setText(dataSinhVien.getString("QueQuan"));
                        //tf_NamNhapHoc.setText(dataSinhVien.getString("NamNhapHoc"));
                        date_NgaySinh.setValue(dataSinhVien.getDate("NgaySinh").toLocalDate());
                        if(dataSinhVien.getString("GioiTinh").equals("Nam")){
                            checkbox_Nam.setSelected(true);
                        }else {
                            checkbox_Nu.setSelected(true);
                        }
                    }
                    String TrangThaiSuDung = "Đang sử dụng phòng";
                    LocalDate ngayTraPhong;
                    LocalDate ngayHienTai = LocalDate.now();
                    while(data.next()){
                        ngayTraPhong = data.getDate("NgayTraPhong").toLocalDate(); // Ép liểu dữ liệu từ Date (trong MySQL) ra kiểu LocalDate để so sánh
                        if(ngayHienTai.equals(ngayTraPhong)){
                            TrangThaiSuDung = "Hết hạn hôm nay";
                        } else if ( ngayHienTai.isAfter(ngayTraPhong)) {
                            TrangThaiSuDung = "Đã trả phòng";
                        }

                        ListHoatDongThuePhong.add(new HoatDongThuePhong(data.getString("IdHoaDon"), data.getString("MaSoPhong"), data.getString("Tang"), data.getString("Day"), data.getString("NgayNhanPhong"), data.getString("NgayTraPhong"),TrangThaiSuDung));

                    }
                    //Đưa tất cả dữ liệu vào Table

                    tb_cl_MaHoaDon.setCellValueFactory(new PropertyValueFactory<HoatDongThuePhong,String>("MaHoaDon"));
                    tb_cl_Day.setCellValueFactory(new PropertyValueFactory<HoatDongThuePhong,String>("Day"));
                    tb_cl_MaPhong.setCellValueFactory(new PropertyValueFactory<HoatDongThuePhong,String>("MaPhong"));
                    tb_cl_Tang.setCellValueFactory(new PropertyValueFactory<HoatDongThuePhong,String>("Tang"));
                    tb_cl_NgayNhanPhong.setCellValueFactory(new PropertyValueFactory<HoatDongThuePhong,String>("NgayNhanPhong"));
                    tb_cl_NgayTraPhong.setCellValueFactory(new PropertyValueFactory<HoatDongThuePhong,String>("NgayTraPhong"));
                    tb_cl_TrangThai.setCellValueFactory(new PropertyValueFactory<HoatDongThuePhong,String>("TrangThai"));
                    table_HienThiThongTin.setItems(ListHoatDongThuePhong); // gán giá trị các cột vào bảng
                } catch (SQLException e) {

                    throw new RuntimeException(e);
                }

            }
        });

        bt_ChinhSuaThongTin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String GioiTinh = null;
                if(checkbox_Nam.isSelected() == true){
                    GioiTinh = "Nam";
                }else GioiTinh = "Nữ";
                String sqlQueryUpdateDataNguoiDung= "UPDATE `thongtinsinhvien` SET `HoVaTen`='"+tf_HoVaTen.getText()+"',`MaSoDinhDanh`='"+tf_MaDinhDanh.getText()+"',`SoDienThoai`='"+tf_SDT.getText()+"',`NgaySinh`='"+date_NgaySinh.getValue()+"',`GioiTinh`='"+GioiTinh+"',`QueQuan`='"+tf_QueQuan.getText()+"',`Email`='"+tf_Email.getText()+"' WHERE MaSoDinhDanh = '"+tf_MaDinhDanhTimKiem.getText()+"'";
                try {
                    int checkQueryUpdateDuLieuNguoiDung = new ConnectDB().getStmt().executeUpdate(sqlQueryUpdateDataNguoiDung);
                    if(checkQueryUpdateDuLieuNguoiDung > 0){
                        DBUtils.printAlertMsg("THÔNG BÁO", "Cập nhật dữ liệu thành công!");


                    }else {
                        DBUtils.printAlertMsg("THÔNG BÁO", "Cập nhật dữ liệu không thành công!");
                    }
                } catch (SQLException e) {
                    DBUtils.printAlertMsg("THÔNG BÁO", "Nhập sai dữ liệu đầu vào!");
                    throw new RuntimeException(e);
                }
            }
        });


            }
}
