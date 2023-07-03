
import java.util.*;
import java.io.*;

public class Main {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println((sol(n)));
    }
    public static int sol(int n ){
        int[] dp = new int[n + 1];

        // 초기화
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 최대값으로 초기화
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

}