import java.io.*;
import java.util.*;

public class Before {
	static int N, M;
	static int[][] dp;
	static List<List<Node>> nodes;
	static String favoritePath;
	static String currentPath;
	static int maxResult = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nodes = new ArrayList<>();

		for(int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<>());
		}
		favoritePath = br.readLine();

		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);

			nodes.get(from).add(new Node(to, ch));
			nodes.get(to).add(new Node(from, ch));
		}

		// 각 경로마다 2차원 DP 배열 재사용
		dp = new int[M + 1][M + 1];
		treeFind(1, new StringBuilder(), -1);

		System.out.println(maxResult);
	}

	private static void treeFind(int index, StringBuilder sb, int parent) {
		if(nodes.get(index).size() == 1 && index != 1) {
			currentPath = sb.toString();
			initDP(); // DP 배열 초기화
			int result = LCS(currentPath.length() - 1, favoritePath.length() - 1);
			maxResult = Math.max(maxResult, result);
			return;
		}

		for(Node next : nodes.get(index)) {
			if(next.to != parent) {
				sb.append(next.ch);
				treeFind(next.to, sb, index);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	private static void initDP() {
		for(int i = 0; i <= M; i++) {
			Arrays.fill(dp[i], -1);
		}
	}

	private static int LCS(int aIndex, int bIndex) {
		if(aIndex < 0 || bIndex < 0) {
			return 0;
		}

		if(dp[aIndex][bIndex] != -1) {
			return dp[aIndex][bIndex];
		}

		if(currentPath.charAt(aIndex) == favoritePath.charAt(bIndex)) {
			return dp[aIndex][bIndex] = LCS(aIndex - 1, bIndex - 1) + 1;
		}

		return dp[aIndex][bIndex] = Math.max(LCS(aIndex - 1, bIndex), LCS(aIndex, bIndex - 1));
	}
}

class Node {
	int to;
	char ch;
	Node(int to, char ch) {
		this.to = to;
		this.ch = ch;
	}
}