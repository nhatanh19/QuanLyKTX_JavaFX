package filefxml.quanlyktx_fx_version2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageController implements Initializable {
    @FXML
    private AnchorPane AnPane_ShowFxml;

    @FXML
    private AnchorPane Pane_Controller;

    @FXML
    private AnchorPane Pane_TextAndImage;

    @FXML
    private Button bt_main_DangKyNguoiDung;

    @FXML
    private Button bt_main_QuanLyPhongKTX;

    @FXML
    private Button bt_main_ThanhToanHoaDon;


    @FXML
    private Button bt_main_HoaDonTienDien;

    @FXML
    private Button bt_main_TimKiemChinhSuaNguoiDung;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Mặc định lúc chạy chương trình
        try {
            AnPane_ShowFxml.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("TimKiem-ChinhSua-view.fxml"));
            AnPane_ShowFxml.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


            bt_main_DangKyNguoiDung.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        AnPane_ShowFxml.getChildren().clear();
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("DangKyNguoiDung-view.fxml"));
                        AnPane_ShowFxml.getChildren().add(anchorPane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });


            bt_main_TimKiemChinhSuaNguoiDung.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        AnPane_ShowFxml.getChildren().clear();
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("TimKiem-ChinhSua-view.fxml"));
                        AnPane_ShowFxml.getChildren().add(anchorPane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            bt_main_QuanLyPhongKTX.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        AnPane_ShowFxml.getChildren().clear();
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("NewQuanLyPhongKTX-view.fxml"));
                        AnPane_ShowFxml.getChildren().add(anchorPane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            bt_main_ThanhToanHoaDon.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        AnPane_ShowFxml.getChildren().clear();
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("QuanLyHoaDon-view.fxml"));
                        AnPane_ShowFxml.getChildren().add(anchorPane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            bt_main_HoaDonTienDien.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        AnPane_ShowFxml.getChildren().clear();
                        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("HoaDonTienDien-view.fxml"));
                        AnPane_ShowFxml.getChildren().add(anchorPane);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
    }
}
