package co.edu.uniquindio.arbol_binario.arbolbinario;

import co.edu.uniquindio.arbol_binario.arbolbinario.model.ArbolBinario;
import co.edu.uniquindio.arbol_binario.arbolbinario.model.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArbolBinarioController {

    @FXML
    private TextField txtDato;
    @FXML
    private TextArea txtSalida;
    @FXML
    private Canvas canvasArbol;

    private final ArbolBinario arbol = new ArbolBinario();

    private int obtenerDato() {
        return Integer.parseInt(txtDato.getText());
    }

    public void initialize() {
        // Limpia canvas y salida al iniciar
        txtSalida.clear();
        GraphicsContext gc = canvasArbol.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasArbol.getWidth(), canvasArbol.getHeight());
    }

    public void agregarDato(ActionEvent actionEvent) {
        try {
            int dato = obtenerDato();
            arbol.agregarDato(dato);
            txtSalida.setText(arbol.mostrarArbol());
            dibujarArbol();
            txtDato.clear();
        } catch (NumberFormatException e) {
            txtSalida.setText("Por favor, ingrese un número válido.");
        }
    }

    public void eliminarDato(ActionEvent actionEvent) {

        try {
            int dato = obtenerDato();
            arbol.eliminarDato(dato);
            txtSalida.setText(arbol.mostrarArbol());
            dibujarArbol();
            txtDato.clear();
        } catch (NumberFormatException e) {
            txtSalida.setText("Por favor, ingrese un número válido.");
        }
    }

    public void existeDato(ActionEvent actionEvent) {
        try {
            int dato = obtenerDato();
            boolean existe = arbol.existeDato(dato);
            txtSalida.setText("¿Existe " + dato + "? " + existe);
        } catch (NumberFormatException e) {
            txtSalida.setText("Por favor, ingrese un número válido.");
        }
    }

    public void estaVacio(ActionEvent actionEvent) {
    }

    public void obtenerPeso(ActionEvent actionEvent) {
    }

    public void obtenerAltura(ActionEvent actionEvent) {
    }

    public void obtenerNivel(ActionEvent actionEvent) {
    }

    public void contarHojas(ActionEvent actionEvent) {
    }

    public void obtenerMenor(ActionEvent actionEvent) {
    }

    public void obtenerNodoMayor(ActionEvent actionEvent) {
    }

    public void obtenerNodoMenor(ActionEvent actionEvent) {
    }

    public void recorrerInOrden(ActionEvent actionEvent) {
    }

    public void recorrerPreOrden(ActionEvent actionEvent) {
    }

    public void recorrerPostOrden(ActionEvent actionEvent) {
    }

    public void imprimirAmplitud(ActionEvent actionEvent) {
    }

    public void borrarArbol(ActionEvent actionEvent) {
    }

    private void dibujarArbol() {
        GraphicsContext gc = canvasArbol.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasArbol.getWidth(), canvasArbol.getHeight());

        if (arbol.estaVacio()) {
            return;
        }
        dibujarNodo(gc, arbol.obtenerRaiz(), canvasArbol.getWidth() / 2, 30, canvasArbol.getWidth() / 4);
    }

    private void dibujarNodo(GraphicsContext gc, Nodo nodo, double x, double y, double separacion) {
        if (nodo == null) return;
        double radio = 15;
        if (nodo.getIzquierdo() != null) {
            gc.strokeLine(x, y, x - separacion, y + 50);
            dibujarNodo(gc, nodo.getIzquierdo(), x - separacion, y + 50, separacion / 2);
        }
        if (nodo.getDerecho() != null) {
            gc.strokeLine(x, y, x + separacion, y + 50);
            dibujarNodo(gc, nodo.getDerecho(), x + separacion, y + 50, separacion / 2);
        }
        gc.strokeOval(x - radio, y - radio, radio * 2, radio * 2);
        gc.fillText(String.valueOf(nodo.getDato()), x - 4, y + 4);
    }
}