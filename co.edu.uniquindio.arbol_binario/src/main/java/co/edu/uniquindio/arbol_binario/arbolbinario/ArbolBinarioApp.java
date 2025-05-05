package co.edu.uniquindio.arbol_binario.arbolbinario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ArbolBinarioApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ArbolBinarioApp.class.getResource("vista-arbolBinario.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Arbol-Binario");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}