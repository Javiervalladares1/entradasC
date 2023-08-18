public class Localidad {
    private String nombre;
    private double precio;
    private int capacidad;
    private int boletosVendidos;

    public Localidad(String nombre, double precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
        this.boletosVendidos = 0;
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

    public int getBoletosVendidos() {
        return boletosVendidos;
    }

    public void venderBoletos(int cantidad) {
        if (cantidad > 0 && capacidad >= cantidad) {
            capacidad -= cantidad;
            boletosVendidos += cantidad;
        }
    }

    public boolean tieneEspacio(int cantidad) {
        return capacidad >= cantidad;
    }
}
