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
            System.out.println("5. Registrar resultado de partido");
            System.out.println("6. Deshacer último resultado");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

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
                    registrarResultado();
                    break;
                case 6:
                    deshacerResultado();
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }


    // Insertar un nuevo jugador con validación de ID único
    private void insertarJugador() {
        int id;
        while (true) {
            System.out.print("Ingrese el ID del jugador: ");
            id = scanner.nextInt();
            scanner.nextLine();
            if (controller.jugadorExiste(id)) {
                System.out.println("El ID ya existe. Por favor, ingrese un ID único.");
            } else {
                break;
            }
        }

        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del jugador: ");
        int edad = scanner.nextInt();
        System.out.print("Ingrese el ranking del jugador: ");
        int ranking = scanner.nextInt();
        System.out.print("Ingrese la puntuación promedio del jugador: ");
        double puntuacionPromedio = scanner.nextDouble();
        scanner.nextLine();
        controller.insertarJugador(id, nombre, edad, ranking, puntuacionPromedio);
        System.out.println("Jugador insertado correctamente.");
    }

    // Editar la edad de un jugador
    private void editarJugador() {
        System.out.print("Ingrese el ID del jugador a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la nueva edad: ");
        int nuevaEdad = scanner.nextInt();
        System.out.print("Ingrese el nuevo ranking: ");
        int nuevoRanking = scanner.nextInt();
        System.out.print("Ingrese la nueva puntuación promedio: ");
        double nuevaPuntuacion = scanner.nextDouble();
        scanner.nextLine();
        boolean exito = controller.editarJugador(id, nuevaEdad, nuevoRanking, nuevaPuntuacion);
        if (exito) {
            System.out.println("Jugador editado correctamente.");
        } else {
            System.out.println("Jugador no encontrado.");
        }
    }

    // Eliminar un jugador
    private void eliminarJugador() {
        System.out.print("Ingrese el ID del jugador a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.eliminarJugador(id);
        System.out.println("Jugador eliminado correctamente.");
    }

    // Mostrar todos los jugadores
    private void mostrarJugadores() {
        System.out.println("\n--- Jugadores del Torneo ---");
        controller.mostrarJugadores();
    }


    // Registrar resultado de partido
    private void registrarResultado() {
        System.out.print("Ingrese el ID del jugador ganador: ");
        int idGanador = scanner.nextInt();
        System.out.print("Ingrese el ID del jugador perdedor: ");
        int idPerdedor = scanner.nextInt();
        scanner.nextLine();
        controller.registrarResultado(idGanador, idPerdedor);
        System.out.println("Resultado registrado correctamente.");
    }

    // Deshacer el último resultado
    private void deshacerResultado() {
        controller.deshacerResultado();
    }

}