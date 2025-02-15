package Model;

public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    // Insertar un jugador en el árbol
    public void insertar(String nombre, int edad) {
        raiz = insertarRec(raiz, nombre, edad);
    }

    private Nodo insertarRec(Nodo actual, String nombre, int edad) {
        if (actual == null) {
            return new Nodo(nombre, edad);
        }

        if (edad < actual.edad) {
            actual.izquierda = insertarRec(actual.izquierda, nombre, edad);
        } else {
            actual.derecha = insertarRec(actual.derecha, nombre, edad);
        }
        return actual;
    }

    // Buscar un jugador en el árbol
    public Nodo buscar(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Nodo buscarRec(Nodo actual, String nombre) {
        if (actual == null || actual.nombre.equals(nombre)) {
            return actual;
        }

        if (nombre.compareTo(actual.nombre) < 0) {
            return buscarRec(actual.izquierda, nombre);
        } else {
            return buscarRec(actual.derecha, nombre);
        }
    }

    // Editar un jugador (cambiar su edad)
    public boolean editarJugador(String nombre, int nuevaEdad) {
        Nodo jugador = buscar(nombre);
        if (jugador != null) {
            jugador.edad = nuevaEdad;
            return true;
        }
        return false;
    }

    // Eliminar un jugador
    public void eliminar(String nombre) {
        raiz = eliminarRec(raiz, nombre);
    }

    private Nodo eliminarRec(Nodo actual, String nombre) {
        if (actual == null) {
            return null;
        }

        if (nombre.compareTo(actual.nombre) < 0) {
            actual.izquierda = eliminarRec(actual.izquierda, nombre);
        } else if (nombre.compareTo(actual.nombre) > 0) {
            actual.derecha = eliminarRec(actual.derecha, nombre);
        } else {
            // Caso 1: Nodo sin hijos
            if (actual.izquierda == null && actual.derecha == null) {
                return null;
            }
            // Caso 2: Un solo hijo
            if (actual.izquierda == null) {
                return actual.derecha;
            } else if (actual.derecha == null) {
                return actual.izquierda;
            }
            // Caso 3: Dos hijos
            Nodo sucesor = encontrarMin(actual.derecha);
            actual.nombre = sucesor.nombre;
            actual.edad = sucesor.edad;
            actual.derecha = eliminarRec(actual.derecha, sucesor.nombre);
        }
        return actual;
    }

    private Nodo encontrarMin(Nodo actual) {
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    // Mostrar los jugadores en orden
    public void enOrden() {
        enOrdenRec(raiz);
    }

    private void enOrdenRec(Nodo actual) {
        if (actual != null) {
            enOrdenRec(actual.izquierda);
            System.out.println("Nombre: " + actual.nombre + ", Edad: " + actual.edad);
            enOrdenRec(actual.derecha);
        }
    }
}