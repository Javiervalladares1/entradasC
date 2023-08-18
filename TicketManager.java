import java.util.Random;

public class TicketManager {
    private static Random random = new Random();

    public static int generarNumeroAleatorio(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
