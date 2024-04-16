import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main{

	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (command == 0) {
				union(a, b);
			}

			if (command == 1) {
				if (find(a) == find(b)) {
					bw.write("YES\n");
				}else {
					bw.write("NO\n");
				}
			}

		}
		bw.flush();
		bw.close();
		br.close();
	}

	static void union(int a, int b) {
		int x = find(a);
		int y = find(b);

		if (x == y) {
			return;
		}

		if (x < y) {
			parent[y] = x;
		}else {
			parent[x] = y;
		}
	}

	static int find(int number) {
		if (parent[number] == number) {
			return number;
		}
		return parent[number] = find(parent[number]);
	}
}
