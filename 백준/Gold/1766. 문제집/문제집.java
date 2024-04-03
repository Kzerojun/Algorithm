import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static int[] priority;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		priority = new int[N + 1];


		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list[A].add(B);
			priority[B]++;
		}
		calculate();


	}

	static void calculate() {
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->{
			return o1-o2;
		});

		List<Integer> result = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (priority[i] == 0) {
				pq.offer(i);
			}
		}

		while (!pq.isEmpty()) {
			int now = pq.poll();
			result.add(now);
			for (int next : list[now]) {
				priority[next]--;

				if (priority[next] == 0) {
					pq.offer(next);
				}
			}
		}

		for (int i : result) {
			System.out.print(i+" ");
		}
	}
}