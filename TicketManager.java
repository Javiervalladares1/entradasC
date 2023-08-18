import java.util.Random;

public class TicketManager {

    public static int generarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static boolean esTicketApto(int ticket) {
        int a = generarNumeroAleatorio(1, 15000);
        int b = generarNumeroAleatorio(1, 15000);

        int minValor = Math.min(a, b);
        int maxValor = Math.max(a, b);

        return ticket >= minValor && ticket <= maxValor;
    }
}
