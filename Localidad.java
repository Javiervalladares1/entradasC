public class Localidad {
    private String nombre;
    private double precio;
    private int capacidad;

    public Localidad(String nombre, double precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean tieneEspacio(int cantidad) {
        return capacidad >= cantidad;
    }

    public void venderBoletos(int cantidad) {
        capacidad -= cantidad;
    }
}
