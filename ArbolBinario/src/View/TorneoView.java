package View;

import java.util.Scanner;

import Controller.TorneoController;

public class TorneoView {
    private TorneoController controller;
    private Scanner scanner;

    public TorneoView() {
        this.controller = new TorneoController();
        this.scanner = new Scanner(System.in);
    }

    // Mostrar menú y manejar las opciones
    public void mostrarMenu() {
        while (true) {
            System.out.println("\n--- Menú del Torneo ---");
            System.out.println("1. Insertar jugador");
            System.out.println("2. Editar jugador");
            System.out.println("3. Eliminar jugador");
            System.out.println("4. Mostrar jugadores");
            System.out.println("5. Mostrar jugadores eliminados");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    insertarJugador();
                    break;
                case 2:
                    editarJugador();
                    break;
                case 3:
                    eliminarJugador();
                    break;
                case 4:
                    mostrarJugadores();
                    break;
                case 5:
                    mostrarEliminados();
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }

    // Insertar un nuevo jugador
    private void insertarJugador() {
        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del jugador: ");
        int edad = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer
        controller.insertarJugador(nombre, edad);
        System.out.println("Jugador insertado correctamente.");
    }

    // Editar la edad de un jugador
    private void editarJugador() {
        System.out.print("Ingrese el nombre del jugador a editar: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la nueva edad: ");
        int nuevaEdad = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer
        boolean exito = controller.editarJugador(nombre, nuevaEdad);
        if (exito) {
            System.out.println("Jugador editado correctamente.");
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    // Eliminar un jugador
    private void eliminarJugador() {
        System.out.print("Ingrese el nombre del jugador a eliminar: ");
        String nombre = scanner.nextLine();
        controller.eliminarJugador(nombre);
        System.out.println("Jugador eliminado correctamente.");
    }

    // Mostrar todos los jugadores
    private void mostrarJugadores() {
        System.out.println("\n--- Jugadores del Torneo ---");
        controller.mostrarJugadores();
    }

    // Mostrar jugadores eliminados
    private void mostrarEliminados() {
        System.out.println("\n--- Jugadores Eliminados ---");
        controller.mostrarEliminados();
    }
}