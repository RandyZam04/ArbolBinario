package Model;

public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public void insertar(String nombre, int edad) {
        raiz = insertarRec(raiz, nombre, edad);
    }

    private Nodo insertarRec(Nodo nodo, String nombre, int edad) {
        if (nodo == null) {
            return new Nodo(nombre, edad);
        }
        if (edad < nodo.edad) {
            nodo.izquierda = insertarRec(nodo.izquierda, nombre, edad);
        } else {
            nodo.derecha = insertarRec(nodo.derecha, nombre, edad);
        }
        return nodo;
    }

    public void enOrden() {
        enOrdenRec(raiz);
    }

    private void enOrdenRec(Nodo nodo) {
        if (nodo != null) {
            enOrdenRec(nodo.izquierda);
            System.out.println(nodo.nombre + " - " + nodo.edad);
            enOrdenRec(nodo.derecha);
        }
    }

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.insertar("Jugador A", 25);
        arbol.insertar("Jugador B", 20);
        arbol.insertar("Jugador C", 30);
        
        System.out.println("Recorrido en orden:");
        arbol.enOrden();
    }
}
