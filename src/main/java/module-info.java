module com.icthulp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.sql.rowset;
    requires java.management;
    requires org.apache.commons.dbcp2;
    requires jdk.jdi;
    requires com.google.protobuf;


    opens com.icthulp to javafx.fxml;
    exports com.icthulp;
    exports com.icthulp.MyMenu;
    opens com.icthulp.MyMenu to javafx.fxml;
    exports com.icthulp.Tables;
    opens com.icthulp.Tables to javafx.fxml;
    exports com.icthulp.DorpsgenotenInfo;
    opens com.icthulp.DorpsgenotenInfo to javafx.fxml;
    exports com.icthulp.AanmeldenPage;
    opens com.icthulp.AanmeldenPage to javafx.fxml;
    exports com.icthulp.InformatiePage;
    opens com.icthulp.InformatiePage to javafx.fxml;
}