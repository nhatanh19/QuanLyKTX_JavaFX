package filefxml.quanlyktx_fx_version2;

import filefxml.quanlyktx_fx_version2.Database.ConnectDB;
import filefxml.quanlyktx_fx_version2.Model.Phong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HoaDonTienDienController implements Initializable {


    @FXML
    private Button bt_GhiSoDien;

    @FXML
    private Button bt_LayDanhSachCacPhongChuaGhiSoDien;

    @FXML
    private Button bt_ThanhToanTienDien;
    @FXML
    private TableView<Phong> table_HienThiThongTin;

    @FXML
    private TableColumn<Phong, String> tb_cl_MaSoPhong;

    @FXML
    private TableColumn<Phong, String> tb_cl_Tang;

    @FXML
    private TableColumn<Phong, String> tb_cl_Day;

    @FXML
    private TextField tf_Day;

    @FXML
    private TextField tf_MaSoPhong;

    @FXML
    private TextField tf_SoDienThang;

    @FXML
    private TextField tf_Tang;

    @FXML
    private TextField tf_Thang;

    private ObservableList<Phong> ListPhongKTX = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void Load_DSCacPhongChuaGhiSoDien(){
        ListPhongKTX.clear();
        String querySelectDataOfTablePhong = "SELECT `MaSoPhong`, `Day`, `Tang` FROM `phong` WHERE SoNguoiTrongPhong > 0 ";
        try {
            ResultSet dataPhongKTX = new ConnectDB().getStmt().executeQuery(querySelectDataOfTablePhong);
            while(dataPhongKTX.next()){
                ResultSet checkSoDien = new ConnectDB().getStmt().executeQuery("SELECT `Thang` FROM `hoadontiendien` WHERE MaSoPhong = '"+tf_MaSoPhong.getText()+"' AND Day = '"+tf_Day.getText()+"' AND Tang = '"+tf_Tang.getText()+"'");
                //if()
                ListPhongKTX.add(new Phong(dataPhongKTX.getString("MaSoPhong"), dataPhongKTX.getString("Tang"), dataPhongKTX.getString("Day"), dataPhongKTX.getString("LoaiPhong"), dataPhongKTX.getString("GiaPhong") + ".000 VND"));
            }
            tb_cl_MaSoPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("MaPhong"));
            tb_cl_Day.setCellValueFactory(new PropertyValueFactory<Phong, String>("Day"));
            tb_cl_Tang.setCellValueFactory(new PropertyValueFactory<Phong, String>("Tang"));
            table_HienThiThongTin.setItems(ListPhongKTX);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
