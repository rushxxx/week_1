import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Cycle {

    /**
     * Выводит на экран последовательность чисел Фибоначи
     * param elem - количество чисел, которые нужно вывести
     */

    public static void fibo (int elem){
        if (elem < 0) System.out.println("Fibonacci number cannot be negative");
        if (elem <=1) {
            System.out.println(elem + " elem  = " + elem);
        }else {

            String a1 = "0";
            String a2 = "1";
            long t = System.currentTimeMillis();
            System.out.println("1 elem  = 0");
            System.out.println("2 elem  = 1");

            for (int i = 3; i <= elem; i++) {
                String fibo = allSum(a1, a2);
                a1 = a2;
                a2 = fibo;
                System.out.println(i + " element length = " + fibo.length());
            }
            System.out.println("Time of runing the programm - " + (System.currentTimeMillis() - t) + " ms");
        }
    }

    /**
     * Возвращает сумму двух чисел, переданных строкой
     * params s1, s2 - числа в строковом формате
     * reformat - нет проверки, что все символы строки являются числами
     */
    public static String allSum(String s1, String s2){
        StringBuilder res = new StringBuilder();

        // добавляем нули в начале меньшей строки
        int s1Len = s1.length();
        int s2Len = s2.length();

        if (s1Len > s2Len){
            for (int i = s2Len ; i < s1Len; i++){
                s2 = "0".concat(s2);
            }
        }
        if (s2Len > s1Len){
            for (int i = s1Len ; i < s2Len; i++){
                s1 = "0".concat(s1);
            }
        }

        /**
         * Альтернативный способ добавление нуля в начало меньшей строки,
         * работает только с числами Фибаначи,
         * так как в последовательности, разрядность следующего числа
         * может быть больше только на один символ
         */
//        if (s1.length() > s2.length()) {
//            s2 = "0" + s2;
//        }else if (s2.length() > s1.length()) s1 = "0" + s1;


        int brain = 0; // в эту переменную будем складывать 1, если сумма чисел будет больше 9
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

    public static void main(String[] args) {
        fibo(100000);
    }

}
