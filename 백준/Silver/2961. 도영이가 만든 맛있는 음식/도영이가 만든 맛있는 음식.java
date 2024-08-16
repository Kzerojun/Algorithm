import java.util.*;
import java.io.*;

public class Main {
	static List<List<Integer>> combinations;
	static int[][] costs;
	static int N;
	static BufferedReader br;
	static long result;

	public static void main(String[] args) throws IOException {
		init();
		makeCombination(0, new ArrayList<>());
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		costs = new int[N][2];
		result = Long.MAX_VALUE;
		combinations = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
		}
	}

	private static void makeCombination(int index, List<Integer> tmp) {
		combinations.add(new ArrayList<>(tmp));
		if (tmp.size() == N) {
			return;
		}

		for (int i = index; i < N; i++) {
			tmp.add(i);
			makeCombination(i + 1, tmp);
			tmp.remove(tmp.size() - 1);
		}
	}

	private static void start() {
		for (List<Integer> comb : combinations) {

			long sour = 1;
			long sum = 0;

			for (int number : comb) {
				sour = sour * costs[number][0];
				sum = sum + costs[number][1];
			}

			if(sour != 1 && sum != 0 ) {
				result = Math.min(result, Math.abs(sour - sum));
			}
		}
		
		System.out.println(result);
	}
}
