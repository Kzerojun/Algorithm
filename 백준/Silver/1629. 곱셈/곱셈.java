import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long result = pow(A, B, C);
        System.out.println(result);
    }

    static long pow(long a, long b, int c) {
        if (b == 0) {
            return 1;
        }

        long res = pow(a, b / 2, c);

        if (b % 2 == 0) {
            return (res * res) % c;
        } else {
            return ((res * res) % c * a) % c;
        }
    }
}
