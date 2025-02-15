package Model;

public class Nodo {
    public int id;
    public String nombre;
    public int edad;
    public int ranking;
    public double puntuacionPromedio;
    public Nodo izquierda;
    public Nodo derecha;

    public Nodo(int id, String nombre, int edad, int ranking, double puntuacionPromedio) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.ranking = ranking;
        this.puntuacionPromedio = puntuacionPromedio;
        this.izquierda = null;
        this.derecha = null;
    }
}
