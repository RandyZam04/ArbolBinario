package Model;

public class ListaEnlazada {
	private NodoEliminado cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    // Agregar un jugador eliminado
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

    // Mostrar los jugadores eliminados
    public void mostrarEliminados() {
        if (cabeza == null) {
            System.out.println("No hay jugadores eliminados.");
            return;
        }
        NodoEliminado temp = cabeza;
        while (temp != null) {
            System.out.println("Nombre: " + temp.nombre + ", Edad: " + temp.edad);
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
}
