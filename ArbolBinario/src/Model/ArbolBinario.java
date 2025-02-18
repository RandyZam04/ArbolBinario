package Model;

import javax.swing.JOptionPane;
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

            // Se asegura que el nombre del partido solo se forme con los jugadores directos
            String nombreA = (jugadorA.izquierda == null && jugadorA.derecha == null) ? jugadorA.nombre : "Ganador del partido";
            String nombreB = (jugadorB.izquierda == null && jugadorB.derecha == null) ? jugadorB.nombre : "Ganador del partido";

            String partidoNombre = "Partido: " + nombreA + " vs " + nombreB;
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
        if (partido == null) {
            JOptionPane.showMessageDialog(null, "No se puede registrar el resultado porque el partido no existe.");
            return;
        }

        if (partido.izquierda == null || partido.derecha == null) {
            JOptionPane.showMessageDialog(null, "No se puede registrar el resultado porque uno de los jugadores fue eliminado.");
            return;
        }

        // Asegurar que no se duplique "Ganador: " en el nombre
        String nuevoNombre = ganador.nombre.replace("Ganador: ", ""); 
        partido.nombre = "Ganador: " + nuevoNombre;

        partido.izquierda = ganador;
        partido.derecha = null;

        JOptionPane.showMessageDialog(null, "Ganador registrado: " + partido.nombre.replace("Ganador: ", ""));
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
    
 // Método para editar un nodo existente por su ID
    public boolean editarNodo(int id, String nuevoNombre) {
        return editarNodoRecursivo(raiz, id, nuevoNombre);
    }

    private boolean editarNodoRecursivo(Nodo nodo, int id, String nuevoNombre) {
        if (nodo == null) return false;

        if (nodo.id == id) {
            nodo.nombre = nuevoNombre;
            return true;
        }

        return editarNodoRecursivo(nodo.izquierda, id, nuevoNombre) || editarNodoRecursivo(nodo.derecha, id, nuevoNombre);
    }


    // Método para eliminar un nodo del árbol
    public boolean eliminarNodo(int id) {
        if (raiz == null) return false;
        raiz = eliminarNodoRecursivo(raiz, id);
        return true;
    }

    private Nodo eliminarNodoRecursivo(Nodo nodo, int id) {
        if (nodo == null) return null;

        if (nodo.id == id) {
            // Caso 1: Nodo hoja
            if (nodo.izquierda == null && nodo.derecha == null) {
                return null;
            }
            // Caso 2: Un hijo
            if (nodo.izquierda == null) {
                return nodo.derecha;
            }
            if (nodo.derecha == null) {
                return nodo.izquierda;
            }
            // Caso 3: Dos hijos
            Nodo sucesor = encontrarMinimo(nodo.derecha);
            nodo.id = sucesor.id;
            nodo.nombre = sucesor.nombre;
            nodo.edad = sucesor.edad;
            nodo.ranking = sucesor.ranking;
            nodo.puntuacionPromedio = sucesor.puntuacionPromedio;
            nodo.derecha = eliminarNodoRecursivo(nodo.derecha, sucesor.id);
            return nodo;
        }

        nodo.izquierda = eliminarNodoRecursivo(nodo.izquierda, id);
        nodo.derecha = eliminarNodoRecursivo(nodo.derecha, id);
        return nodo;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }

    
}
