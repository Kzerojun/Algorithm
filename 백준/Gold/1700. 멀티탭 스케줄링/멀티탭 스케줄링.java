import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	static int N, K;
	static int[] plugs;
	static int[] schedule; // 전기 용품 사용 순서

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(simulate());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		plugs = new int[N];
		schedule = new int[K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			schedule[i] = Integer.parseInt(st.nextToken());
		}

	}

	private static int simulate() {
		int result = 0;

		for (int i = 0; i < K; i++) {
			int number = schedule[i];

			if (isPlugged(number)) {
				continue;
			}

			if (isEmptySlot(number)) {
				continue;
			}

			result++;
			int index = findIndex(i);

			plugs[index] = number;
		}

		return result;
	}

	private static boolean isPlugged(int number) {
		for (int plug : plugs) {
			if (plug == number) {
				return true;
			}
		}

		return false;
	}

	private static boolean isEmptySlot(int number) {
		for (int i = 0; i < N; i++) {
			if (plugs[i] == 0) {
				plugs[i] = number;
				return true;
			}
		}

		return false;
	}

	private static int findIndex(int currentIndex) {
		int farIndex = -1;
		int plugIndex = -1;
		for (int i = 0; i < N; i++) {
			int plugNumber = plugs[i];

			boolean willUsed = false;
			int index = Integer.MAX_VALUE;

			for (int j = currentIndex; j < K; j++) {
				if (schedule[j] == plugNumber) {
					willUsed = true;
					index = j;
					break;
				}
			}

			if (!willUsed) {
				return i;
			}

			if (index > farIndex) {
				farIndex = index;
				plugIndex = i;
			}
		}

		return plugIndex;

	}
}