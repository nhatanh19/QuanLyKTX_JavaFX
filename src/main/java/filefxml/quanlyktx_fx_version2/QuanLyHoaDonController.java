package filefxml.quanlyktx_fx_version2;

import filefxml.quanlyktx_fx_version2.Database.ConnectDB;
import filefxml.quanlyktx_fx_version2.Model.HoaDon;
import filefxml.quanlyktx_fx_version2.Model.Phong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuanLyHoaDonController implements Initializable {
    @FXML
    private Button bt_ThanhToanAllHoaDon;

    @FXML
    private Button bt_ThanhToanHoaDon;

    @FXML
    private Button bt_TimKiem;

    @FXML
    private TableView<HoaDon> table_HienThiHoaDon;


    @FXML
    private TableColumn<HoaDon, String> tb_cl_MaHoaDon;

    @FXML
    private TableColumn<HoaDon, String> tb_cl_TenHoaDon;

    @FXML
    private TableColumn<HoaDon, String> tb_cl_TongTienThanhToan;

    @FXML
    private TableColumn<HoaDon, String> tb_cl_TrangThaiThanhToan;

    @FXML
    private TextField tf_MaDinhDanhTimKiem;

    @FXML
    private TextField tf_MaHoaDon;
    private ObservableList<HoaDon> ListHoaDon = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> ListMaHoaDon = new ArrayList<>();

        bt_TimKiem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LoadDataOfTable(ListMaHoaDon); // Lấy data đổ vào bảng hiển thị
            }
        });

        bt_ThanhToanAllHoaDon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConnectDB connectDB = new ConnectDB();
                int checkQueryHoaDonThuePhong = 0;
                int checkQueryHoaDonTienNuoc = 0;
                for(String idHoaDon : ListMaHoaDon){
                    try {
                        checkQueryHoaDonThuePhong = connectDB.getStmt().executeUpdate("UPDATE `hoadonthuephong` SET `DaThanhToan`='YES' WHERE IdHoaDon = '" + idHoaDon + "'");
                        checkQueryHoaDonTienNuoc = connectDB.getStmt().executeUpdate("UPDATE `hoadontiennuoc` SET `DaThanhToan`='YES' WHERE IdHoaDon = '"+idHoaDon+"'");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (checkQueryHoaDonThuePhong > 0 && checkQueryHoaDonTienNuoc > 0){
                    DBUtils.printAlertMsg("THÔNG BÁO", "Thanh toán tất cả các hoá đơn thành công!");
                    LoadDataOfTable(ListMaHoaDon);
                } else DBUtils.printAlertMsg("THÔNG BÁO", "Thanh toán tất cả các hoá đơn thất bại!");
            }
        });

        bt_ThanhToanHoaDon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ConnectDB connectDB = new ConnectDB();
                int checkQueryHoaDonThuePhong = 0;
                int checkQueryHoaDonTienNuoc = 0;
                try {
                    checkQueryHoaDonThuePhong = connectDB.getStmt().executeUpdate("UPDATE `hoadonthuephong` SET `DaThanhToan`='YES' WHERE IdHoaDon = '" + tf_MaHoaDon.getText() + "'");
                    checkQueryHoaDonTienNuoc = connectDB.getStmt().executeUpdate("UPDATE `hoadontiennuoc` SET `DaThanhToan`='YES' WHERE IdHoaDon = '"+tf_MaHoaDon.getText()+"'");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (checkQueryHoaDonThuePhong > 0 && checkQueryHoaDonTienNuoc > 0){
                    DBUtils.printAlertMsg("THÔNG BÁO", "Thanh toán hoá đơn "+tf_MaHoaDon.getText()+" thành công!");
                    LoadDataOfTable(ListMaHoaDon);
                } else DBUtils.printAlertMsg("THÔNG BÁO", "Thanh toán hoá đơn "+tf_MaHoaDon.getText()+" thất bại!");
            }
        });
    }


    public void LoadDataOfTable(ArrayList<String> ListMaHoaDon){
        ListHoaDon.clear();
        ConnectDB conn = new ConnectDB();
        String TrangThaiThanhToan = "";
        try {
            ResultSet data_AllHoaDon = new ConnectDB().getStmt().executeQuery("SELECT `IdHoaDon` FROM `quanlyhoatdongthuephong` WHERE MaSoDinhDanh = '"+tf_MaDinhDanhTimKiem.getText()+"'");
            while (data_AllHoaDon.next()){
                ListMaHoaDon.add(data_AllHoaDon.getString("IdHoaDon"));
                //Truy vấn đến bảng Tiền Phòng
                ResultSet data_TienPhong = conn.getStmt().executeQuery("SELECT `IdHoaDon`, `TongTienPhong`, `DaThanhToan` FROM `hoadonthuephong` WHERE IdHoaDon = '"+data_AllHoaDon.getString("IdHoaDon")+"'");
                while(data_TienPhong.next()){
                    if(data_TienPhong.getString("DaThanhToan").equals("YES")){
                        TrangThaiThanhToan = "Đã thanh toán";
                    }else {
                        TrangThaiThanhToan = "Chưa thanh toán";
                    }
                    ListHoaDon.add(new HoaDon(data_TienPhong.getString("IdHoaDon"), data_TienPhong.getString("TongTienPhong") + " VND", "Hoá đơn tiền phòng KTX", TrangThaiThanhToan));
                }

                //Truy vấn đến bảng Tiền Nước
                //SELECT `IdHoaDon`, `TongTienNuoc`, `DaThanhToan` FROM `hoadontiennuoc` WHERE 1
                ResultSet data_TienNuoc = conn.getStmt().executeQuery("SELECT `IdHoaDon`, `TongTienNuoc`, `DaThanhToan` FROM `hoadontiennuoc` WHERE IdHoaDon = '"+data_AllHoaDon.getString("IdHoaDon")+"'");
                while(data_TienNuoc.next()){
                    if(data_TienNuoc.getString("DaThanhToan").equals("YES")){
                        TrangThaiThanhToan = "Đã thanh toán";
                    }else {
                        TrangThaiThanhToan = "Chưa thanh toán";
                    }
                    ListHoaDon.add(new HoaDon(data_TienNuoc.getString("IdHoaDon"), data_TienNuoc.getString("TongTienNuoc") + " VND", "Hoá đơn tiền nước KTX", TrangThaiThanhToan));
                }

//                        //Truy vấn đến bảng Tiền Điện
//                        //SELECT `IdHoaDon`, `Thang`, `TienDienTheoThang`, `DaThanhToan` FROM `hoadontiendien` WHERE IdHoaDon = ''
//                        ResultSet data_TienDien = conn.getStmt().executeQuery("SELECT `IdHoaDon`, `Thang`, `TienDienTheoThang`, `DaThanhToan` FROM `hoadontiendien` WHERE IdHoaDon = '"+data_AllHoaDon.getString("IdHoaDon")+"'");
//                        while(data_TienDien.next()){
//                            if(data_TienDien.getString("DaThanhToan").equals("YES")){
//                                TrangThaiThanhToan = "Đã thanh toán";
//                            }else {
//                                TrangThaiThanhToan = "Chưa thanh toán";
//                            }
//                            ListHoaDon.add(new HoaDon(data_TienDien.getString("IdHoaDon"), data_TienDien.getString("TienDienTheoThang") + " VND", "Hoá đơn tiền điện tháng " + data_TienDien.getString("Thang"), TrangThaiThanhToan));
//                        }

            }

            tb_cl_MaHoaDon.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("MaHoaDon"));
            tb_cl_TenHoaDon.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("TenHoaDon"));
            tb_cl_TongTienThanhToan.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("TongTienThanhToan"));
            tb_cl_TrangThaiThanhToan.setCellValueFactory(new PropertyValueFactory<HoaDon, String>("TrangThaiThanhToan"));
            table_HienThiHoaDon.setItems(ListHoaDon);
        } catch (SQLException e) {
            DBUtils.printAlertMsg("THÔNG BÁO", "Không tồn tại mã định danh này trên hệ thống!");
            throw new RuntimeException(e);
        }
    }
}

