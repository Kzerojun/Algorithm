import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static int N;
	static List<List<Integer>> graph;
	static int[] peoples;
	static List<List<Integer>> combinations;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		peoples = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());

			for (int j = 0; j < count; j++) {
				int node = Integer.parseInt(st.nextToken()) - 1;
				graph.get(i).add(node);
				graph.get(node).add(i);
			}
		}

		combinations = new ArrayList<>();
	}

	// 0 이면 빨강 1이면 파랑
	private static void makeCombination(List<Integer> tmp, int index) {
		if (tmp.size() == N) {
			combinations.add(new ArrayList<>(tmp));
			return;
		}

		for (int i = index; i < N; i++) {
			for (int j = 0; j <= 1; j++) {
				tmp.add(j);
				makeCombination(tmp, i + 1);
				tmp.remove(tmp.size() - 1);
			}
		}
	}

	private static void start() {
		makeCombination(new ArrayList<>(), 0);

		for (List<Integer> comb : combinations) {
			boolean check = check(comb);

			if (check) {
				int bluePeopleCount = 0;
				int redPeopleCount = 0;

				for (int i = 0; i < N; i++) {
					if (comb.get(i) == 0) {
						redPeopleCount += peoples[i];
					}
					if (comb.get(i) == 1) {
						bluePeopleCount += peoples[i];
					}
				}

				result = Math.min(result, Math.abs(bluePeopleCount - redPeopleCount));
			}
		}

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static boolean check(List<Integer> comb) {
		List<Integer> red = new ArrayList<>();
		List<Integer> blue = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (comb.get(i) == 0) {
				red.add(i);
			}
			if (comb.get(i) == 1) {
				blue.add(i);
			}
		}

		if (!isConnect(red, new boolean[N])) {
			return false;
		}

		if (!isConnect(blue, new boolean[N])) {
			return false;
		}

		return true;
	}

	private static boolean isConnect(List<Integer> group, boolean[] visited) {
		if (group.isEmpty()) return false;

		Queue<Integer> q = new LinkedList<>();
		q.add(group.get(0));
		visited[group.get(0)] = true;
		int count = 1;

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int next : graph.get(now)) {
				if (!visited[next] && group.contains(next)) {
					visited[next] = true;
					q.add(next);
					count++;
				}
			}
		}

		return count == group.size();
	}
}
