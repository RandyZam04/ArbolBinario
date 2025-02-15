package Controller;

import Model.ArbolBinario;

public class TorneoController {
    private ArbolBinario torneo;

    public TorneoController() {
        this.torneo = new ArbolBinario();
    }

    public void agregarJugador(String nombre, int edad) {
        torneo.insertar(nombre, edad);
    }

    public void mostrarJugadores() {
        torneo.enOrden();
    }

    public ArbolBinario getTorneo() {
        return torneo;
    }
}
