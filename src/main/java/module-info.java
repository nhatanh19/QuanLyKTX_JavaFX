module filefxml.quanlyktx_fx_version2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.email;
    requires javax.mail;

    opens filefxml.quanlyktx_fx_version2.Model to javafx.base;
    opens filefxml.quanlyktx_fx_version2 to javafx.fxml;
    exports filefxml.quanlyktx_fx_version2;

}