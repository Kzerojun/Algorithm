import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int[][] dp;
    static String A, B;
    static int maxLength = 0;

    public static void main(String[] args) throws IOException {
        simulate();
    }

    private static void simulate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();

        int lenA = A.length();
        int lenB = B.length();

        dp = new int[lenA + 1][lenB + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                maxLength = Math.max(maxLength, dp(i, j));
            }
        }

        System.out.println(maxLength);
    }

    private static int dp(int aIndex, int bIndex) {
        if (aIndex == 0 || bIndex == 0) {
            return 0;
        }

        if (dp[aIndex][bIndex] != 0) {
            return dp[aIndex][bIndex];
        }

        char aCh = A.charAt(aIndex - 1);
        char bCh = B.charAt(bIndex - 1);

        if (aCh == bCh) {
            dp[aIndex][bIndex] = dp(aIndex - 1, bIndex - 1) + 1;
            maxLength = Math.max(maxLength, dp[aIndex][bIndex]);
        } else {
            dp[aIndex][bIndex] = 0;
        }

        return dp[aIndex][bIndex];
    }
}
