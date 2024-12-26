import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static int N;
	static int[] colorsCount;
	static long[][][][] dp;


	public static void main(String[] args) throws IOException {
		init();
	}


	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		colorsCount = new int[3];

		for (int i = 0; i < 3; i++) {
			// 0 빨강 , 1 초록, 2 파랑
			colorsCount[i] = Integer.parseInt(st.nextToken());
		}

		// 레벨 , 빨강 , 초록, 파랑
		dp = new long[N + 1][101][101][101];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= 100; j++) {
				for (int k = 0; k <= 100; k++) {
					for (int z = 0; z <= 100; z++) {
						dp[i][j][k][z] = -1;
					}
				}
			}
		}

		System.out.println(dp(0, 0, 0, 0, colorsCount));

	}

	private static long dp(int level, int red, int green, int blue,int[] colorsCount) {
		if (level == N) {
			return 1;
		}

		if (dp[level][red][green][blue] != -1) {
			return dp[level][red][green][blue];
		}

		dp[level][red][green][blue] = 0;
		List<Combination> combinations = new LinkedList<>();
		makeCombinations(level + 1, 0, 0, 0, 0, combinations);

		for (Combination comb : combinations) {

			if (colorsCount[0] >= comb.redCount && colorsCount[1] >= comb.greenCount
					&& colorsCount[2] >= comb.blueCount) {

				colorsCount[0] -= comb.redCount;
				colorsCount[1] -= comb.greenCount;
				colorsCount[2] -= comb.blueCount;

				long result = dp(level + 1, red+comb.redCount, green+comb.greenCount,blue+comb.blueCount, colorsCount);
				dp[level][red][green][blue] +=result;

				colorsCount[0] += comb.redCount;
				colorsCount[1] += comb.greenCount;
				colorsCount[2] += comb.blueCount;
			}
		}

		return dp[level][red][green][blue];
	}


	private static void makeCombinations(int level, int count, int red, int green, int blue,List<Combination> combinations) {
		if (count == level) {
			if (isPossible(red, green, blue, level)) {
				combinations.add(new Combination(red, green, blue));
			}

			return;
		}

		makeCombinations(level,count+1,red+1,green,blue,combinations);
		makeCombinations(level,count+1,red,green+1,blue,combinations);
		makeCombinations(level,count+1,red,green,blue+1,combinations);

	}

	private static boolean isPossible(int red, int green, int blue, int level) {
		if ((red + green + blue) != level) {
			return false;
		}

		if ((red > 0 && green > 0 && red != green) ||
				(red > 0 && blue > 0 && red != blue) ||
				(green > 0 && blue > 0 && green != blue)) {
			return false;
		}

		return true;
	}




}

class Combination{
	int redCount;
	int greenCount;
	int blueCount;

	Combination(int redCount, int greenCount, int blueCount) {
		this.redCount = redCount;
		this.greenCount = greenCount;
		this.blueCount = blueCount;
	}
}