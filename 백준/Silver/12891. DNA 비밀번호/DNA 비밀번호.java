import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static int S, P;
    static String password;
    static int[] correct;

    public static void main(String[] args) throws IOException {
        init();
        start();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        password = br.readLine();
        correct = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            correct[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void start() {
        int result = 0;

        int A = 0;
        int C = 0;
        int G = 0;
        int T = 0;

        int left = 0;
        int right = P - 1;

        for (int j = 0; j <= right; j++) {
            char ch = password.charAt(j);

            if (ch == 'A') {
                A++;
            }
            if (ch == 'C') {
                C++;
            }
            if (ch == 'G') {
                G++;
            }
            if (ch == 'T') {
                T++;
            }
        }
        if (correct[0] <= A && correct[1] <= C && correct[2] <= G && correct[3] <= T) {
            result++;
        }

        while (right < S - 1) {
            char ch = password.charAt(left++);
            if (ch == 'A') {
                A--;
            }
            if (ch == 'C') {
                C--;
            }
            if (ch == 'G') {
                G--;
            }
            if (ch == 'T') {
                T--;
            }

            right++;  // 이 부분에서 right를 하나만 증가시키면 됨.

            ch = password.charAt(right);
            if (ch == 'A') {
                A++;
            }
            if (ch == 'C') {
                C++;
            }
            if (ch == 'G') {
                G++;
            }
            if (ch == 'T') {
                T++;
            }

            if (correct[0] <= A && correct[1] <= C && correct[2] <= G && correct[3] <= T) {
                result++;
            }
        }

        System.out.println(result);
    }
}
