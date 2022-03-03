import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileCache {
    private static Map<Integer, String> cache = new HashMap<>();

    public static void main(String[] args) {



        while (true) {
            String res = "";
            System.out.print("Введите номер элемента Фибоначи : ");
            Scanner sc = new Scanner(System.in);
            int elem = sc.nextInt();

            if (elem > 1) {
                long t = System.currentTimeMillis();

                res = cache.containsKey(elem) ? cache.get(elem) : fibo(elem);

                System.out.println(elem + " элемент = " + res);
                System.out.println("Time of runing the programm - " + (System.currentTimeMillis() - t) + " ms \n\n");
            } else {
                if (elem == 0 || elem == 1) System.out.println(elem + " elem  = " + elem);
                if (elem < 0) System.out.println("Fibonacci number cannot be negative");
            }
        }
    }

    /**
     * Возвращает число Фибоначи
     * Кэширует все вычисленные числа Фибоначи
     * ! При использовании кэша в памяти, куча заканчивается примерно на 140000-м элементе
     * @param elem количество чисел, которые нужно вывести
     */
    public static String fibo (int elem){
        String a1 = "0";
        String a2 = "1";
        String res = "";

        for (int i = 3; i <= elem; i++) {
            res = allSum(a1, a2);
            a1 = a2;
            a2 = res;
            cache.put(i, res);
        }

        return res;
    }

    /**
     * Возвращает сумму двух чисел, переданных строкой
     * params s1, s2 - числа в строковом формате
     * reformat - нет проверки, что все символы строки являются числами
     */
    public static String allSum(String s1, String s2){
        StringBuilder res = new StringBuilder();

        if (s1.length() > s2.length()) {
            s2 = "0" + s2;
        }else if (s2.length() > s1.length()) s1 = "0" + s1;


        int brain = 0;
        int numA;
        int numB;
        int c;
        for (int i = s1.length() - 1; i >= 0; i--) {
            // забираем элементы строки с конца
            numA = Character.getNumericValue(s1.charAt(i));
            numB = Character.getNumericValue(s2.charAt(i));

            // складываем два числа и возможно 1
            c = numA + numB + brain;

            // добавляем сумму в результат
            res.append(c % 10);
            brain = c / 10;

        }
        if (brain == 1){
            res.append(1);
        }
        res.reverse();
        return res.toString();
    }
}
