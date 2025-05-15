import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static Item[] items;
	static int[][] dp;


	public static void main(String[] args) throws Exception {
		init();
		int result = simulate();
		printResult(result);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		items = new Item[N];
		dp = new int[100_001][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			items[i] = new Item(weight, value);
		}

		for (int i = 0; i <= 100_000; i++) {
			Arrays.fill(dp[i], -1);
		}
	}

	private static int simulate() {
		int result = dp(0, 0);
		return result;
	}

	private static int dp(int weight, int index) {
		if (index >= N) {
			return 0;
		}

		if (dp[weight][index] != -1) {
			return dp[weight][index];
		}

		dp[weight][index] = 0;

		if (weight + items[index].weight <= K) {
			int result1 = dp(weight + items[index].weight, index + 1);
			dp[weight][index] = Math.max(result1 + items[index].value, dp[weight][index]);
		}

		int result2 = dp(weight, index + 1);
		dp[weight][index] = Math.max(result2, dp[weight][index]);

		return dp[weight][index];
	}


	private static void printResult(int result) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(result + " ");
		bw.flush();
		bw.close();
	}

}

class Item {

	int weight;
	int value;

	Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
}
