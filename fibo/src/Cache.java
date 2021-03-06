import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cache {
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
     * Кэширует каждое cacheN вычисленное число
     * ! Первый раз 1000000 элементов просчитывает за 1200 секунд
     * ! Последующие запуски в среднем - 1с
     * refactor - нарушен первый принцип SOLID,
     * вынести операции с кэшем в отдельный метод
     * @param elem количество чисел, которые нужно вывести
     */
    public static String fibo (int elem){
        String a1 = "0";
        String a2 = "1";
        String res = "";
        int start = 3;
        int cacheN = 1000;
        int prevKey = 0;
        String prevVal = "";

        // если в кэше есть ближайший просчитанный элемент,
        // то начинаем считать от него
        if (cache.containsKey(elem - (elem % cacheN))){
            start = elem - (elem % cacheN) - 1;
            a1 = cache.get(elem - (elem % cacheN) - 1);
            a2 = cache.get(elem - (elem % cacheN));
        }

        for (int i = start; i <= elem; i++) {
            res = allSum(a1, a2);
            a1 = a2;
            a2 = res;
            // в кэш записываем каждый cacheN элемент
            if (i % cacheN == 0) {
                cache.put(prevKey, prevVal);
                cache.put(i, res);
            }
            prevKey = i;
            prevVal = res;
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
}
