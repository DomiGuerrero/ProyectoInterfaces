package com.pruebas.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class Pelicula {

    @FXML
    private TextField anyoPelicula;

    @FXML
    private Button btnBorrarDirector;

    @FXML
    private Button btnBorrarPelicula;

    @FXML
    private Button btnCerrarPelicula;

    @FXML
    private Button btnCrearDirector;

    @FXML
    private Button btnCrearPelicula;

    @FXML
    private ImageView imagenPelicula;

    @FXML
    private TextArea textoArgumento;

    @FXML
    private TextArea textoDirectores;

    @FXML
    private ChoiceBox<?> tipoPelicula;

    @FXML
    private TextField tituloPelicula;

    @FXML
    void borrarPelicula(ActionEvent event) {

    }

    @FXML
    void cerrarPelicula(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EscenaPeliculas.fxml"));
        Main.mainNode.setCenter(fxmlLoader.load());
    }

    @FXML
    void crearPelicula(ActionEvent event) {

    }

}







