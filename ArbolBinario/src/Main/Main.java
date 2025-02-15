package Main;

import Controller.TorneoController;
import View.TorneoView;

public class Main {
	public static void main(String[] args) {
        TorneoController controlador = new TorneoController();
        TorneoView vista = new TorneoView(controlador);
        vista.mostrarMenu();
    }
}
