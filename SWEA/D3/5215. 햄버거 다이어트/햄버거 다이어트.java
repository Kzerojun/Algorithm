import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static int T;
	static int[][] dp;
	static List<Food> foods;
	
	static int N,L;
	public static void main(String[] args) throws IOException {
		init();
		play();
		
	}
	
	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}
	
	private static void play() throws IOException {
		for(int i = 1; i<=T; i++) {
			initGame();
			int result = calculate();
			System.out.println("#"+i+" "+result);

		}
	}
	
	private static void initGame() throws IOException {

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][L+1];
		foods = new ArrayList<>();
		
		for(int i = 0 ; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int score = Integer.parseInt(st.nextToken());
			int calorie = Integer.parseInt(st.nextToken());
			
			foods.add(new Food(score,calorie));
		}
		
	
	}
	
	private static int calculate() {
		for(int i = 1; i<=N; i++) {
			Food food = foods.get(i-1);
			for(int j = 0; j<=L; j++) {
				if(food.calorie<=j) {
					dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-food.calorie]+food.score);
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}

		}
		
		return dp[N][L];
	}
	

}

class Food {
	int score;
	int calorie;
	
	Food(int score, int calorie) {
		this.score = score;
		this.calorie = calorie;
	}
}
