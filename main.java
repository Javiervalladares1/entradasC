import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la solicitud de compra de boletos.");
        
        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingresa tu email: ");
        String email = scanner.nextLine();
        
        System.out.print("Ingresa la cantidad de boletos a comprar: ");
        int cantidadBoletos = scanner.nextInt();
        
        System.out.print("Ingresa tu presupuesto máximo: ");
        double presupuestoMaximo = scanner.nextDouble();
      
        
        scanner.close();
    }
}