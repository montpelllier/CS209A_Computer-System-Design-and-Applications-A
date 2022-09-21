package practice.lab3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Practice3Answer {
    static String functionNo = "Please input the function No:\n" +
            "1 - Get even numbers\n" +
            "2 - Get odd numbers\n" +
            "3 - Get prime numbers\n" +
            "4 - Get prime numbers that are bigger than 5\n" +
            "0 - Quit\n";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean terminal = false;
        Predicate<Integer> integerPredicate = null;
        ArrayList<Integer> arrayList;
        while (true) {
            System.out.println(functionNo);
            switch (scanner.nextInt()) {
                case 1:
                    integerPredicate = i -> i % 2 == 0;
                    break;
                case 2:
                    integerPredicate = i -> i % 2 == 1;
                    break;
                case 3:
                    integerPredicate = i -> isPrime(i);
                    break;
                case 4:
                    integerPredicate = i -> isPrime(i);
                    integerPredicate = integerPredicate.and(i -> i > 5);
                    break;
                default:
                    terminal = true;
            }
            if (terminal) break;

            System.out.println("Input size of the list:\n");
            int length = scanner.nextInt();
            arrayList = new ArrayList<>(length);

            System.out.println("Input elements of the list:\n");
            while (length-- > 0) {
                arrayList.add(scanner.nextInt());
            }

            System.out.println("Filter results:\n" + arrayList.stream().filter(integerPredicate).toList());
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
