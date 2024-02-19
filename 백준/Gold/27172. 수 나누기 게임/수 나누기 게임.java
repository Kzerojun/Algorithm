import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] player = new int[N];
		boolean[] visited = new boolean[1_000_001];
		result = new int[1_000_001];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			player[i] = Integer.parseInt(st.nextToken());
			visited[player[i]] = true;
		}

		for (int i : player) {
			for (int j = i * 2; j < 1_000_001; j += i) {
				if (visited[j]) {
					++result[i];
					--result[j];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		
		for (int num : player)
			sb.append(result[num]).append(' ');

		
		System.out.println(sb.toString());
	}


}
