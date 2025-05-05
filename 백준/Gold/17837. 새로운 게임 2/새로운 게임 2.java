
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	static int N, K;
	static int turn;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	static int[][] graph;
	static Player[] players;
	static Map<String, Stack<Player>> maps;
	static boolean gameEnd;

	public static void main(String[] args) throws IOException {
		init();
		simulate();
		printResult();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		players = new Player[K];
		maps = new HashMap<>();

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken())-1;
			int col = Integer.parseInt(st.nextToken())-1;
			int direction = Integer.parseInt(st.nextToken()) - 1;

			players[i] = new Player(row, col, direction);

			String nowLocation = row + " " + col;
			Stack<Player> nst = maps.getOrDefault(nowLocation,
					new Stack<>());
			nst.add(players[i]);
			maps.put(nowLocation, nst);
		}


		gameEnd = false;
		turn = 0;
	}

	private static void simulate() {
		while (true) {
			turn++;
			movePlayers();
			if (turn >= 1000) {
				break;
			}

			if (gameEnd) {
				break;
			}
		}
	}

	public static boolean isBlue(int x, int y) {
		return x < 0 || y < 0 || x >= N || y >= N || graph[x][y] == 2;
	}

	private static void movePlayers() {
		for (Player player : players) {
			int nx = player.row + dx[player.direction];
			int ny = player.col + dy[player.direction];
			String nowKey = player.row + " " + player.col;

			// 다음 칸이 블루일때
			if (isBlue(nx, ny)) {
				if (player.direction % 2 == 0) {
					player.direction++;
				} else {
					player.direction--;
				}

				nx = player.row + dx[player.direction];
				ny = player.col + dy[player.direction];

				// 다다음칸이 블루일때
				if (isBlue(nx, ny)) {
					continue;
				}
			}


			// 흰색 OR 빨간색
			int color = graph[nx][ny];
			Stack<Player> nowStack = maps.get(nowKey);
			Stack<Player> tmp = new Stack<>();

			while (!nowStack.isEmpty()) {
				Player p = nowStack.pop();

				p.row = nx;
				p.col = ny;

				tmp.add(p);
				if (p == player) {
					break;
				}
			}

			List<Player> move = new ArrayList<>();
			while (!tmp.isEmpty()) {
				move.add(tmp.pop());
			}

			if (color == 1) {
				Collections.reverse(move);
			}
			String nextKey = nx + " " + ny;
			Stack<Player> nextStack = maps.getOrDefault(nextKey, new Stack<>());
			for (Player p : move) {
				nextStack.push(p);
			}

			maps.put(nextKey, nextStack);

			isGameEnd();
		}
	}



	private static void isGameEnd() {
		for (Stack<Player> dq : maps.values()) {
			if (dq.size() >= 4) {
				gameEnd = true;
				break;
			}
		}
	}

	private static void printResult() {
		if (gameEnd) {
			System.out.println(turn);
		} else {
			System.out.println(-1);
		}
	}
}

class Player {

	int row;
	int col;
	int direction;

	Player(int row, int col, int direction) {
		this.row = row;
		this.col = col;
		this.direction = direction;
	}
}