import java.util.Scanner;

public class SistemaBoletos {

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

        System.out.println("\n--- Nueva Solicitud de Boletos ---");
        int ticket = TicketManager.generarNumeroAleatorio(1, 15000);
        int a = TicketManager.generarNumeroAleatorio(1, 15000);
        int b = TicketManager.generarNumeroAleatorio(1, 15000);

        

        if (ticket >= Math.min(a, b) && ticket <= Math.max(a, b)) {
            System.out.println("El ticket es apto para comprar boletos.");

            int localidadIndex = TicketManager.generarNumeroAleatorio(0, 2);
            Localidad localidadSeleccionada = localidades[localidadIndex];

            System.out.println("Localidad seleccionada: " + localidadSeleccionada.getNombre());

            if (localidadSeleccionada.tieneEspacio(comprador.getCantidadBoletos())) {
                if (comprador.getPresupuestoMaximo() >= localidadSeleccionada.getPrecio() * comprador.getCantidadBoletos()) {
                    double precioTotal = localidadSeleccionada.getPrecio() * comprador.getCantidadBoletos();

                    System.out.println("Precio total: $" + precioTotal);

                    if (NumeroFibonacci.esFibonacci(ticket)) {
                        if (localidadSeleccionada.getNombre().equals("Localidad 10")) {
                            int entradasDisponibles = Math.min(localidadSeleccionada.getCapacidad(), comprador.getCantidadBoletos());

                            System.out.println("Cantidad de boletos disponibles: " + entradasDisponibles);
                            double costoTotal = entradasDisponibles * localidadSeleccionada.getPrecio();
                            System.out.println("Costo total: $" + costoTotal);

                            localidadSeleccionada.venderBoletos(entradasDisponibles);
                        } else {
                            System.out.println("Lo siento, la compra especial solo es válida en la Localidad 10.");
                        }
                    } else {
                        int entradasDisponibles = Math.min(localidadSeleccionada.getCapacidad(), comprador.getCantidadBoletos());

                        System.out.println("Cantidad de boletos disponibles: " + entradasDisponibles);
                        double costoTotal = entradasDisponibles * localidadSeleccionada.getPrecio();
                        System.out.println("Costo total: $" + costoTotal);

                        localidadSeleccionada.venderBoletos(entradasDisponibles);
                    }
                } else {
                    System.out.println("El precio total excede tu presupuesto. No se puede realizar la compra.");
                }
            } else {
                System.out.println("Lo sentimos, la localidad está agotada o no tiene suficiente espacio.");
            }
        } else {
            System.out.println("El ticket no es apto para comprar boletos.");
        }
    }

    public static void consultarDisponibilidadTotal(Localidad[] localidades) {
        System.out.println("\n--- Consultar Disponibilidad Total ---");
        for (Localidad localidad : localidades) {
            System.out.println("Localidad: " + localidad.getNombre());
            System.out.println("Boletos vendidos: " + localidad.getBoletosVendidos());
            System.out.println("Boletos disponibles: " + localidad.getCapacidad());
        }
    }

    public static void consultarDisponibilidadIndividual(Localidad[] localidades) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Consultar Disponibilidad Individual ---");
        System.out.print("Ingresa el nombre de la localidad: ");
        String nombreLocalidad = scanner.nextLine();

        boolean encontrada = false;
        for (Localidad localidad : localidades) {
            if (localidad.getNombre().equalsIgnoreCase(nombreLocalidad)) {
                System.out.println("Localidad: " + localidad.getNombre());
                System.out.println("Boletos vendidos: " + localidad.getBoletosVendidos());
                System.out.println("Boletos disponibles: " + localidad.getCapacidad());
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            System.out.println("Localidad no encontrada.");
        }
    }

    public static void reporteCaja(Localidad[] localidades) {
        System.out.println("\n--- Reporte de Caja ---");
        double totalGenerado = 0;

        for (Localidad localidad : localidades) {
            double ingresos = localidad.getBoletosVendidos() * localidad.getPrecio();
            System.out.println("Localidad: " + localidad.getNombre());
            System.out.println("Ingresos generados: $" + ingresos);
            totalGenerado += ingresos;
        }

        System.out.println("Total generado: $" + totalGenerado);
    }

    public static void ingresarCodigoEspecial(Comprador comprador, Localidad[] localidades) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Código Especial ---");
        System.out.print("Ingresa el código especial: ");
        int codigoEspecial = scanner.nextInt();

        if (NumeroFibonacci.esFibonacci(codigoEspecial)) {
            Localidad localidadEspecial = localidades[2]; // Localidad 10
            if (localidadEspecial.tieneEspacio(1)) {
                double precioTotal = localidadEspecial.getPrecio();

                System.out.println("Precio total (código especial): $" + precioTotal);
                comprador.venderBoletos(1);

                System.out.println("¡Compra exitosa! Se ha vendido 1 boleto en la Localidad 10 por $20000.");
            } else {
                System.out.println("Lo sentimos, la localidad especial está agotada.");
            }
        } else {
            System.out.println("Código especial inválido. No se puede realizar la compra.");
        }
    }
    public static void ingresarCodigoEspecial(Comprador comprador, Localidad[] localidades) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Código Especial ---");
        System.out.print("Ingresa el código especial: ");
        int codigoEspecial = scanner.nextInt();

        if (NumeroFibonacci.esFibonacci(codigoEspecial)) {
            Localidad localidadEspecial = localidades[2]; // Localidad 10
            if (localidadEspecial.tieneEspacio(1)) {
                double precioTotal = localidadEspecial.getPrecio();

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