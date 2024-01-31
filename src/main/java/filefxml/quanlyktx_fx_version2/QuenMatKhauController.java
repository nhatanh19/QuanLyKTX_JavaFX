package filefxml.quanlyktx_fx_version2;

import filefxml.quanlyktx_fx_version2.API.ConnectGmail;
import filefxml.quanlyktx_fx_version2.API.ConnectGmailV2;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import filefxml.quanlyktx_fx_version2.Database.ConnectDB;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class QuenMatKhauController implements Initializable {
    @FXML
    private Button bt_exitForm;

    @FXML
    private Button bt_requestCodeAccess;

    @FXML
    private Button bt_setPassword;

    @FXML
    private Label lb_thongBao;

    @FXML
    private TextField tf_codeAccess;

    @FXML
    private TextField tf_emailUser;

    @FXML
    private TextField tf_newPassword;

    @FXML
    private Label lb_thongBaoGuiCodeThanhCong;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Lấy code đăng nhập
        bt_requestCodeAccess.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = tf_emailUser.getText();
                if(email.equals("")){
                    DBUtils.printAlertMsg("Thông báo", "Chưa điền email!");
                }else{
                        //ConnectDB connectDB = new ConnectDB();
                    try {
                        ResultSet data = new ConnectDB().getStmt().executeQuery("SELECT `Email` FROM `taikhoan` WHERE Email = '"+email+"'");
                        boolean check = data.next();
                        if(check) {
                            Random random = new Random();
                            // Khởi tạo một chuỗi để lưu trữ dãy mã
                            StringBuilder code = new StringBuilder();
                            // Sinh ngẫu nhiên 10 chữ số và thêm vào chuỗi mã
                            for (int i = 0; i < 6; i++) {
                                int digit = random.nextInt(10); // Sinh số nguyên từ 0 đến 9
                                code.append(digit);
                            }

                            boolean checkStatusEmail = new ConnectGmail().SendEmail(email, "Mã xác nhận thay đổi mật khẩu", "Mã thay đổi mật khẩu của bạn là: "+code);


                            if(checkStatusEmail){
                                lb_thongBaoGuiCodeThanhCong.setText("Gửi code thành công!");
                            }else {
                                lb_thongBaoGuiCodeThanhCong.setText("Gửi code thất bại!");
                            }

                            // gửi code lên csdl
                            new ConnectDB().getStmt().executeUpdate("UPDATE `taikhoan` SET `codeAccess`='"+code+"' WHERE Email = '"+email+"'");
                        }else{
                            DBUtils.printAlertMsg("Thông báo", "Không tồn tại email này!");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        //Thay đôỉ mật khẩu
        bt_setPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String pass = tf_newPassword.getText();
                String code = tf_codeAccess.getText();
                if(pass.equals("")){
                    DBUtils.printAlertMsg("Thông báo", "Chưa nhập mật khẩu mới!");
                }else if(code.equals("")){
                    DBUtils.printAlertMsg("Thông báo", "Chưa nhập mã xác nhận!");
                }else {
                    try {
                        int i = new ConnectDB().getStmt().executeUpdate("UPDATE `taikhoan` SET `Password`='"+pass+"' WHERE Email = '"+tf_emailUser.getText()+"' and codeAccess = '"+code+"'");
                        if(i > 0){
                            // thực hiện thành công thì i sẽ nhận 1 giá trị bất kì, là v trí bị thay đổi giá trị
                            lb_thongBao.setText("Mật khẩu đã được đổi thành công!");
                        }else {
                            lb_thongBao.setText("Mã xác nhận sai! Vui lòng nhập lại!");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        bt_exitForm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    DBUtils.changeScene(actionEvent, "login-view.fxml", "Quản Lý KTX");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
