import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Main {

	static Map<Integer, Integer> startIndex;
	static Map<Integer, Integer> endIndex;
	static int N;
	static Switch[] switches;
	static int[] dp;
	static int[] tracking;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		startIndex = new HashMap<>();
		endIndex = new HashMap<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			startIndex.put(number, i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			endIndex.put(number, i);
		}

		switches = new Switch[N];
		tracking = new int[N];

		int index = 0;
		for (Integer key : startIndex.keySet()) {
			int start = startIndex.get(key);
			int end = endIndex.get(key);

			switches[index++] = new Switch(start, end, key);
		}

		dp = new int[N];
		Arrays.fill(dp, -1);
		Arrays.fill(tracking, -1);
		Arrays.sort(switches, (o1, o2) -> o1.start - o2.start);

		for (int i = 0; i < N; i++) {
			if (dp[i] == -1) {
				dfs(i);
			}
		}

		int maxCount = -1;
		int switchIndex = -1;
		for (int i = 0; i < N; i++) {
			if (dp[i] > maxCount) {
				maxCount = dp[i];
				switchIndex = i;
			}
		}

		System.out.println(maxCount);

		List<Integer> results = new ArrayList<>();
		while (switchIndex != -1) {
			results.add(switches[switchIndex].number);
			switchIndex = tracking[switchIndex];
		}

		Collections.sort(results);

		for (int number : results) {
			System.out.print(number+" ");
		}



	}


	private static int dfs(int index) {
		if (dp[index] != -1) {
			return dp[index];
		}

		dp[index] = 1;
		for (int next = index + 1; next < N; next++) {
			if (switches[next].end > switches[index].end) {
				int result = dfs(next);
				if (result + 1 > dp[index]) {
					tracking[index] = next;
					dp[index] = result + 1;
				}
			}
		}

		return dp[index];
	}
}


class Switch {

	int start;
	int end;
	int number;

	Switch(int start, int end,int number) {
		this.start = start;
		this.end = end;
		this.number = number;
	}
}

