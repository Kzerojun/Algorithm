import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	static int N, C, M;
	static List<Box> boxes;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		boxes = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int boxCount = Integer.parseInt(st.nextToken());

			boxes.add(new Box(from, to, boxCount));
		}

		boxes.sort((o1,o2)->{
			if (o1.to == o2.to) {
				return o1.from - o2.from;
			}

			return o1.to - o2.to;
		});


		int[] maxLoad = new int[N+1];
		int result = 0;

		Arrays.fill(maxLoad, C);

		for (Box box : boxes) {
			int minCapacity = Integer.MAX_VALUE;

			for (int i = box.from; i < box.to; i++) {
				minCapacity = Math.min(minCapacity, maxLoad[i]);
			}

			int loadedMaxBox = Math.min(box.weight, minCapacity);

			for (int i = box.from; i < box.to; i++) {
				maxLoad[i] -= loadedMaxBox;
			}

			result += loadedMaxBox;
		}

		System.out.println(result);


	}
}

class Box {

	int from;

	int to;

	int weight;

	Box(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
