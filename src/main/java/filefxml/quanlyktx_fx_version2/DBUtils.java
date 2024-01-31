package filefxml.quanlyktx_fx_version2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class DBUtils {
    public static void changeScene(ActionEvent actionEvent,String fxmlFile, String title ) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        // Lấy kích thước màn hình
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Tính toán vị trí căn giữa màn hình
        double centerX = bounds.getMinX() + (bounds.getWidth() - root.getBoundsInParent().getWidth()) / 2;
        double centerY = bounds.getMinY() + (bounds.getHeight() - root.getBoundsInParent().getHeight()) / 2;

        // Đặt vị trí mới cho cửa sổ
        stage.setX(centerX);
        stage.setY(centerY);
        //
        stage.show();
    }

    public static void printAlertMsg(String title, String Msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(Msg);
        alert.showAndWait(); // Hiển thị thông báo và đợi cho đến khi người dùng đóng nó đi
    }
}
