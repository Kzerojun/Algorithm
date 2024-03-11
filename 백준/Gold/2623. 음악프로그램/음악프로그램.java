import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
	static int N;
	static int[] entry;
	static List<Integer>[] nodes;

	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());


		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N+1]; //사이클 확인용
		nodes = new ArrayList[N+1];
		entry = new int[N+1];

		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());

			int before = -1;

			for (int j = 0; j < count; j++) {
				if(j==0) {
					before = Integer.parseInt(st.nextToken());
					continue;
				}

				int number = Integer.parseInt(st.nextToken());
				nodes[before].add(number);
				entry[number]++;
				before=number;
			}
		}

		calculate();

	}

	static void calculate() throws IOException {
		List<Integer> result = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			if(entry[i]==0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();
			result.add(now);

			for(int next : nodes[now]) {
				entry[next]--;
				if(entry[next]==0) {
					q.add(next);
				}
			}
		}

		if (result.size() != N) {
			System.out.println(0);
		} else {
			for (int x : result) {
				System.out.println(x);
			}
		}
	}

}

class Node {
	int number;
	int entry;

	Node(int number, int entry) {
		this.number = number;
		this.entry = entry;
	}
}