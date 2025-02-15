package View;

import java.util.Scanner;

import Controller.TorneoController;

public class TorneoView {
    private TorneoController controlador;
    private Scanner scanner;

    public TorneoView(TorneoController controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n===== Gestión de Torneos =====");
            System.out.println("1. Agregar Jugador");
            System.out.println("2. Mostrar Jugadores");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    agregarJugador();
                    break;
                case 2:
                    mostrarJugadores();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    private void agregarJugador() {
        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del jugador: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        controlador.agregarJugador(nombre, edad);
        System.out.println("Jugador agregado correctamente.");
    }

    private void mostrarJugadores() {
        System.out.println("\n=== Jugadores en el Torneo ===");
        controlador.mostrarJugadores();
    }
}
