package co.edu.uniquindio.arbol_binario.arbolbinario.model;

public class Main {

    public static void main(String[] args) throws Exception {

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
        System.out.println(arbolBinario.obtenerAltura());
        System.out.println(arbolBinario.obtenerNivel());
        System.out.println(arbolBinario.contarHojas());
        System.out.println(arbolBinario.obtenerMenor());
        arbolBinario.imprimirAmplitud();
        System.out.println();
        arbolBinario.eliminarDato(1);
        arbolBinario.recorrerArbolPreOrden();
        System.out.println();
        System.out.println(arbolBinario.obtenerNodoMayor());
        System.out.println(arbolBinario.obtenerNodoMenor());
        arbolBinario.borrarArbol();
        arbolBinario.recorrerArbolPreOrden();
    }
}
