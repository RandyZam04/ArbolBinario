package Controller;

import Model.ArbolBinario;
import Model.Nodo;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class TorneoController {
    private ArbolBinario arbol;
    private List<Nodo> jugadores;

    public TorneoController() {
        this.arbol = new ArbolBinario();
        this.jugadores = new ArrayList<>();
    }

    public boolean insertarJugador(int id, String nombre) {
        // Verificar si el ID ya existe en la lista de jugadores
        for (Nodo jugador : jugadores) {
            if (jugador.id == id) {
                return false; // ID duplicado, no se puede agregar
            }
        }

        // Insertar nuevo jugador
        Nodo nuevo = arbol.insertar(id, nombre, 0, 0, 0);
        jugadores.add(nuevo);
        return true;
    }


    public void construirTorneo() {
        arbol.construirTorneo(jugadores);
    }

    public void registrarResultado(Nodo partido, Nodo ganador) {
        arbol.registrarResultado(partido, ganador);
    }

    public String obtenerGanador() {
        Nodo ganador = arbol.obtenerGanador();
        return (ganador != null) ? ganador.nombre : null;
    }

    public Nodo getRaiz() {
        return arbol.obtenerGanador();
    }

    public DefaultMutableTreeNode getArbolComoNodo() {
        return arbol.convertirArbolAVisual();
    }

    public List<Nodo> getJugadores() {
        return jugadores;
    }

    // Nuevo método para obtener partidos disponibles
    public List<Nodo> obtenerPartidos() {
        return arbol.obtenerPartidos();
    }
    
 // Método para editar un nodo existente
    public boolean editarJugador(int id, String nuevoNombre) {
        return arbol.editarNodo(id, nuevoNombre);
    }


    // Método para eliminar un nodo existente
    public boolean eliminarJugador(int id) {
        return arbol.eliminarNodo(id);
    }

}
