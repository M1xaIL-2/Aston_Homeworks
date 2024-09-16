import java.util.Scanner;

public class FactorialNG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число для поиска его факториала");
        int chislo = scanner.nextInt();
        long fact = number(chislo);
        System.out.println("Факториал числа " + chislo + ": " + fact);
    }

    public static long number(int b) {
        long a = 1;
        for (int i = 1; i <= b; i++) {
            a = a * i;
        }
        System.out.println(a);
        return a;
    }

}
