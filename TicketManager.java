import java.util.Random;

public class TicketManager {

    public static int generarNumeroAleatorio(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static boolean esTicketApto(int ticket) {
        int a = generarNumeroAleatorio(1, 15000);
        int b = generarNumeroAleatorio(1, 15000);
        return (a <= ticket && ticket <= b) || (b <= ticket && ticket <= a);
    }
}
