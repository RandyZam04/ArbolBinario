package Model;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

public class ArbolBinario {
    private Nodo raiz;

    // Construir el árbol del torneo
    public void construirTorneo(List<Nodo> jugadores) {
        int n = jugadores.size();
        int potencia = 1;
        while (potencia < n) {
            potencia *= 2;
        }

        int faltantes = potencia - n;
        for (int i = 0; i < faltantes; i++) {
            jugadores.add(new Nodo(-1, "BYE", 0, 0, 0));
        }

        Queue<Nodo> cola = new LinkedList<>(jugadores);

        while (cola.size() > 1) {
            Nodo jugadorA = cola.poll();
            Nodo jugadorB = cola.poll();

            String partidoNombre = "Partido: " + jugadorA.nombre + " vs " + jugadorB.nombre;
            Nodo partido = new Nodo(-1, partidoNombre, 0, 0, 0);
            partido.izquierda = jugadorA;
            partido.derecha = jugadorB;

            cola.add(partido);
        }

        this.raiz = cola.poll();
    }

    // Insertar un nuevo nodo
    public Nodo insertar(int id, String nombre, int edad, int ranking, double puntuacionPromedio) {
        return new Nodo(id, nombre, edad, ranking, puntuacionPromedio);
    }

    // Registrar el resultado de un partido
    public void registrarResultado(Nodo partido, Nodo ganador) {
        if (partido != null) {
            partido.nombre = "Ganador: " + ganador.nombre;
            partido.izquierda = ganador;
            partido.derecha = null;
        }
    }

    // Convertir el árbol en estructura visual
    public DefaultMutableTreeNode convertirArbolAVisual() {
        if (raiz == null) {
            return new DefaultMutableTreeNode("Árbol vacío");
        }
        return construirVisualTorneo(raiz);
    }

    private DefaultMutableTreeNode construirVisualTorneo(Nodo nodo) {
        if (nodo == null) return null;

        DefaultMutableTreeNode visual = new DefaultMutableTreeNode(nodo.nombre);
        DefaultMutableTreeNode izq = construirVisualTorneo(nodo.izquierda);
        DefaultMutableTreeNode der = construirVisualTorneo(nodo.derecha);

        if (izq != null) visual.add(izq);
        if (der != null) visual.add(der);

        return visual;
    }

    // Obtener el ganador final
    public Nodo obtenerGanador() {
        return raiz;
    }

    // Obtener todos los partidos disponibles
    public List<Nodo> obtenerPartidos() {
        List<Nodo> partidos = new ArrayList<>();
        obtenerPartidosRecursivo(raiz, partidos);
        return partidos;
    }

    private void obtenerPartidosRecursivo(Nodo nodo, List<Nodo> partidos) {
        if (nodo == null) return;
        if (nodo.nombre.startsWith("Partido")) {
            partidos.add(nodo);
        }
        obtenerPartidosRecursivo(nodo.izquierda, partidos);
        obtenerPartidosRecursivo(nodo.derecha, partidos);
    }
}
