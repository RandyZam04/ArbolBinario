package Model;

public class Nodo {
    String nombre;
    int edad;
    Nodo izquierda, derecha;

    public Nodo(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.izquierda = null;
        this.derecha = null;
    }
}
