import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {

	static int[][] A;
	static int rowLength;
	static int colLength;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// A[r][c]가 k가 되기위한 최소시간
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());

		// 배열 A 초기화
		A = new int[101][101];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rowLength = 3;
		colLength = 3;
		int time = 0;

		while (time <= 100) {
			if (A[r][c] == k) {
				break;
			}

			if (colLength <= rowLength) {
				calR();
			} else {
				calC();
			}

//			for (int i = 0; i < rowLength; i++) {
//				for (int j = 0; j < colLength; j++) {
//					System.out.print(A[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();

			time++;
		}

		if (time == 101) {
			System.out.println(-1);
		} else{
			System.out.println(time);
		}

		br.close();

	}

	static void calR() {
		int[][] board = new int[101][101];

		for (int i = 0; i < rowLength; i++) {
			Map<Integer, Integer> numberCount = new TreeMap<>();

			for (int j = 0; j < 100; j++) {
				if (A[i][j] == 0) {
					continue;
				}
				numberCount.put(A[i][j], numberCount.getOrDefault(A[i][j], 0) + 1);
			}

			List<Integer> keySet = new ArrayList<>(numberCount.keySet());
			keySet.sort((o1, o2) -> {
				if (Objects.equals(numberCount.get(o1), numberCount.get(o2))) {
					return o1 - o2;
				}
				return numberCount.get(o1) - numberCount.get(o2);
			});

			colLength = Math.max(keySet.size() * 2, colLength);

			int count =0;
			for (int j = 0; j < colLength; j += 2) {
				int key = keySet.get(count);
				int value = numberCount.get(key);
				board[i][j] = key;
				board[i][j+1] = value;

				count++;
				if (count == keySet.size()) {
					break;
				}
			}
		}
		A= board;
	}

	static void calC() {
		int[][] board = new int[101][101];

		for (int i = 0; i < colLength; i++) {
			Map<Integer, Integer> numberCount = new TreeMap<>();

			for (int j = 0; j < 100; j++) {
				if (A[j][i] == 0) {
					continue;
				}
				numberCount.put(A[j][i], numberCount.getOrDefault(A[j][i], 0) + 1);
			}

			List<Integer> keySet = new ArrayList<>(numberCount.keySet());
			keySet.sort((o1, o2) -> {
				if (Objects.equals(numberCount.get(o1), numberCount.get(o2))) {
					return o1 - o2;
				}
				return numberCount.get(o1) - numberCount.get(o2);
			});

			rowLength = Math.max(keySet.size() * 2, rowLength);
			int count = 0;

			for (int j = 0; j < rowLength; j += 2) {
				int key = keySet.get(count);
				int value = numberCount.get(key);
				board[j][i] = key;
				board[j+1][i] = value;

				count++;
				if (count == keySet.size()) {
					break;
				}
			}

		}
		A = board;
	}
}
