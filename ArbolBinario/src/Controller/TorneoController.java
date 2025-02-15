package Controller;

import Model.ArbolBinario;
import Model.ListaEnlazada;
import Model.Nodo;

public class TorneoController {
    private ArbolBinario arbol;
    private ListaEnlazada listaEliminados;

    public TorneoController() {
        this.arbol = new ArbolBinario();
        this.listaEliminados = new ListaEnlazada();
    }

    // Insertar jugador
    public void insertarJugador(int id, String nombre, int edad, int ranking, double puntuacionPromedio) {
        arbol.insertar(id, nombre, edad, ranking, puntuacionPromedio);
    }

    // Editar jugador
    public boolean editarJugador(int id, int nuevaEdad, int nuevoRanking, double nuevaPuntuacion) {
        return arbol.editarJugador(id, nuevaEdad, nuevoRanking, nuevaPuntuacion);
    }

    // Eliminar jugador
    public void eliminarJugador(int id) {
        arbol.eliminar(id);
        Nodo eliminado = arbol.buscar(id);
        if (eliminado != null) {
            listaEliminados.agregarEliminado(eliminado.nombre, eliminado.edad);
        }
    }

    // Mostrar todos los jugadores
    public void mostrarJugadores() {
        arbol.mostrarArbol();
    }


    // Registrar resultado de partido
    public void registrarResultado(int idGanador, int idPerdedor) {
        arbol.registrarResultado(idGanador, idPerdedor);
    }

    // Deshacer último resultado
    public void deshacerResultado() {
        arbol.deshacerResultado();
        String resultado = arbol.obtenerUltimoResultado();
        if (resultado != null) {
            String[] partes = resultado.split(" - ");
            String[] ganador = partes[0].split(": ");
            String[] perdedor = partes[1].split(": ");
            int idPerdedor = Integer.parseInt(perdedor[1]);
            Nodo jugador = arbol.buscar(idPerdedor);
            if (jugador != null) {
                jugador.estado = "Activo";
                listaEliminados.removerEliminado(jugador.nombre);
                arbol.insertar(jugador.id, jugador.nombre, jugador.edad, jugador.ranking, jugador.puntuacionPromedio);
            }
        }
    }

    // Verificar si un jugador existe por ID
    public boolean jugadorExiste(int id) {
        return arbol.buscar(id) != null;
    }
}
