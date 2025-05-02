module co.edu.uniquindio.arbol_binario.arbolbinario {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.arbol_binario.arbolbinario to javafx.fxml;
    exports co.edu.uniquindio.arbol_binario.arbolbinario;
}