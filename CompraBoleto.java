import java.util.Scanner;

public class CompraBoleto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Localidad[] localidades = {
            new Localidad("Localidad 1", 100, 20),
            new Localidad("Localidad 5", 500, 20),
            new Localidad("Localidad 10", 1000, 20)
        };

        Comprador comprador = null;

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    comprador = nuevoComprador();
                    break;
                case 2:
                    if (comprador != null) {
                        nuevaSolicitudBoletos(comprador, localidades);
                    } else {
                        System.out.println("Primero debes ingresar como nuevo comprador.");
                    }
                    break;
                case 3:
                    consultarDisponibilidadTotal(localidades);
                    break;
                case 4:
                    if (comprador != null) {
                        consultarDisponibilidadIndividual(localidades);
                    } else {
                        System.out.println("Primero debes ingresar como nuevo comprador.");
                    }
                    break;
                case 5:
                    reporteCaja(localidades);
                    break;
                case 6:
                    if (comprador != null) {
                        ingresarCodigoEspecial(comprador, localidades);
                    } else {
                        System.out.println("Primero debes ingresar como nuevo comprador.");
                    }
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n--- Menú ---");
        System.out.println("1. Nuevo comprador");
        System.out.println("2. Nueva solicitud de boletos");
        System.out.println("3. Consultar disponibilidad total");
        System.out.println("4. Consultar disponibilidad individual");
        System.out.println("5. Reporte de caja");
        System.out.println("6. Código Especial");
        System.out.println("7. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public static Comprador nuevoComprador() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Nuevo Comprador ---");
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingresa tu email: ");
        String email = scanner.nextLine();

        System.out.print("Ingresa la cantidad de boletos a comprar: ");
        int cantidadBoletos = scanner.nextInt();

        System.out.print("Ingresa tu presupuesto máximo: ");
        double presupuestoMaximo = scanner.nextDouble();

        System.out.println("¡Nuevo comprador registrado!");

        return new Comprador(nombre, email, cantidadBoletos, presupuestoMaximo);
    }

    public static void nuevaSolicitudBoletos(Comprador comprador, Localidad[] localidades) {
        Scanner scanner = new Scanner(System.in);

        int ticket = TicketManager.generarNumeroAleatorio(1, 15000);

        if (TicketManager.esTicketApto(ticket)) {
            System.out.println("¡Solicitud de compra realizada con éxito!");
            System.out.println("Detalles de la compra:");
            System.out.println("Nombre: " + comprador.getNombre());
            System.out.println("Email: " + comprador.getEmail());
            System.out.println("Cantidad de boletos: " + comprador.getCantidadBoletos());
            System.out.println("Presupuesto máximo: $" + comprador.getPresupuestoMaximo());
            System.out.println("Número de ticket: " + ticket);

            int localidadIndex = TicketManager.generarNumeroAleatorio(0, 2);
            Localidad localidadSeleccionada = localidades[localidadIndex];

            System.out.println("Localidad seleccionada: " + localidadSeleccionada.getNombre());

            if (localidadSeleccionada.getCapacidad() > 0) {
                System.out.print("Ingresa la cantidad de boletos deseados: ");
                int cantidadDeseada = scanner.nextInt();

                if (cantidadDeseada > 0 && cantidadDeseada <= comprador.getCantidadBoletos()) {
                    if (cantidadDeseada > localidadSeleccionada.getCapacidad()) {
                        cantidadDeseada = localidadSeleccionada.getCapacidad();
                    }

                    double precioTotal = cantidadDeseada * localidadSeleccionada.getPrecio();

                    if (precioTotal <= comprador.getPresupuestoMaximo()) {
                        System.out.println("Precio total: $" + precioTotal);

                        localidadSeleccionada.venderBoletos(cantidadDeseada);
                        comprador.restarBoletos(cantidadDeseada);

                        System.out.println("¡Compra exitosa! Se han vendido " + cantidadDeseada + " boletos en la "
                                + localidadSeleccionada.getNombre() + ".");
                    } else {
                        System.out.println("El precio total excede tu presupuesto. No se puede realizar la compra.");
                    }
                } else {
                    System.out.println("Cantidad de boletos inválida. No se puede realizar la compra.");
                }
            } else {
                System.out.println("Lo sentimos, la localidad está agotada.");
            }
        } else {
            System.out.println("Lo sentimos, el número de ticket no es apto para comprar boletos.");
        }
    }

    public static void consultarDisponibilidadTotal(Localidad[] localidades) {
        System.out.println("\n--- Disponibilidad Total ---");
        for (Localidad localidad : localidades) {
            System.out.println(localidad.getNombre() + ": Boletos disponibles = " + localidad.getCapacidad());
        }
    }

    public static void consultarDisponibilidadIndividual(Localidad[] localidades) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Disponibilidad Individual ---");
        System.out.println("Selecciona una localidad:");

        for (int i = 0; i < localidades.length; i++) {
            System.out.println((i + 1) + ". " + localidades[i].getNombre());
        }

        int seleccion = scanner.nextInt();
        if (seleccion >= 1 && seleccion <= localidades.length) {
            Localidad localidadSeleccionada = localidades[seleccion - 1];
            System.out.println(localidadSeleccionada.getNombre() + ": Boletos disponibles = " + localidadSeleccionada.getCapacidad());
        } else {
            System.out.println("Selección inválida.");
        }
    }

    public static void reporteCaja(Localidad[] localidades) {
        double totalGenerado = 0;

        System.out.println("\n--- Reporte de Caja ---");
        for (Localidad localidad : localidades) {
            int boletosVendidos = localidad.getCapacidad() - 20;
            double ingresos = boletosVendidos * localidad.getPrecio();
            System.out.println(localidad.getNombre() + ": Boletos vendidos = " + boletosVendidos + ", Ingresos = $" + ingresos);
            totalGenerado += ingresos;
        }

        System.out.println("Total generado en ventas: $" + totalGenerado);
    }

    public static void ingresarCodigoEspecial(Comprador comprador, Localidad[] localidades) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Código Especial ---");
        System.out.print("Ingresa el código especial: ");
        int codigoEspecial = scanner.nextInt();

        if (NumeroFibonacci.esFibonacci(codigoEspecial)) {
            Localidad localidadEspecial = localidades[2]; // Localidad 10
            if (localidadEspecial.tieneEspacio(1)) {
                double precioTotal = 20000;

                System.out.println("Precio total (código especial): $" + precioTotal);
                localidadEspecial.venderBoletos(1);

                System.out.println("¡Compra exitosa! Se ha vendido 1 boleto en la Localidad 10 por $20000.");
            } else {
                System.out.println("Lo sentimos, la localidad especial está agotada.");
            }
        } else {
            System.out.println("Código especial inválido. No se puede realizar la compra.");
        }
    }
}

// prompt chat gpt: ayudame a cambiar los printnl para que se vea mas profesional