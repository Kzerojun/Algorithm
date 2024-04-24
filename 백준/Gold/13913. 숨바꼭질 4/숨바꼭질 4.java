import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int N, K;
	static boolean[] visited = new boolean[100_001];
	static int[] parent = new int[100_001]; // 각 위치의 부모 위치를 저장할 배열

	public static void main(String[] args) throws IOException {
		init();
		move(N);
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	}

	static void move(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		parent[start] = -1; // 시작점의 부모는 없음을 표시

		while (!q.isEmpty()) {
			int current = q.poll();

			if (current == K) {
				System.out.println(getTime(K));
				printPath(K);
				return;
			}

			int[] nextPositions = {current + 1, current - 1, current * 2};
			for (int next : nextPositions) {
				if (next >= 0 && next <= 100_000 && !visited[next]) {
					q.add(next);
					visited[next] = true;
					parent[next] = current;
				}
			}
		}
	}

	static int getTime(int end) {
		int time = 0;
		while (parent[end] != -1) {
			end = parent[end];
			time++;
		}
		return time;
	}

	static void printPath(int end) {
		LinkedList<Integer> path = new LinkedList<>();
		for (int at = end; at != -1; at = parent[at]) {
			path.addFirst(at);
		}
		for (int pos : path) {
			System.out.print(pos + " ");
		}
		System.out.println();
	}
}
