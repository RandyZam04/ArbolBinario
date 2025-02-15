package Controller;

import Model.ArbolBinario;
import Model.ListaEnlazada;

public class TorneoController {
    private ArbolBinario arbol;
    private ListaEnlazada listaEliminados;

    public TorneoController() {
        this.arbol = new ArbolBinario();
        this.listaEliminados = new ListaEnlazada();
    }

    // Insertar jugador
    public void insertarJugador(String nombre, int edad) {
        arbol.insertar(nombre, edad);
    }

    // Editar jugador
    public boolean editarJugador(String nombre, int nuevaEdad) {
        return arbol.editarJugador(nombre, nuevaEdad);
    }

    // Eliminar jugador
    public void eliminarJugador(String nombre) {
        arbol.eliminar(nombre);
    }

    // Mostrar todos los jugadores
    public void mostrarJugadores() {
        arbol.enOrden();
    }

    // Mostrar jugadores eliminados
    public void mostrarEliminados() {
        arbol.mostrarEliminados();
    }
}
