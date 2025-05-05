package co.edu.uniquindio.arbol_binario.arbolbinario.model;

public class ArbolBinario {

    private Nodo raiz;
    private int peso;

    public ArbolBinario() {

        raiz = null;
        peso = 0;
    }

    private boolean estaVacio(){
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

    public void recorrerArbolInOrden(){

        inOrden(raiz);
    }

    private void inOrden(Nodo raiz) {

        if (raiz == null){
            return;
        }
        inOrden(raiz.getIzquierdo());
        System.out.print(raiz.getDato() + " ");
        inOrden(raiz.getDerecho());
    }

    public void recorrerArbolPreOrden(){

        preOrden(raiz);
    }

    private void preOrden(Nodo raiz) {

        if (raiz == null){
            return;
        }
        System.out.print(raiz.getDato() + " ");
        preOrden(raiz.getIzquierdo());
        preOrden(raiz.getDerecho());
    }

    public void recorrerArbolPosOrden(){

        posOrden(raiz);
    }

    private void posOrden(Nodo raiz) {

        if (raiz == null){
            return;
        }
        posOrden(raiz.getIzquierdo());
        posOrden(raiz.getDerecho());
        System.out.print(raiz.getDato() + " ");
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
}
