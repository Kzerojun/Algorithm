import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int solution = 0;

		int[] playerA = new int[N];

		for (int i = 0; i < N; i++) {
			playerA[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			if (find(from, playerA) == find(to, playerA)) {
				bw.write((i+1) + "");
				solution++;
				break;
			}

			union(from, to, playerA);
		}

		if (solution == 0) {
			bw.write(0 + "");
		}

		bw.flush();
		bw.close();


	}

	static void union(int a, int b, int[] parent) {
		int x = find(a, parent);
		int y = find(b, parent);

		if (x == y) {
			return;
		}

		if (x < y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}

	static int find(int number, int[] parent) {
		if (parent[number] == number) {
			return number;
		}
		return parent[number] = find(parent[number], parent);
	}
}