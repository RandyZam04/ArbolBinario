package Model;

public class Nodo {
    public String nombre;
    public int edad;
    public int id;
    public int ranking;
    public double puntuacionPromedio;
    public String estado;
    Nodo izquierda, derecha;

    public Nodo(int id, String nombre, int edad, int ranking, double puntuacionPromedio) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.ranking = ranking;
        this.puntuacionPromedio = puntuacionPromedio;
        this.estado = "Activo";
        this.izquierda = null;
        this.derecha = null;
    }
}