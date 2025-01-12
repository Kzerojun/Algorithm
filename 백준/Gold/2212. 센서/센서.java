import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	static int[] sensors;
	static int N, K;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		sensors = new int[N];

		for (int i = 0; i < N; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(sensors);

		int[] groups = new int[N - 1];

		for (int i = 0; i < N - 1; i++) {
			int a = sensors[i];
			int b = sensors[i+1];

			groups[i] = b - a;
		}

		Arrays.sort(groups);
		int result = 0;
		for (int i = 0; i < (N-1) -(K - 1); i++) {
			result += groups[i];
		}

		System.out.println(result);

	}


}
