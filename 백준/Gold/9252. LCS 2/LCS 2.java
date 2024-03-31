import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String A = br.readLine();
		String B = br.readLine();

		int[][] dp = new int[A.length()+1][B.length()+1];

		for (int i = 1; i <= A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		System.out.println(dp[A.length()][B.length()]);
		back(dp,A.length(),B.length(),A);

	}

	static void back(int[][] dp, int i, int j,String string) {
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		while (i > 0 && j > 0) {
			if (dp[i][j] == dp[i - 1][j]) {
				i--;
			} else if (dp[i][j] == dp[i][j - 1]) {
				j--;
			} else {
				stack.push(string.charAt(i - 1));
				i--;
				j--;
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.toString());

	}
}