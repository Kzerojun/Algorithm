import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	static int N;
	static List<Lecture> lectures;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		lectures = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());

			lectures.add(new Lecture(number, startTime, endTime));
		}

		lectures.sort((o1, o2) -> o1.startTime - o2.startTime);

		PriorityQueue<Lecture> pq = new PriorityQueue<>((o1, o2) -> {
			return o1.endTime - o2.endTime;
		});

		int result = 0;

		for (int i = 0; i < N; i++) {
			while (!pq.isEmpty() && pq.peek().endTime <= lectures.get(i).startTime) {
				pq.poll();
			}
			pq.add(lectures.get(i));
			result = Math.max(result, pq.size());
		}

		System.out.println(result);
	}
}

class Lecture {

	int number;
	int startTime;
	int endTime;

	Lecture(int number, int startTime, int endTime) {
		this.number = number;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
