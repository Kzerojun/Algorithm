import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] costs;
	static int max;

	public static void main(String[] args) throws Exception {
		init();
		int result = simulate();
		printResult(result);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		costs = new int[N];

		for (int i = 0; i < N; i++) {
			costs[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, costs[i]);
		}
	}

	private static int simulate() {
		int left = max;
		int right = 1_000_000_000;

		while (left < right) {
			int mid = (left + right) / 2;

			if (isCan(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}

	private static boolean isCan(int cost) {
		int count = 1;
		int wallet = cost;

		for (int i = 0; i < N; i++) {
			if (wallet - costs[i] < 0) {
				count++;
				wallet = cost;
			}
			wallet = wallet - costs[i];
		}

		return count <= M;
	}


	private static void printResult(int result) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(result + " ");
		bw.flush();
		bw.close();
	}

}
