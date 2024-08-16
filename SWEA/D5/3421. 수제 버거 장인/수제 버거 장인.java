import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br;
	static int T;
	static int N, M;
	static List<List<Integer>> combinations;
	static int[][] not;

	public static void main(String[] args) throws IOException {
		init();
		play();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void play() throws IOException {

		for (int t = 1; t <= T; t++) {
			initGameInfo();
			playGame(t);
		}
	}

	private static void initGameInfo() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		combinations = new ArrayList<>();
		not = new int[M][2];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			not[i][0] = Integer.parseInt(st.nextToken());
			not[i][1] = Integer.parseInt(st.nextToken());
		}
	}

	private static void makeCombination(List<Integer> origin, List<Integer> tmp,int index) {
		boolean check = false;
		for (int i = 0; i < M; i++) {
			if (tmp.contains(not[i][0]) && tmp.contains(not[i][1])) {
				check = true;
				break;
			}
		}
		
		if(!check) {
			combinations.add(new ArrayList<>(tmp));
		}
		
		
		
		
		
		
		

		if(index == N) {
			return;
		}
		
		for(int i = index ;i <N; i++) {
			tmp.add(origin.get(i));
			makeCombination(origin,tmp,i+1);
			tmp.remove(tmp.size()-1);
		}

	}

	private static void playGame(int t) {
		List<Integer> origin = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			origin.add(i + 1);
		}

		makeCombination(origin, new ArrayList<>(), 0);
		int result = combinations.size();
		System.out.println("#" + t + " " + result);

	}
}
