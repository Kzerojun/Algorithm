import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	static int N;
	static int[] arr;

	static int[] dp;

	static int[] dp2;

	static int solution = -1;

	public static void main(String[] args) throws IOException {
		init();
		calculateIncrease();
		calculateDecrease();


		for (int i = 0; i < N; i++) {
			solution = Math.max(solution, dp[i] + dp2[i]-1);
		}
		System.out.println(solution);

	}

	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N];
		dp2 = new int[N];
	}

	public static void calculateIncrease() {
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
	}

	public static void calculateDecrease() {
		for (int i = N-1; i >= 0; i--) {
			dp2[i] = 1;
			for (int j = N - 1; j > i; j--) {
				if (arr[i] > arr[j]) {
					dp2[i] = Math.max(dp2[i], dp2[j] + 1);
				}
			}
		}
	}
}