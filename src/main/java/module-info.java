module com.pruebas.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.pruebas.demo to javafx.fxml;
    exports com.pruebas.demo;
}