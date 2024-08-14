import java.io.BufferedReader;
import java.util.*;
import java.io.*;

public class Solution {

	static BufferedReader br;
	static int T;
	static int N, M;
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		init();
		play();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void play() throws IOException {
		for (int i = 1; i <= T; i++) {
			initGraph();
			int result = calculate();
			System.out.println("#" + i + " " + result);
		}

	}

	private static void initGraph() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < N; j++) {
				graph[i][j + 1] = graph[i][j + 1] + graph[i][j];
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < N; j++) {
				graph[j + 1][i] = graph[j + 1][i] + graph[j][i];
			}
		}

	}

	private static int calculate() {
		int maxResult = Integer.MIN_VALUE;

		for (int i = M; i <= N; i++) {
			for (int j = M; j <= N; j++) {
				int gridSum = graph[i][j] - graph[i - M][j] - graph[i][j - M] + graph[i - M][j - M];

				maxResult = Math.max(gridSum, maxResult);
			}
		}
		return maxResult;
	}
}
