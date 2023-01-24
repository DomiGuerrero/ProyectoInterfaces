package com.pruebas.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main extends Application {

    public static BorderPane mainNode;
    final String FICHERO_CONFIGURACION ="settings.properties";
    static Properties configuracion =  new Properties();
    static MongoClient clienteMongo;
    static MongoDatabase databaseMongo;

    private void conectarBaseDatos(Properties config){
        try {
            clienteMongo =  MongoClients.create(config.getProperty("MONGO_URI"));
            databaseMongo =clienteMongo.getDatabase(  config.getProperty("MONGODB_DATABASE"));
        }catch (Exception ex){
            Alert alerta =  new  Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Alerta de Error");
            alerta.setContentText("Error: no se ha podido acceder a la base de datos");
            alerta.show();
        }


    }
    private void cargarConfiguracion(String fichero_configuracion) {
        try{
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(fichero_configuracion);
            configuracion.load(input);
            System.out.println(configuracion.getProperty("MONGO_URI"));

        } catch (IOException e) {
            Alert alerta =  new  Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Alerta de Error");
            alerta.setContentText("Error no se ha podido leer " + fichero_configuracion);
            alerta.show();
        }
    }
    @Override
    public void start(Stage stage) throws IOException {

        cargarConfiguracion(FICHERO_CONFIGURACION);
        conectarBaseDatos(configuracion);
        Document pelicula =  new Document();
        pelicula.append("nombre","Gran Torino")
                .append("nombre_original", "Gran Torino")
                .append("plot", "Walt Kowalski, un veterano de la guerra de Corea," +
                        " es un obrero jubilado del sector del automóvil que ha enviudado recientemente. " +
                        "Su máxima pasión es cuidar de su más preciado tesoro: un coche Gran Torino de 1972. " +
                        "Es un hombre inflexible y cascarrabias, al que le cuesta trabajo asimilar los cambios " +
                        "que se producen a su alrededor, " +
                        "especialmente la llegada de multitud de inmigrantes asiáticos a su barrio. Sin embargo, " +
                        "las circustancias harán que se vea obligado a " +
                        "replantearse sus ideas.")
                .append("Fecha_lanzamiento",2008);
        System.out.println(databaseMongo.getCollection("Peliculas").insertOne(pelicula));

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        mainNode = fxmlLoader.load();
        Scene scene = new Scene(mainNode,650,400);
        stage.setTitle("Información de películas");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}