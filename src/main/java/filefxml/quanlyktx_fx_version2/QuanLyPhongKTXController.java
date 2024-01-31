package filefxml.quanlyktx_fx_version2;

import filefxml.quanlyktx_fx_version2.Database.ConnectDB;
import filefxml.quanlyktx_fx_version2.Model.HoatDongThuePhong;
import filefxml.quanlyktx_fx_version2.Model.NguoiDung;
import filefxml.quanlyktx_fx_version2.Model.Phong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.Result;


public class QuanLyPhongKTXController implements Initializable {
    @FXML
    private Button bt_ChuyenTrangThaiCacPhongDaHetHopDong;

    @FXML
    private Button bt_DanhSachCacPhongDangDuocThue;

    @FXML
    private TableView<Phong> table_HienThiThongTin;

    @FXML
    private TableColumn<Phong, String> tb_cl_Day;

    @FXML
    private TableColumn<Phong, String> tb_cl_LoaiPhong;

    @FXML
    private TableColumn<Phong, String> tb_cl_MaSoPhong;

    @FXML
    private TableColumn<Phong, String> tb_cl_Tang;

    @FXML
    private TableColumn<Phong, String> tb_cl_GiaPhong;


    @FXML
    private Button bt_TimKiemPhong;


    @FXML
    private TableView<NguoiDung> table_ThongTinNguoiDung;

    @FXML
    private TableColumn<NguoiDung, String> tb_cl_Email;


    @FXML
    private TableColumn<NguoiDung, String> tb_cl_HoVaTen;


    @FXML
    private TableColumn<NguoiDung, String> tb_cl_MaSoDinhDanh;


    @FXML
    private TableColumn<NguoiDung, String> tb_cl_NgaySinh;

    @FXML
    private TableColumn<NguoiDung, String> tb_cl_SDT;

    @FXML
    private TextField tf_Day;

    @FXML
    private TextField tf_MaSoPhong;

    @FXML
    private TextField tf_Tang;


    private ObservableList<Phong> ListPhongKTX = FXCollections.observableArrayList();

    private ObservableList<NguoiDung> ListNguoiDung = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_DanhSachCacPhongDangDuocThue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LoadTable_HienThiThongTin();
            }
        });

        bt_ChuyenTrangThaiCacPhongDaHetHopDong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<Phong> dataPhong = new ArrayList<>();

                String queryCheckPhongDaHetHopDong = "SELECT `MaSoPhong`, `Tang`, `Day`, `IdHoaDon` FROM `quanlyhoatdongthuephong` WHERE TrangThaiSuDung = 'true' AND NgayTraPhong < CURRENT_DATE()";
                try {
                    System.out.println(queryCheckPhongDaHetHopDong);
                    ResultSet data = new ConnectDB().getStmt().executeQuery(queryCheckPhongDaHetHopDong);
                    while (data.next()){
                        String MaSoPhong = data.getString("MaSoPhong"), Tang = data.getString("Tang") , Day = data.getString("Day");
                        new ConnectDB().getStmt().executeUpdate("UPDATE `quanlyhoatdongthuephong` SET `TrangThaiSuDung`='false' WHERE IdHoaDon = '"+data.getString("IdHoaDon")+"'");
                        dataPhong.add(new Phong(MaSoPhong, Tang, Day));
                        System.out.println("data chay thanh cong!");
                    }
                    data.close();
                    for (Phong i : dataPhong){
                        ResultSet phong = new ConnectDB().getStmt().executeQuery("SELECT `SoNguoiTrongPhong` FROM `phong` WHERE MaSoPhong = '"+i.getMaPhong()+"' AND Day = '"+i.getDay()+"' AND Tang = '"+i.getTang()+"'");
                        while(phong.next()){
                            int Nguoi = phong.getInt("SoNguoiTrongPhong");
                            Nguoi -= 1;
                            new ConnectDB().getStmt().executeUpdate("UPDATE `phong` SET `TinhTrangSuDung`='true',`SoNguoiTrongPhong`='"+Nguoi+"' WHERE MaSoPhong = '"+i.getMaPhong()+"' AND Day = '"+i.getDay()+"' AND Tang = '"+i.getTang()+"'");
                        }
                        phong.close();
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                DBUtils.printAlertMsg("THÔNG BÁO", "Đã chuyển đổi trạng thái các hợp đồng, các phòng thành công!");
                LoadTable_HienThiThongTin();
            }
        });

        bt_TimKiemPhong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ListNguoiDung.clear();
                String MaSoPhong = tf_MaSoPhong.getText();
                String Day =  tf_Day.getText();
                String Tang = tf_Tang.getText();
                if(MaSoPhong.isEmpty() || Day.isEmpty() || Tang.isEmpty()){
                    DBUtils.printAlertMsg("THÔNG BÁO" , "Chưa nhập đầy đủ thông tin phòng!");
                }else {
                    String queryCrawlNguoiDung = "SELECT `HoVaTen`, `MaSoDinhDanh`, `SoDienThoai`, `NgaySinh`, `Email`\n" +
                            "FROM `thongtinsinhvien`\n" +
                            "WHERE `MaSoDinhDanh` IN (\n" +
                            "    SELECT `MaSoDinhDanh`\n" +
                            "    FROM `quanlyhoatdongthuephong`\n" +
                            "    WHERE `MaSoPhong` = '"+MaSoPhong+"' AND `Tang` = '"+Tang+"' \n" +
                            "    AND `Day` = '"+Day+"' AND `TrangThaiSuDung` = 'true'\n" +
                            ");";
                    try {
                        ResultSet dataNguoiDung = new ConnectDB().getStmt().executeQuery(queryCrawlNguoiDung);
                        LocalDate NgaySinh;
                        while(dataNguoiDung.next()){
                            NgaySinh = dataNguoiDung.getDate("NgaySinh").toLocalDate();
                            ListNguoiDung.add(new NguoiDung(dataNguoiDung.getString("HoVaTen"), dataNguoiDung.getString("MaSoDinhDanh"), dataNguoiDung.getString("SoDienThoai"), dataNguoiDung.getString("Email"), NgaySinh));
                        }
                        tb_cl_MaSoDinhDanh.setCellValueFactory(new PropertyValueFactory<NguoiDung, String>("MaDinhDanh"));
                        tb_cl_Email.setCellValueFactory(new PropertyValueFactory<NguoiDung, String>("Email"));
                        tb_cl_HoVaTen.setCellValueFactory(new PropertyValueFactory<NguoiDung, String>("HoVaTen"));
                        tb_cl_NgaySinh.setCellValueFactory(new PropertyValueFactory<NguoiDung, String>("NgaySinh"));
                        tb_cl_SDT.setCellValueFactory(new PropertyValueFactory<NguoiDung, String>("SDT"));
                        table_ThongTinNguoiDung.setItems(ListNguoiDung);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });

    }
    public void LoadTable_HienThiThongTin(){
        ListPhongKTX.clear();
        String querySelectDataOfTablePhong = "SELECT `MaSoPhong`, `Day`, `Tang`, `LoaiPhong`, `GiaPhong` FROM `phong` WHERE SoNguoiTrongPhong > 0 ";
        try {
            ResultSet dataPhongKTX = new ConnectDB().getStmt().executeQuery(querySelectDataOfTablePhong);
            while(dataPhongKTX.next()){
                ListPhongKTX.add(new Phong(dataPhongKTX.getString("MaSoPhong"), dataPhongKTX.getString("Tang"), dataPhongKTX.getString("Day"), dataPhongKTX.getString("LoaiPhong"), dataPhongKTX.getString("GiaPhong") + ".000 VND"));
            }
            tb_cl_MaSoPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("MaPhong"));
            tb_cl_Day.setCellValueFactory(new PropertyValueFactory<Phong, String>("Day"));
            tb_cl_Tang.setCellValueFactory(new PropertyValueFactory<Phong, String>("Tang"));
            tb_cl_LoaiPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("LoaiPhong"));
            tb_cl_GiaPhong.setCellValueFactory(new PropertyValueFactory<Phong, String>("GiaPhong"));
            table_HienThiThongTin.setItems(ListPhongKTX);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
