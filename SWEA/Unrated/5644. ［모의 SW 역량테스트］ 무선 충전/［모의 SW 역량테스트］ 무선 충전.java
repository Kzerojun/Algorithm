import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br;
	static int T;
	static Player playerA;
	static Player playerB;
	static int[] aMoveInfos;
	static int[] bMoveInfos;
	static int M, A; // 이동시간, BC의 개수
	static Map<String, List<Charger>> maps;
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	static int result;

	public static void main(String[] args) throws IOException {
		init();
		startEachCase();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void initEachCase() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		playerA = new Player(1, 1, 0);
		playerB = new Player(10, 10, 0);
		aMoveInfos = new int[M];
		bMoveInfos = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			aMoveInfos[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			bMoveInfos[i] = Integer.parseInt(st.nextToken());
		}

		maps = new HashMap<>();

		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int range = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());

			calculateChargerRange(y, x, range, power, i);
		}
		result = 0;
	}

	private static void calculateChargerRange(int x, int y, int range, int power, int id) {
		Queue<Location> q = new LinkedList<>();
		boolean[][] visited = new boolean[11][11];
		q.add(new Location(x, y));
		while (!q.isEmpty()) {
			Location now = q.poll();

			for (int i = 0; i < 5; i++) {
				int nx = dx[i] + now.x;
				int ny = dy[i] + now.y;

				if (nx < 1 || ny < 1 || nx > 10 || ny > 10 || visited[nx][ny])
					continue;

				if (Math.abs(nx - x) + Math.abs(ny - y) <= range) {
					visited[nx][ny] = true;
					String key = nx + " " + ny;
					List<Charger> list = maps.getOrDefault(key, new ArrayList<>());
					list.add(new Charger(id, power));
					maps.put(key, list);
					q.add(new Location(nx, ny));
				}
			}
		}

	}

	private static void startEachCase() throws IOException {
		for (int t = 1; t <= T; t++) {
			initEachCase();
			simulate();


			System.out.println("#" + t + " " + result);
		}
	}

	private static void simulate() {
		charge();

		for (int i = 0; i < M; i++) {
			move(aMoveInfos[i], playerA);
			move(bMoveInfos[i], playerB);
			charge();
		}
	}

	private static void move(int direction, Player player) {
		player.x = player.x + dx[direction];
		player.y = player.y + dy[direction];
	}

	private static void charge() {
		String playerAKey = playerA.x + " " + playerA.y;
		String playerBKey = playerB.x + " " + playerB.y;

		List<Charger> aCharger = new ArrayList<>();
		List<Charger> bCharger = new ArrayList<>();

		if (maps.containsKey(playerAKey)) {
			aCharger = maps.get(playerAKey);
		}
		if (maps.containsKey(playerBKey)) {
			bCharger = maps.get(playerBKey);
		}

		int tmpSum = Integer.MIN_VALUE;

		if (aCharger.size() == 0) {
			for (Charger charge : bCharger) {
				tmpSum = Math.max(charge.power, tmpSum);
			}
		} else if (bCharger.size() == 0) {
			for (Charger charge : aCharger) {
				tmpSum = Math.max(charge.power, tmpSum);
			}
		} else {
			for (Charger acg : aCharger) {
				for (Charger bcg : bCharger) {
					if (acg.id == bcg.id) {
						tmpSum = Math.max(acg.power, tmpSum);
					} else {
						tmpSum = Math.max(tmpSum, acg.power + bcg.power);
					}
				}
			}
		}
		


		if (tmpSum != Integer.MIN_VALUE) {
			result += tmpSum;
		}


	}
}

class Player extends Location {
	Location location;
	int chargeValue;

	Player(int x, int y, int chargeValue) {
		super(x, y);
		this.chargeValue = chargeValue;
	}
}

class Location {
	int x;
	int y;

	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Charger {
	int id;
	int power;

	Charger(int id, int power) {
		this.id = id;
		this.power = power;
	}
}
