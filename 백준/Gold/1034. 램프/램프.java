import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, M, K;
    private static int[] offCount;
    private static String[] line;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(simulate());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        offCount = new int[N];
        line = new String[N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int zero = 0;
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '0') {
                    zero++;
                }
            }
            offCount[i] = zero;
            line[i] = s;
        }

        K = Integer.parseInt(br.readLine());
    }

    private static int simulate() {
        int max = 0;

        for (int i = 0; i < N; i++) {
            if (offCount[i] <= K && (K - offCount[i]) % 2 == 0) {
                int equalCount = 1;
                for (int j = 0; j < N; j++) {
                    if (i != j && line[i].equals(line[j])) {
                        equalCount++;
                    }
                }
                max = Math.max(max, equalCount);
            }
        }

        return max;
    }
}
