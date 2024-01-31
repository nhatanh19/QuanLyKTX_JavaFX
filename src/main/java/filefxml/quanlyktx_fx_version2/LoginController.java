package filefxml.quanlyktx_fx_version2;

import filefxml.quanlyktx_fx_version2.Database.ConnectDB;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.SQLException;

public class LoginController implements Initializable {

    @FXML
    private Button LoginButton;

    @FXML
    private ImageView imageLogin;

    @FXML
    private PasswordField inputPasswordField;

    @FXML
    private TextField inputUsernameField;

    @FXML
    private Button buttonQuenMatKhau;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Dang nhap
        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ResultSet checkAccount = null;
                boolean check = false;
                String user = inputUsernameField.getText();
                String pass = inputPasswordField.getText();
                ConnectDB connectDB = new ConnectDB();
                String queryCheckAccount = "SELECT `User`, `Password` FROM `taikhoan` WHERE User = '"+user +"' AND Password = '"+pass+"'";
                try {
                    checkAccount =  connectDB.getStmt().executeQuery(queryCheckAccount);
                    check = checkAccount.next();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (check){
                    try {
                        //DBUtils.changeScene(event , "manage-view.fxml", "Quan Ly", 1200, 750);
                        DBUtils.changeScene(event , "manage-view.fxml", "Quan Ly");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else{
                    inputUsernameField.clear();
                    inputPasswordField.clear();
                    // hien thi mess bao loi
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("WARNING");
                    alert.setHeaderText("Tài khoản không tồn tại!");
                    alert.showAndWait(); // Hiển thị thông báo và đợi cho đến khi người dùng đóng nó đi
                }

            }
        });

        // Quen mat khau
        buttonQuenMatKhau.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    //DBUtils.changeScene(actionEvent, "LayLaiMatKhau-view.fxml", "Quên mật khẩu",632,334);
                    DBUtils.changeScene(actionEvent, "LayLaiMatKhau-view.fxml", "Quên mật khẩu");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
