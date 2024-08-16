import java.util.*;
import java.io.*;

class Solution {
	static BufferedReader br;
	static int T;
	static int N, W, H;
	static int[][] graph;
	static List<List<Integer>> pers;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int sol;
	static int[][] tmpGraph;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void start() throws IOException {
		for (int t = 1; t <= T; t++) {
			initGame();
			permutation(new ArrayList<>());
			playGame();
			System.out.println("#" + t + " " + sol);
		}
	}

	private static void initGame() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		graph = new int[H][W];
		pers = new ArrayList<>();
		sol = Integer.MAX_VALUE;

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void permutation(List<Integer> per) {
		if (per.size() == N) {
			pers.add(new ArrayList<>(per));
			return;
		}

		for (int i = 0; i < W; i++) {
			per.add(i);
			permutation(per);
			per.remove(per.size() - 1);
		}
	}

	private static void playGame() {
		for (List<Integer> per : pers) {
			tmpGraph = new int[H][W];
			copyGraph(tmpGraph);

			for (int h : per) {
				dropBall(h);
				
			}
			
			int result = count();
			sol = Math.min(result, sol);
		}
	}

	private static void dropBall(int h) {
		Queue<Ball> q = new LinkedList<>();

		int nx = -1;
		// 위에 공 떨구기
		while (true) {
			nx = nx + dx[1];

			if (nx >= H) {
				break;
			}

			if (tmpGraph[nx][h] > 0) {
				q.add(new Ball(nx, h, tmpGraph[nx][h]));
				break;
			}
		}

		spread(q);
	}

	private static void spread(Queue<Ball> q) {
		boolean[][] visited = new boolean[H][W];
		
		while (!q.isEmpty()) {
			Ball now = q.poll();
			tmpGraph[now.x][now.y] = 0;
			visited[now.x][now.y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = now.x;
				int ny = now.y;
				int moveCount = now.move;
				while (moveCount-- > 1) {
					nx = nx + dx[i];
					ny = ny + dy[i];

					if (nx < 0 || ny < 0 || nx >= H || ny >= W || visited[nx][ny]) {
						continue;
					}

					if (tmpGraph[nx][ny] >= 1) {
						visited[nx][ny] = true;
						q.add(new Ball(nx, ny, tmpGraph[nx][ny]));
					}
				}
			}
		}
		
		dropGraph();
		
	}

	private static void copyGraph(int[][] tmp) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = graph[i][j];
			}
		}
	}
	
	private static void dropGraph() {
		for(int j = 0 ;j<W; j++) {
			for(int i = H-1; i>0; i--) {
				if(tmpGraph[i][j]==0) {
					int x = i;
					while(true) {
						x--;
						if(tmpGraph[x][j]!= 0) {
							tmpGraph[i][j] = tmpGraph[x][j];
							tmpGraph[x][j] = 0;
							break;
						}
						
						if(x<=0) {
							break;
						}
					}
				}
			}
		}
	}

	private static int count() {
		int result = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (tmpGraph[i][j] > 0) {
					result++;
				}
			}
		}
		return result;
	}

}

class Ball {
	int x;
	int y;
	int move;

	Ball(int x, int y, int move) {
		this.x = x;
		this.y = y;
		this.move = move;
	}
}