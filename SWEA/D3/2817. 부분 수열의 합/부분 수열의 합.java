import java.util.*;
import java.io.*;

public class Solution {
    static int N, K;
    static int[] A;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; ++t) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            A = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            count = 0;
            findSum(0, 0);
            System.out.println("#" + t + " " + count);
        }
        br.close();
    }

    static void findSum(int index, int sum) {
        if (sum == K) {
            count++;
            return;
        }
        if (index == N) {
            return;
        }

        findSum(index + 1, sum + A[index]);
        findSum(index + 1, sum);
    }
}
