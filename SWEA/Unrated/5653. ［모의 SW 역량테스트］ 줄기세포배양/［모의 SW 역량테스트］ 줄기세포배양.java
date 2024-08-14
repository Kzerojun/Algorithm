import java.util.*;
import java.io.*;

class Solution {
	static BufferedReader br;
	static int T;

	static int N, M, K;

	static Map<String, Cell> graph;

	static List<Cell> cells;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void start() throws IOException {
		for (int testcase = 1; testcase <= T; testcase++) {
			initGame();
			play(testcase);
		}
	}

	private static void initGame() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cells = new ArrayList<>();
		graph = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < M; j++) {
				int life = Integer.parseInt(st.nextToken());
				if (life > 0) {
					Cell cell = new Cell(i, j, life);
					cells.add(cell);
					String key = i + " " + j;
					graph.put(key, cell);
				}
			}
		}
	}

	private static void play(int testcase) {
		int time = 0;

		while (time++ < K) {
			cells.sort((o1, o2) -> {
				return o2.life - o1.life;
			});
			
			
			int size = cells.size();

			for(int i = 0 ; i<size; i++) {
				Cell cell = cells.get(i);
				cell.updateTime();
			}
			
			for(int i = size-1; i>=0; i--) {
				if(cells.get(i).activeLife==0) {
					cells.remove(i);
				}
			}
		}

		System.out.println("#" + testcase + " " + cells.size());
	}

	static class Cell {
		int x;
		int y;
		int life;
		int deactiveLife;
		int activeLife;
		// true -> 활성화 false -> 비활성화
		boolean status;

		Cell(int x, int y, int life) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.deactiveLife = life;
			this.activeLife = life;
			this.status = false;
		}

		private void updateTime() {
			if (!this.status) {
				this.deactiveLife--;
				if (this.deactiveLife == 0) {
					status = true;
				}
			} else {
				this.activeLife--;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					String key = nx + " " + ny;

					if (graph.containsKey(key)) {
						continue;
					}

					Cell next = new Cell(nx, ny, this.life);
					graph.put(key, next);
					cells.add(next);
				}
			}

		}
	}
}
