import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static BufferedReader br;
	static int K, N;
	static List<Integer> lans;
	static long max, mid, min;

	public static void main(String[] args) throws IOException {
		init();
		calculate();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		lans = new ArrayList<>();
		max = Integer.MIN_VALUE;
		min = 1;

		for (int i = 0; i < K; i++) {
			int number = Integer.parseInt(br.readLine());
			lans.add(number);
			max = Math.max(max, number);
		}
	}

	private static void calculate() {

		while (min <= max) {
			mid = (max + min) / 2;
			int divideCount = 0;

			for (int number : lans) {
				divideCount += number / mid;
			}

			if (divideCount < N) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}

		System.out.println(max);
	}
}
