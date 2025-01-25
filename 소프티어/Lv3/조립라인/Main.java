import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static int[] B;
    static int[][] moveCost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        moveCost = new int[N][2];
        dp = new int[N][2];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            moveCost[i][0] = Integer.parseInt(st.nextToken());
            moveCost[i][1] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        A[N - 1] = Integer.parseInt(st.nextToken());
        B[N - 1] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

      	// A의 0번에서 시작, B의 0번에서 시작
        int aStart = calculateDp(0, 0);
        int bStart = calculateDp(0, 1);

        System.out.println(Math.min(aStart, bStart));
    }

    static int calculateDp(int index, int workSpace) {
        if (index == N) {
            return 0;
        }

        if (dp[index][workSpace] != -1) {
            return dp[index][workSpace];
        }

        dp[index][workSpace] = (workSpace == 0) ? A[index] : B[index];

        // 기존의 작업장
        int stayCost = calculateDp(index + 1, workSpace);

        // 다른 작업장으로 이동
        int nextWorkSpace = (workSpace + 1) % 2;
        int switchCost = calculateDp(index + 1, nextWorkSpace) + moveCost[index][workSpace];

        // 최소 비용 선택
        dp[index][workSpace] += Math.min(stayCost, switchCost);

        return dp[index][workSpace];
    }
}
