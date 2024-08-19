
import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static BufferedReader br;
	static int N;
	static int[][] synergy;
	static List<List<Integer>> combinations;
	static int sol;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void start() throws IOException {
		for (int t = 1; t <= T; t++) {
			initEachCase();
			makeCombinations(new ArrayList<>(),0);
			caluclate();

			System.out.println("#" + t + " " + sol);
		}
	}

	private static void initEachCase() throws IOException {
		N = Integer.parseInt(br.readLine());

		synergy = new int[N][N];
		combinations = new ArrayList<>();
		sol = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void makeCombinations(List<Integer> tmp,int index) {

		if (tmp.size() == N / 2) {
			combinations.add(new ArrayList<>(tmp));
			return;
		}

		for (int i = index; i < N; i++) {
			tmp.add(i);
			makeCombinations(tmp,i+1);
			tmp.remove(tmp.size() - 1);
		}
	}

	private static void caluclate() {

		for(int i = 0 ; i<combinations.size()/2; i++) {
			calScore(i);
		}
	}

	private static void calScore(int index) {
		List<Integer> A = combinations.get(index);
		List<Integer> B = combinations.get(combinations.size()-index-1);
		

		int aTaste = 0;
		int bTaste = 0;
		
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < A.size(); j++) {
				aTaste += synergy[A.get(i)][A.get(j)];
			}
		}

		for (int i = 0; i < B.size(); i++) {
			for (int j = 0; j < B.size(); j++) {
				bTaste += synergy[B.get(i)][B.get(j)];
			}
		}
		

		int result = Math.abs(aTaste - bTaste);

		sol = Math.min(sol, result);

	}
}
