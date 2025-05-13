import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] numbers;


	public static void main(String[] args) throws Exception {
		init();
		int result = simulate();
		printResult(result);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			numbers[i] = number;
		}
	}

	private static int simulate() {
		Arrays.sort(numbers);
		int sol = 0;

		if (N <= 2) {
			return 0;
		}

		for (int i = 0; i < N; i++) {
			boolean result = isGood(i);

			if (result) {
				sol++;
			}
		}

		return sol;
	}

	private static boolean isGood(int index) {
		int left = 0;
		int right = N - 1;
		int target = numbers[index];

		while (left < right) {
			int sum = numbers[left] + numbers[right];

			if (left == index) { left++; continue; }
			if (right == index) { right--; continue; }

			if (sum < target) {
				left++;
			} else if (sum > target) {
				right--;
			} else {
				return true;
			}
		}

		return false;
	}

	private static void printResult(int result) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(result + " ");
		bw.flush();
		bw.close();
	}

}
