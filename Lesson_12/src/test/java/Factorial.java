import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число для поиска его факториала");
        int chislo = scanner.nextInt();

        try {
            long fact = number(chislo);
            System.out.println("Факториал числа " + chislo + ": " + fact);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static long number(int b) {
        if (b < 0) {
            throw new IllegalArgumentException("Факториал высчитывается только для неотрицательных целых чисел.");
        }

        long a = 1;
        for (int i = 1; i <= b; i++) {
            a = a * i;
        }
        return a;
    }
}
