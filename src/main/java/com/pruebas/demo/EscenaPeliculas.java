package com.pruebas.demo;

import com.mongodb.client.MongoCollection;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Sorts.ascending;

public class EscenaPeliculas {

    private final static int dataSize = 10_023;
    private final static int FILAS_POR_PAGINA = 9;
    @FXML
    public Pagination paginacion;
    @FXML
    private TableColumn<Document, Integer> columnaAño;

    @FXML
    private TableColumn<Document, String> columnaTipo;

    @FXML
    private TableColumn<Document, String> columnaTitulo;

    @FXML
    private TableView<Document> tableview;

    private MongoCollection<Document> coleccionMovies;

    private ObservableList<Document> datosMovies;


    public void initialize(){

        coleccionMovies = Main.databaseMongo.getCollection("movies");
        long elementosTotales =  coleccionMovies.countDocuments();
        int paginasTotales =(int)elementosTotales/FILAS_POR_PAGINA;
        paginacion.setPageCount(paginasTotales);

        columnaTitulo.setCellValueFactory(param-> {
            String dato = param.getValue().getString("title");
            return new SimpleStringProperty(dato);
        });
        columnaTipo.setCellValueFactory(param-> {
            String dato = param.getValue().getString("type");
            return new SimpleStringProperty(dato);
        });
        columnaAño.setCellValueFactory(param-> {
            Integer dato = param.getValue().getInteger("year");
            return new SimpleObjectProperty<Integer>(dato);
        });
        paginacion.setPageFactory(this::createPage);
    }

    private Node createPage(Integer indice) {
        int fromIndex = indice * FILAS_POR_PAGINA;
        datosMovies =  coleccionMovies.find().skip(fromIndex).limit(FILAS_POR_PAGINA).sort(ascending("title")).into(FXCollections.observableArrayList());
        tableview.setItems(datosMovies);
        return new Label("Página: " + indice);
    }

    public void seleccionPelicula(KeyEvent keyEvent) {
    }
}
