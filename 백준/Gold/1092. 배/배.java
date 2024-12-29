import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static int N, M;
	static int[] cranes;

	static List<Integer> boxes;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(simulate());
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		cranes = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cranes[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		boxes = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			boxes.add(Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(cranes);
		boxes.sort((o1, o2) -> {
			return o1 - o2;
		});


	}

	private static int simulate() {

		int time = 0;

		while (!boxes.isEmpty()) {
			boolean check = false;
			time++;

			for (int i = N - 1; i >= 0; i--) {
				int index = binarySearch(cranes[i]);

				if (index != -1) {
					if (boxes.get(index) <= cranes[i]) {
						boxes.remove(index);
						check = true;
					}
				}

			}

			if (!check) {
				return -1;
			}
		}

		return time;
	}

	private static int binarySearch(int target) {
		int left = 0;
		int right = boxes.size() - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (boxes.get(mid) <= target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return right;
	}

}