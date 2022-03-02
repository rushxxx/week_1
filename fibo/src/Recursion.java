public class Recursion {
    public static long getFibonachiNumber(long n){
        if (n <= 1){
            return n;
        }else{
            return getFibonachiNumber(n - 1) + getFibonachiNumber(n - 2);
        }
    }

    public static void main(String[] args) {
        // amount of elements
        int elem = 42;
        for (int i = 0; i <= elem; i++) {
            long t = System.currentTimeMillis();
            long currentElem = getFibonachiNumber(i);
            System.out.print(i + " elem = " + currentElem + "  -  ");
            // time to calculate the current number
            System.out.println("( " + (System.currentTimeMillis() - t) + " ms )");
        }
    }
}
