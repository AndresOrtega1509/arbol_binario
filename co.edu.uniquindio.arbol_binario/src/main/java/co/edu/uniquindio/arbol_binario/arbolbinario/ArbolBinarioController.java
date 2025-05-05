package co.edu.uniquindio.arbol_binario.arbolbinario;

import co.edu.uniquindio.arbol_binario.arbolbinario.model.ArbolBinario;
import co.edu.uniquindio.arbol_binario.arbolbinario.model.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

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
            dibujarArbol();
            txtSalida.setText("Dato agregado: " + dato);
            txtDato.clear();
        } catch (NumberFormatException e) {
            txtSalida.setText("Por favor, ingrese un número válido.");
        }
    }

    public void eliminarDato(ActionEvent actionEvent) {

        try {
            int dato = obtenerDato();
            arbol.eliminarDato(dato);
            txtSalida.setText("Dato eliminado: " + dato);
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
        txtSalida.setText("¿Árbol vacío? " + arbol.estaVacio());
    }

    public void obtenerPeso(ActionEvent actionEvent) {
        txtSalida.setText("Peso del árbol: " + arbol.obtenerPeso());
    }

    public void obtenerAltura(ActionEvent actionEvent) {
        txtSalida.setText("Altura del árbol: " + arbol.obtenerAltura());
    }

    public void obtenerNivel(ActionEvent actionEvent) {
        txtSalida.setText("Nivel del árbol: " + arbol.obtenerNivel());
    }

    public void contarHojas(ActionEvent actionEvent) {
        txtSalida.setText("Cantidad de hojas: " + arbol.contarHojas());
    }

    public void obtenerMenor(ActionEvent actionEvent) {
        try {
            int menor = arbol.obtenerMenor();
            txtSalida.setText("Menor valor: " + menor);
        } catch (Exception e) {
            txtSalida.setText(e.getMessage());
        }
    }

    public void obtenerNodoMayor(ActionEvent actionEvent) {
        Nodo mayor = arbol.obtenerNodoMayor();
        txtSalida.setText(mayor != null ? "Nodo mayor: " + mayor : "El árbol está vacío.");
    }

    public void obtenerNodoMenor(ActionEvent actionEvent) {
        Nodo menor = arbol.obtenerNodoMenor();
        txtSalida.setText(menor != null ? "Nodo menor: " + menor : "El árbol está vacío.");
    }

    public void recorrerInOrden(ActionEvent actionEvent) {
        List<Integer> recorrido = arbol.recorrerArbolInOrden();
        txtSalida.setText(!recorrido.isEmpty() ? "Recorrido InOrden: " + recorrido : "El arbol está vacio.");
    }

    public void recorrerPreOrden(ActionEvent actionEvent) {
        List<Integer> recorrido = arbol.recorrerArbolPreOrden();
        txtSalida.setText(!recorrido.isEmpty() ? "Recorrido PreOrden: " + recorrido : "El arbol está vacio.");
    }

    public void recorrerPostOrden(ActionEvent actionEvent) {
        List<Integer> recorrido = arbol.recorrerArbolPosOrden();
        txtSalida.setText(!recorrido.isEmpty() ? "Recorrido PosOrden: " + recorrido : "El arbol está vacio.");
    }

    public void imprimirAmplitud(ActionEvent actionEvent) {
        List<Integer> recorridoAmplitud = arbol.imprimirAmplitud();
        txtSalida.setText(recorridoAmplitud != null ? "Recorrido en Amplitud: " + recorridoAmplitud :
                "El arbol está vacio.");
    }

    public void borrarArbol(ActionEvent actionEvent) {
        arbol.borrarArbol();
        dibujarArbol();
        txtSalida.setText("Arbol eliminado.");
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

        // Dibuja línea al hijo izquierdo
        if (nodo.getIzquierdo() != null) {
            double hijoX = x - separacion;
            double hijoY = y + 50;

            double dx = hijoX - x;
            double dy = hijoY - y;
            double dist = Math.sqrt(dx * dx + dy * dy);
            double offsetX = dx * radio / dist;
            double offsetY = dy * radio / dist;

            gc.strokeLine(x + offsetX, y + offsetY, hijoX - offsetX, hijoY - offsetY);
            dibujarNodo(gc, nodo.getIzquierdo(), hijoX, hijoY, separacion / 2);
        }

        // Dibuja línea al hijo derecho
        if (nodo.getDerecho() != null) {
            double hijoX = x + separacion;
            double hijoY = y + 50;

            double dx = hijoX - x;
            double dy = hijoY - y;
            double dist = Math.sqrt(dx * dx + dy * dy);
            double offsetX = dx * radio / dist;
            double offsetY = dy * radio / dist;

            gc.strokeLine(x + offsetX, y + offsetY, hijoX - offsetX, hijoY - offsetY);
            dibujarNodo(gc, nodo.getDerecho(), hijoX, hijoY, separacion / 2);
        }

        // Dibuja el nodo actual
        gc.strokeOval(x - radio, y - radio, radio * 2, radio * 2);
        gc.fillText(String.valueOf(nodo.getDato()), x - 4, y + 4);
    }
}