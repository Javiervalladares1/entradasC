public class NumeroFibonacci {

    public static boolean esFibonacci(int numero) {
        int a = 0, b = 1;
        while (b < numero) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b == numero;
    }
}
//promp chat gpt: como hacer una clase en java que de los numeros fibonacci