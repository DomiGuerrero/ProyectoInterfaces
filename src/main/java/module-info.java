module com.pruebas.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;


    opens com.pruebas.demo to javafx.fxml;
    exports com.pruebas.demo;
}