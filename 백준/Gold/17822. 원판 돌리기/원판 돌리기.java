import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static int N, M, T;
	static List<Command> commands;
	static int[][] circle;

	public static void main(String[] args) throws IOException {
		init();
		processCommands();
		System.out.println(calculateSum());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		circle = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		commands = new ArrayList<>();

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			commands.add(new Command(x, d, k));
		}
	}

	static void processCommands() {
		for (Command command : commands) {
			executeCommand(command);
			adjustValuesBasedOnNeighbors();
		}
	}

	static void executeCommand(Command command) {
		for (int i = command.x - 1; i < N; i += command.x) {
			spin(i, command.d, command.k);
		}
	}

	static void spin(int circleNumber, int direction, int count) {
		int[] newCircle = new int[M];
		System.arraycopy(circle[circleNumber], 0, newCircle, 0, M);

		for (int i = 0; i < M; i++) {
			int newPosition = (i + (direction == 0 ? count : M - count)) % M;
			newCircle[newPosition] = circle[circleNumber][i];
		}

		circle[circleNumber] = newCircle;
	}

	static void adjustValuesBasedOnNeighbors() {
		boolean[][] toClear = new boolean[N][M];
		boolean found = false;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int current = circle[i][j];
				if (current != 0) {
					if (circle[i][(j + 1) % M] == current || circle[i][(j + M - 1) % M] == current ||
							(i > 0 && circle[i - 1][j] == current) || (i < N - 1 && circle[i + 1][j] == current)) {
						toClear[i][j] = true;
						found = true;
					}
				}
			}
		}

		if (found) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (toClear[i][j]) circle[i][j] = 0;
				}
			}
		} else {
			adjustValuesBasedOnAverage();
		}
	}

	static void adjustValuesBasedOnAverage() {
		int totalSum = 0;
		int totalCount = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (circle[i][j] != 0) {
					totalSum += circle[i][j];
					totalCount++;
				}
			}
		}

		if (totalCount > 0) {
			double average = (double) totalSum / totalCount;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (circle[i][j] != 0) {
						if (circle[i][j] > average) {
							circle[i][j]--;
						} else if (circle[i][j] < average) {
							circle[i][j]++;
						}
					}
				}
			}
		}
	}

	static int calculateSum() {
		int sum = 0;
		for (int[] row : circle) {
			for (int num : row) {
				sum += num;
			}
		}
		return sum;
	}
}

class Command {
	int x;
	int d;
	int k;

	Command(int x, int d, int k) {
		this.x = x;
		this.d = d;
		this.k = k;
	}
}
