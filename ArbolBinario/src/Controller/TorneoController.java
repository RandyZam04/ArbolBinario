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

    public void insertarJugador(int id, String nombre, int edad, int ranking, double puntuacionPromedio) {
        Nodo nuevo = arbol.insertar(id, nombre, edad, ranking, puntuacionPromedio);
        jugadores.add(nuevo);
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
}
