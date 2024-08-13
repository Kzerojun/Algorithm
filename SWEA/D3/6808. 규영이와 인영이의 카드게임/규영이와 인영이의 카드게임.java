import java.util.*;
import java.io.*;

class Solution {

	static BufferedReader br;
	static BufferedWriter bw;
	static int T;
	static int[] inyoung;
	static int[] guyoung;
	static List<int[]> pers;
	static int win;
	static int lose;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());
	}

	private static void start() throws IOException {
		for (int testcase = 1; testcase <= T; testcase++) {
			initGameInfo();
			permutation(new int[9], new boolean[18],0);
			play(testcase);
		}
		bw.close();
		br.close();
	}

	private static void initGameInfo() throws IOException {
		pers = new ArrayList<>();
		win = 0;
		lose = 0;

		inyoung = new int[9];
		guyoung = new int[9];
		boolean[] guyoungDeck = new boolean[19];

		// 규영이 정보 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 9; i++) {
			int number = Integer.parseInt(st.nextToken());
			guyoungDeck[number] = true;
			guyoung[i] = number;
		}

		int index = 0;
		for (int i = 1; i <= 18; i++) {
			if (!guyoungDeck[i]) {
				inyoung[index++] = i;
			}
		}
	}

	private static void permutation(int[] tmp, boolean[] checked,int index) {
		if (index == 9) {
			pers.add(tmp.clone());
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!checked[i]) {
				tmp[index] = inyoung[i];
				checked[i] = true;
				permutation(tmp, checked,index+1);
				tmp[index] = 0;
				checked[i] = false;
			}
		}
	}

	private static void play(int testcase) throws IOException {
		for (int[] tmp : pers) {
			calculate(tmp);
		}
		
		bw.write("#"+testcase+" "+win+" "+lose+"\n");
		bw.flush();		
	}

	private static void calculate(int[] inyoungPer) {
		int inSum = 0;
		int guSum = 0;
		for (int i = 0; i < 9; i++) {
			int inNumber = inyoungPer[i];
			int guNumber = guyoung[i];

			if (inNumber > guNumber) {
				inSum += inNumber + guNumber;
			} else if (inNumber < guNumber) {
				guSum += inNumber + guNumber;
			}
		}

		if (inSum > guSum) {
			lose++;
		} else if (inSum < guSum) {
			win++;
		}
	}

}
