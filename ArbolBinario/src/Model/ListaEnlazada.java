package Model;

public class ListaEnlazada {
    private NodoEliminado cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void agregarEliminado(String nombre, int edad) {
        NodoEliminado nuevoNodo = new NodoEliminado(nombre, edad);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoEliminado temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
    }



    // Remover un jugador eliminado
    public void removerEliminado(String nombre) {
        if (cabeza == null) return;

        if (cabeza.nombre.equals(nombre)) {
            cabeza = cabeza.siguiente;
            return;
        }

        NodoEliminado temp = cabeza;
        while (temp.siguiente != null) {
            if (temp.siguiente.nombre.equals(nombre)) {
                temp.siguiente = temp.siguiente.siguiente;
                return;
            }
            temp = temp.siguiente;
        }
    }

    // Clase interna NodoEliminado
    private class NodoEliminado {
        String nombre;
        int edad;
        NodoEliminado siguiente;

        public NodoEliminado(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
            this.siguiente = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("[ ");
        NodoEliminado temp = cabeza;
        while (temp != null) {
            resultado.append("Nombre: ").append(temp.nombre).append(", Edad: ").append(temp.edad).append("; ");
            temp = temp.siguiente;
        }
        resultado.append("]");
        return resultado.toString();
    }
}
