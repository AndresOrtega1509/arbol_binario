package co.edu.uniquindio.arbol_binario.arbolbinario.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolBinario {

    private Nodo raiz;
    private int peso;

    public ArbolBinario() {

        raiz = null;
        peso = 0;
    }

    public boolean estaVacio(){
        return peso == 0;
    }

    public void agregarDato(int dato){

        Nodo nuevo = new Nodo(dato, null, null);
        insertar(nuevo, raiz);
        peso++;

    }

    private void insertar(Nodo nuevo, Nodo padre) {

        if (this.raiz == null){
            raiz = nuevo;
        }else {
            if (nuevo.getDato() <= padre.getDato()){
                if (padre.getIzquierdo() == null){
                    padre.setIzquierdo(nuevo);
                }else {
                    insertar(nuevo, padre.getIzquierdo());
                }

            }else {
                if (padre.getDerecho() == null){
                    padre.setDerecho(nuevo);
                }else {
                    insertar(nuevo, padre.getDerecho());
                }
            }
        }
    }

    public List<Integer> recorrerArbolInOrden(){
        List<Integer> recorrido = new ArrayList<>();
        inOrden(raiz, recorrido);
        return recorrido;
    }

    private void inOrden(Nodo raiz, List<Integer> recorrido) {

        if (raiz == null){
            return;
        }
        inOrden(raiz.getIzquierdo(), recorrido);
        System.out.print(raiz.getDato() + " ");
        recorrido.add(raiz.getDato());
        inOrden(raiz.getDerecho(), recorrido);
    }

    public List<Integer> recorrerArbolPreOrden(){
        List<Integer> recorrido = new ArrayList<>();
        preOrden(raiz, recorrido);
        return recorrido;
    }

    private void preOrden(Nodo raiz, List<Integer> recorrido) {

        if (raiz == null){
            return;
        }
        System.out.print(raiz.getDato() + " ");
        recorrido.add(raiz.getDato());
        preOrden(raiz.getIzquierdo(), recorrido);
        preOrden(raiz.getDerecho(), recorrido);
    }

    public List<Integer> recorrerArbolPosOrden(){
        List<Integer> recorrido = new ArrayList<>();
        posOrden(raiz, recorrido);
        return recorrido;
    }

    private void posOrden(Nodo raiz, List<Integer> recorrido) {

        if (raiz == null){
            return;
        }
        posOrden(raiz.getIzquierdo(), recorrido);
        posOrden(raiz.getDerecho(), recorrido);
        System.out.print(raiz.getDato() + " ");
        recorrido.add(raiz.getDato());
    }

    public boolean existeDato(int dato){

        return realizarBusqueda(dato, raiz);
    }

    private boolean realizarBusqueda(int dato, Nodo raiz) {

        if (raiz == null) {
            return false;
        }
        if (dato == raiz.getDato()) {
            return true;
        }
        if (dato < raiz.getDato()) {
            return realizarBusqueda(dato, raiz.getIzquierdo());
        } else {
            return realizarBusqueda(dato, raiz.getDerecho());
        }
    }

    public int obtenerPeso(){
        return peso;
    }

    public int obtenerAltura() {
        return calcularAltura(raiz);
    }

    private int calcularAltura(Nodo aux) {
        if (aux == null) {
            return 0;
        }

        int alturaIzquierda = calcularAltura(aux.getIzquierdo());
        int alturaDerecha = calcularAltura(aux.getDerecho());

        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    public int obtenerNivel(){

        return calcularAltura(raiz) - 1;
    }

    public int contarHojas(){

        return calcularCantidadHojas(raiz);
    }

    private int calcularCantidadHojas(Nodo aux) {

        if (aux == null) {
            return 0;
        }
        if (aux.getIzquierdo() == null && aux.getDerecho() == null){
            return 1;
        }
        // Suma de hojas en el subárbol izquierdo y derecho
        return calcularCantidadHojas(aux.getIzquierdo()) + calcularCantidadHojas(aux.getDerecho());
    }

    public int obtenerMenor() throws Exception {
        if (raiz == null) {
            throw new RuntimeException("El árbol está vacío.");
        }

        Nodo actual = raiz;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual.getDato();
    }

    public List<Integer> imprimirAmplitud() {
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return null;
        }
        List<Integer> recorridoAmplitud = new ArrayList<>();
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            System.out.print(actual.getDato() + " ");
            recorridoAmplitud.add(actual.getDato());

            if (actual.getIzquierdo() != null) {
                cola.add(actual.getIzquierdo());
            }
            if (actual.getDerecho() != null) {
                cola.add(actual.getDerecho());
            }
        }
        return recorridoAmplitud;
    }

    public void eliminarDato(int dato) {
        raiz = eliminarNodo(raiz, dato);
    }

    private Nodo eliminarNodo(Nodo aux, int dato) {
        if (aux == null) {
            return null; // Dato no encontrado
        }

        if (dato < aux.getDato()) {
            aux.setIzquierdo(eliminarNodo(aux.getIzquierdo(), dato));
        } else if (dato > aux.getDato()) {
            aux.setDerecho(eliminarNodo(aux.getDerecho(), dato));
        } else {
            // Caso 1: sin hijos
            if (aux.getIzquierdo() == null && aux.getDerecho() == null) {
                peso--;
                return null;
            }

            // Caso 2: un solo hijo
            if (aux.getIzquierdo() == null) {
                peso--;
                return aux.getDerecho();
            }
            if (aux.getDerecho() == null) {
                peso--;
                return aux.getIzquierdo();
            }

            // Caso 3: dos hijos
            // Buscar el menor del subárbol derecho (sucesor inorden)
            Nodo sucesor = encontrarMinimo(aux.getDerecho());
            aux.setDato(sucesor.getDato());
            aux.setDerecho(eliminarNodo(aux.getDerecho(), sucesor.getDato()));
        }

        return aux;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    public Nodo obtenerNodoMayor(){

        if (raiz == null){
            return null;
        }

        Nodo actual = raiz;

        while (actual.getDerecho() != null){
            actual = actual.getDerecho();
        }

        return actual;
    }

    public Nodo obtenerNodoMenor(){

        if (raiz == null){
            return null;
        }
        Nodo actual = raiz;

        while (actual.getIzquierdo() != null){
            actual = actual.getIzquierdo();
        }

        return actual;
    }

    public void borrarArbol(){
        raiz = null;
        peso = 0;
    }


    public Nodo obtenerRaiz() {
        return raiz;
    }
}
