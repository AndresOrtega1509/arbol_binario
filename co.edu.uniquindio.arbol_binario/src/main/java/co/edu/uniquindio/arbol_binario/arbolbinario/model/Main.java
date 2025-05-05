package co.edu.uniquindio.arbol_binario.arbolbinario.model;

public class Main {

    public static void main(String[] args) {

        ArbolBinario arbolBinario = new ArbolBinario();

        arbolBinario.agregarDato(20);
        arbolBinario.agregarDato(18);
        arbolBinario.agregarDato(7);
        arbolBinario.agregarDato(23);
        arbolBinario.agregarDato(19);
        arbolBinario.agregarDato(30);

        arbolBinario.recorrerArbolInOrden();
        System.out.println();
        arbolBinario.recorrerArbolPreOrden();
        System.out.println();
        arbolBinario.recorrerArbolPosOrden();
        System.out.println();
        System.out.println(arbolBinario.existeDato(19));
        System.out.println(arbolBinario.obtenerPeso());
    }
}
