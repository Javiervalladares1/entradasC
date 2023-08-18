import java.util.Scanner;

public class CompraBoleto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la solicitud de compra de boletos.");

        System.out.print("Ingresa el código especial: ");
        int codigoEspecial = scanner.nextInt();

        if (NumeroFibonacci.esFibonacci(codigoEspecial)) {
            comprarBoletosEspeciales();
        } else {
            System.out.println("Código especial inválido. Continuando con el proceso de compra.");

            System.out.print("Ingresa tu nombre: ");
            String nombre = scanner.nextLine(); // Consumir la nueva línea pendiente
            nombre = scanner.nextLine();

            System.out.print("Ingresa tu email: ");
            String email = scanner.nextLine();

            System.out.print("Ingresa la cantidad de boletos a comprar: ");
            int cantidadBoletos = scanner.nextInt();

            System.out.print("Ingresa tu presupuesto máximo: ");
            double presupuestoMaximo = scanner.nextDouble();

            int ticket = TicketManager.generarNumeroAleatorio(1, 15000);
            int a = TicketManager.generarNumeroAleatorio(1, 15000);
            int b = TicketManager.generarNumeroAleatorio(1, 15000);

            if (esAptoParaCompra(ticket, a, b)) {
                comprarBoletos(nombre, email, cantidadBoletos, presupuestoMaximo);
            } else {
                System.out.println("El ticket no es apto para comprar boletos.");
            }
        }

        scanner.close();
    }

    public static boolean esAptoParaCompra(int ticket, int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        return ticket >= min && ticket <= max;
    }

    public static void comprarBoletos(String nombre, String email, int cantidadBoletos, double presupuestoMaximo) {
        Localidad[] localidades = {
            new Localidad("Localidad 1", 100, 20),
            new Localidad("Localidad 5", 500, 20),
            new Localidad("Localidad 10", 1000, 20)
        };

        int localidadIndex = TicketManager.generarNumeroAleatorio(0, 2);
        Localidad localidadSeleccionada = localidades[localidadIndex];

        System.out.println("Localidad seleccionada: " + localidadSeleccionada.getNombre());

        if (localidadSeleccionada.getCapacidad() > 0) {
            double precioTotal = cantidadBoletos * localidadSeleccionada.getPrecio();

            if (precioTotal <= presupuestoMaximo) {
                int entradasDisponibles = Math.min(localidadSeleccionada.getCapacidad(), cantidadBoletos);

                System.out.println("Cantidad de boletos disponibles: " + entradasDisponibles);
                double costoTotal = entradasDisponibles * localidadSeleccionada.getPrecio();
                System.out.println("Costo total: $" + costoTotal);

                localidadSeleccionada.capacidad -= entradasDisponibles;
            } else {
                System.out.println("El precio total excede tu presupuesto. No se puede realizar la compra.");
            }
        } else {
            System.out.println("Lo sentimos, la localidad está agotada.");
        }
    }

    public static void comprarBoletosEspeciales() {
        System.out.println("¡Código especial válido! Puedes comprar boletos en la Localidad 10 por $20,000.");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingresa tu email: ");
        String email = scanner.nextLine();

        Localidad localidadEspecial = new Localidad("Localidad 10", 1000, 20);

        if (localidadEspecial.getCapacidad() > 0) {
            int cantidadBoletos = Math.min(localidadEspecial.getCapacidad(), 1);

            double costoTotal = cantidadBoletos * 20000;
            System.out.println("Costo total: $" + costoTotal);

            localidadEspecial.capacidad -= cantidadBoletos;
        } else {
            System.out.println("Lo sentimos, la localidad está agotada.");
        }

        scanner.close();
    }
}

