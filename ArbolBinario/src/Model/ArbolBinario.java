package Model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ArbolBinario {
    private Nodo raiz;
    private ListaEnlazada listaEliminados;
    private Deque<String> historialResultados;
    private Queue<Nodo> partidosPendientes;
    private Map<Integer, Nodo> jugadores;

    public ArbolBinario() {
        this.raiz = null;
        this.listaEliminados = new ListaEnlazada();  // Asegúrate de que solo se crea aquí
        this.historialResultados = new ArrayDeque<>();
        this.partidosPendientes = new LinkedList<>();
        this.jugadores = new HashMap<>();
    }


    public void insertar(int id, String nombre, int edad, int ranking, double puntuacionPromedio) {
        if (jugadores.containsKey(id)) {
            System.out.println("Error: ID ya existente.");
            return;
        }
        Nodo nuevo = new Nodo(id, nombre, edad, ranking, puntuacionPromedio);
        jugadores.put(id, nuevo);
        raiz = insertarRec(raiz, nuevo);
    }

    private Nodo insertarRec(Nodo actual, Nodo nuevo) {
        if (actual == null) {
            return nuevo;
        }

        if (nuevo.ranking < actual.ranking) {
            actual.izquierda = insertarRec(actual.izquierda, nuevo);
        } else {
            actual.derecha = insertarRec(actual.derecha, nuevo);
        }
        return actual;
    }

    public Nodo buscar(int id) {
        return jugadores.get(id);
    }

    public boolean editarJugador(int id, int nuevaEdad, int nuevoRanking, double nuevaPuntuacion) {
        Nodo jugador = buscar(id);
        if (jugador != null) {
            jugador.edad = nuevaEdad;
            jugador.ranking = nuevoRanking;
            jugador.puntuacionPromedio = nuevaPuntuacion;
            return true;
        }
        return false;
    }

    public void eliminar(int id) {
        Nodo jugador = buscar(id);
        if (jugador != null) {
            listaEliminados.agregarEliminado(jugador.nombre, jugador.edad);
            jugadores.remove(id);
            raiz = eliminarRec(raiz, id);
            jugador.estado = "Eliminado";
        }
    }

    private Nodo eliminarRec(Nodo actual, int id) {
        if (actual == null) {
            return null;
        }
        if (id < actual.id) {
            actual.izquierda = eliminarRec(actual.izquierda, id);
        } else if (id > actual.id) {
            actual.derecha = eliminarRec(actual.derecha, id);
        } else {
            if (actual.izquierda == null) return actual.derecha;
            if (actual.derecha == null) return actual.izquierda;
            Nodo sucesor = encontrarMin(actual.derecha);
            actual.id = sucesor.id;
            actual.nombre = sucesor.nombre;
            actual.edad = sucesor.edad;
            actual.ranking = sucesor.ranking;
            actual.puntuacionPromedio = sucesor.puntuacionPromedio;
            actual.estado = sucesor.estado;
            actual.derecha = eliminarRec(actual.derecha, sucesor.id);
        }
        return actual;
    }

    private Nodo encontrarMin(Nodo actual) {
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    public void registrarResultado(int idGanador, int idPerdedor) {
        Nodo ganador = buscar(idGanador);
        Nodo perdedor = buscar(idPerdedor);
        if (ganador != null && perdedor != null) {
            perdedor.estado = "Eliminado";
            listaEliminados.agregarEliminado(perdedor.nombre, perdedor.edad);
            historialResultados.push("Ganador: " + ganador.id + " - Perdedor: " + perdedor.id);
            partidosPendientes.add(ganador);
        } else {
            System.out.println("Error: Uno o ambos jugadores no encontrados.");
        }
    }


    public void deshacerResultado() {
        if (!historialResultados.isEmpty()) {
            String resultado = historialResultados.pop();
            System.out.println("Se deshizo el resultado: " + resultado);
            String[] partes = resultado.split(" - ");
            int idPerdedor = Integer.parseInt(partes[1].split(": ")[1]);
            Nodo jugador = buscar(idPerdedor);
            if (jugador != null) {
                jugador.estado = "Activo";
                listaEliminados.removerEliminado(jugador.nombre);
                insertar(jugador.id, jugador.nombre, jugador.edad, jugador.ranking, jugador.puntuacionPromedio);
            }
        } else {
            System.out.println("No hay resultados para deshacer.");
        }
    }

    public String obtenerUltimoResultado() {
        return historialResultados.peek();
    }

    public void mostrarArbol() {
        mostrarArbolRec(raiz, 0);
    }

    private void mostrarArbolRec(Nodo actual, int nivel) {
        if (actual != null) {
            mostrarArbolRec(actual.derecha, nivel + 1);
            System.out.println(" ".repeat(nivel * 4) + "-> " + actual.nombre + " (ID: " + actual.id + ", Ranking: " + actual.ranking + ", Estado: " + actual.estado + ")");
            mostrarArbolRec(actual.izquierda, nivel + 1);
        }
    }
}