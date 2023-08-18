import java.util.Scanner;

public class Comprador {
    private String nombre;
    private String email;
    private int cantidadBoletos;
    private double presupuestoMaximo;

    public Comprador(String nombre, String email, int cantidadBoletos, double presupuestoMaximo) {
        this.nombre = nombre;
        this.email = email;
        this.cantidadBoletos = cantidadBoletos;
        this.presupuestoMaximo = presupuestoMaximo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public double getPresupuestoMaximo() {
        return presupuestoMaximo;
    }

    public void actualizarDatos() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Actualización de datos del comprador:");
        System.out.print("Ingresa tu nombre: ");
        nombre = scanner.nextLine();

        System.out.print("Ingresa tu email: ");
        email = scanner.nextLine();

        System.out.print("Ingresa la cantidad de boletos a comprar: ");
        cantidadBoletos = scanner.nextInt();

        System.out.print("Ingresa tu presupuesto máximo: ");
        presupuestoMaximo = scanner.nextDouble();

        System.out.println("Datos actualizados con éxito.");
    }
}

