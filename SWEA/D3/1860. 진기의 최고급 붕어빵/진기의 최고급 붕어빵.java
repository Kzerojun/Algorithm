import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int[] person = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                person[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(person);

            boolean check = false;
            for(int i = 0; i < N; i++) {
                if(person[i] / M * K < i + 1) {
                    check = true;
                    break;
                }
            }

            if(check) {
                System.out.printf("#%d Impossible", tc);
            } else {
                System.out.printf("#%d Possible", tc);
            }
            System.out.println();
        }
    }
}

