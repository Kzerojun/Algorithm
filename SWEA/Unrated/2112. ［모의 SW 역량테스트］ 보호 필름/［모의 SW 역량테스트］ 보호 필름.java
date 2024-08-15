import java.util.*;
import java.io.*;

class Solution {

	static BufferedReader br;
	static int T;
	static int D, W, K;
	static int[][] films;
	static List<List<Integer>> combinations;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void start() throws IOException {
		for (int testcase = 1; testcase <= T; testcase++) {
			initGame();
			int result = play();
			System.out.println("#" + testcase + " " + result);
		}
	}

	private static void initGame() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		films = new int[D][W];

		for (int i = 0; i < D; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				films[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int play() {
		for (int medicine = 0; medicine < D; medicine++) {
			combinations = new ArrayList<>();
			makeCombination(medicine, 0, new ArrayList<>());

			for (List<Integer> comb : combinations) {
				if (permutation(comb, new ArrayList<>())) {
					return medicine;
				}
			}
		}

		return -1;

	}

	private static void copyGraph(int[][] tmp) {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = films[i][j];
			}
		}
	}

	private static void makeCombination(int medicine, int index, List<Integer> tmp) {
		if (tmp.size() == medicine) {
			combinations.add(new ArrayList<>(tmp));
			return;
		}

		for (int i = index; i < D; i++) {
			tmp.add(i);
			makeCombination(medicine, i + 1, tmp);
			tmp.remove(tmp.size() - 1);
		}
	}


	private static boolean permutation(List<Integer> comb, List<Integer> tmp) {
		if (tmp.size() == comb.size()) {
			int[][] tmpGraph = new int[D][W];
			copyGraph(tmpGraph);
			for (int i = 0; i < comb.size(); i++) {
				Arrays.fill(tmpGraph[comb.get(i)], tmp.get(i));
			}
			return check(tmpGraph);
		}

		for (int color = 0; color <= 1; color++) {
			tmp.add(color);
			if (permutation(comb, tmp)) {
				return true;
			}
			tmp.remove(tmp.size() - 1);

		}

		return false;
	}

	private static boolean check(int[][] tmp) {
		for (int j = 0; j < W; j++) {
			boolean passed = false;
			for (int i = 0; i <= D - K; i++) {
				if (test(tmp[i][j], tmp, i, j)) {
					passed = true;
					break;
				}
			}
			if (!passed) {
				return false;
			}
		}
		return true;
	}

	private static boolean test(int number, int[][] tmp, int x, int y) {
		for (int i = x; i < x + K; i++) {
			if (number != tmp[i][y]) {
				return false;
			}
		}
		return true;
	}


}




