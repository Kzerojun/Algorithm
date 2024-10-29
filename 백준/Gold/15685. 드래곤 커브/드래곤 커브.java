import java.util.*;


import java.io.*;

public class Main {
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	static boolean[][] graph;
	static int N;
	static List<DragonCurve> dragonCurves;

	public static void main(String[] args) throws IOException {
		init();
		simulate();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		graph = new boolean[101][101];
		dragonCurves = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			int generation = Integer.parseInt(st.nextToken());

			dragonCurves.add(new DragonCurve(startY, startX, direction, generation));
		}
	}

	private static void simulate() {
		for (DragonCurve curve : dragonCurves) {
			move(curve);
		}

		int result = 0;

		for (int i = 0; i <= 99; i++) {
			for (int j = 0; j <= 99; j++) {
				if (graph[i][j] && graph[i + 1][j] && graph[i][j + 1] && graph[i + 1][j + 1]) {
					result++;
				}
			}
		}

		System.out.println(result);
	}

	private static void move(DragonCurve curve) {
		Queue<Info> q = new LinkedList<>();

		Info info = new Info(curve.startX, curve.startY);
		graph[info.x][info.y] = true;
		
		//0세대
		info.x = info.x + dx[curve.direction];
		info.y = info.y + dy[curve.direction];
		graph[info.x][info.y] = true;
		info.directon.add(curve.direction);
		int genertaion = 1;
		
		q.add(info);
		

		//1세대부터 시작
		while (!q.isEmpty()) {
			Info now = q.poll();
			List<Integer> directions = new ArrayList<>(now.directon);
			
			if (genertaion > curve.generation) {
				break;
			}

			for (int i = now.directon.size() - 1; i >= 0; i--) {
				
				now.x = now.x + dx[(now.directon.get(i)+1)%4];
				now.y = now.y + dy[(now.directon.get(i)+1)%4];


				graph[now.x][now.y] = true;

				directions.add((now.directon.get(i)+1));
			}
			

			now.directon = directions;
			genertaion++;
			q.add(now);


		}

	}
}

class DragonCurve {
	int startX;
	int startY;
	int direction;
	int generation;

	public DragonCurve(int startX, int startY, int direction, int generation) {
		this.startX = startX;
		this.startY = startY;
		this.direction = direction;
		this.generation = generation;
	}
}

class Info {
	int x;
	int y;
	List<Integer> directon;

	Info(int x, int y) {
		this.x = x;
		this.y = y;
		this.directon = new ArrayList<>();
	}
}
