import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main{

	static int N;
	static int[][] graph;
	static int[][] visited;

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};


	public static void main(String[] args) throws IOException {
		init();
		move();
	}
	public static void init()throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		visited = new int[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = line.charAt(j) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	static void move() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0));
		visited[0][0] = 0;

		while (!q.isEmpty()) {
			Node now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				if (graph[nx][ny] == 0 && visited[nx][ny] > now.wall+1) {
					visited[nx][ny] = now.wall + 1;
					q.add(new Node(nx, ny, now.wall + 1));
				}else {
					if (graph[nx][ny] == 1 && visited[nx][ny] > now.wall) {
						visited[nx][ny] = now.wall;
						q.add(new Node(nx, ny, now.wall));
					}

				}
			}
		}

		System.out.println(visited[N-1][N-1]);
	}
}

class Node {
	int x;
	int y;
	int wall;

	Node(int x, int y, int wall) {
		this. x= x;
		this.y = y;
		this.wall = wall;
	}

}