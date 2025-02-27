package View;

import Controller.TorneoController;
import Model.Nodo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TorneoGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private TorneoController controller;
    private JTextField txtId, txtNombre;

    public TorneoGUI() {
        controller = new TorneoController();
        setTitle("Gestión del Torneo Deportivo");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de ingreso
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 5));

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        // Botones de acción
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 3, 10, 10));

        JButton btnAgregar = new JButton("Agregar Jugador");
        JButton btnConstruirTorneo = new JButton("Construir Torneo");
        JButton btnMostrarArbol = new JButton("Mostrar Árbol");
        JButton btnRegistrarResultado = new JButton("Registrar Resultado");
        JButton btnMostrarGanador = new JButton("Mostrar Ganador");
     // Agregamos botones adicionales para Editar y Eliminar
        JButton btnEditarJugador = new JButton("Editar Jugador");
        JButton btnEliminarJugador = new JButton("Eliminar Jugador");

        // Asignar acciones a los botones
        btnAgregar.addActionListener(_ -> agregarJugador());
        btnConstruirTorneo.addActionListener(_ -> controller.construirTorneo());
        btnMostrarArbol.addActionListener(_ -> mostrarArbol());
        btnRegistrarResultado.addActionListener(_ -> registrarResultado());
        btnMostrarGanador.addActionListener(_ -> mostrarGanador());
        btnEditarJugador.addActionListener(_ -> editarJugador());
        btnEliminarJugador.addActionListener(_ -> eliminarJugador());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnConstruirTorneo);
        panelBotones.add(btnMostrarArbol);
        panelBotones.add(btnRegistrarResultado);
        panelBotones.add(btnMostrarGanador);
        panelBotones.add(btnEditarJugador);
        panelBotones.add(btnEliminarJugador);

        // Agregar componentes a la ventana
        add(panel, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }

    private void agregarJugador() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();

            boolean agregado = controller.insertarJugador(id, nombre);
            if (!agregado) {
                JOptionPane.showMessageDialog(this, "Error: Ya existe un jugador con ese ID.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Jugador agregado correctamente.");
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID debe ser un número válido.");
        }
    }



    private void mostrarArbol() {
        Nodo raiz = controller.getRaiz();
        if (raiz == null) {
            JOptionPane.showMessageDialog(this, "Árbol vacío o no construido.");
            return;
        }

        JFrame frame = new JFrame("Visualización del Torneo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new TorneoPanel(raiz));
        frame.setVisible(true);
    }

    private void registrarResultado() {
        List<Nodo> partidos = controller.obtenerPartidos();

        if (partidos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay partidos disponibles para registrar resultados.");
            return;
        }

        // Seleccionar partido
        String[] nombresPartidos = partidos.stream().map(n -> n.nombre).toArray(String[]::new);
        String partidoSeleccionado = (String) JOptionPane.showInputDialog(
                this, "Seleccione el partido:",
                "Registrar Resultado",
                JOptionPane.QUESTION_MESSAGE,
                null, nombresPartidos, nombresPartidos[0]);

        if (partidoSeleccionado == null) return;

        // Buscar el partido correspondiente
        Nodo partido = partidos.stream().filter(p -> p.nombre.equals(partidoSeleccionado)).findFirst().orElse(null);
        if (partido == null) {
            JOptionPane.showMessageDialog(this, "Partido no encontrado.");
            return;
        }

        // Seleccionar el ganador
        String[] jugadores = {partido.izquierda.nombre, partido.derecha.nombre};
        String ganadorNombre = (String) JOptionPane.showInputDialog(
                this, "Seleccione el ganador:",
                "Registrar Resultado",
                JOptionPane.QUESTION_MESSAGE,
                null, jugadores, jugadores[0]);

        if (ganadorNombre == null) return;

        Nodo ganador = partido.izquierda.nombre.equals(ganadorNombre) ? partido.izquierda : partido.derecha;

        controller.registrarResultado(partido, ganador);
        JOptionPane.showMessageDialog(this, "Ganador registrado: " + ganador.nombre);
    }

    private void mostrarGanador() {
        String ganador = controller.obtenerGanador();

        if (ganador != null) {
            // Limpiar cualquier acumulación extra de "Ganador: "
            ganador = ganador.replace("Ganador: ", "");

            JOptionPane.showMessageDialog(this, "El ganador es: " + ganador);
        } else {
            JOptionPane.showMessageDialog(this, "Aún no hay ganador.");
        }
    }


    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TorneoGUI().setVisible(true));
    }
 // Método para editar un jugador
    private void editarJugador() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese ID del jugador a editar:"));
            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:");

            boolean editado = controller.editarJugador(id, nuevoNombre);
            String mensaje = editado ? "Jugador editado correctamente." : "Jugador no encontrado.";
            JOptionPane.showMessageDialog(this, mensaje);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido. Debe ser un número.");
        }
    }


    // Método para eliminar un jugador
    private void eliminarJugador() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese ID del jugador a eliminar:"));
            boolean eliminado = controller.eliminarJugador(id);
            String mensaje = eliminado ? "Jugador eliminado correctamente." : "Jugador no encontrado.";
            JOptionPane.showMessageDialog(this, mensaje);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido. Debe ser un número.");
        }
    }

}

