import java.util.*;
import java.io.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        init();
        simulate();
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    public static void simulate() {
        if (n == 1) {
            System.out.println(2);
            return;
        }
        for (int i = n; ; i++) {
            if (isPalind(i) && isPrime(i)) {
                System.out.println(i);
                return;
            }
        }
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalind(int x) {
        String strX = Integer.toString(x);
        int cnt = strX.length();
        for (int i = 0; i <= cnt / 2; i++) {
            if (strX.charAt(i) != strX.charAt(cnt - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
