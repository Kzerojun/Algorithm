import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{

	static int C,N;
	static int[][] dp;
	static City[] cities;

	public static void main(String[] args) throws  IOException{
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		cities = new City[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int cost = Integer.parseInt(st.nextToken());
			int peopleCount = Integer.parseInt(st.nextToken());

			cities[i] = new City(cost, peopleCount);
		}

		dp = new int[N][1001];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i],-1);
		}

		System.out.println(dfs(0,C));

	}

	private static int dfs(int index ,int target) {
		if(target <= 0) {
			return 0;
		}

		if(dp[index][target]!=-1) {
			return dp[index][target];
		}

		dp[index][target] = Integer.MAX_VALUE;

		for(int i = 0 ; i<N; i++) {
			int result = dfs(i,target-cities[i].peopleCounts);
			dp[index][target] = Math.min(result+cities[i].cost,dp[index][target]);
		}

		return dp[index][target];
	}

}

class City{
	int cost;
	int peopleCounts;

	City(int cost, int peopleCounts) {
		this.cost = cost;
		this.peopleCounts = peopleCounts;
	}
}