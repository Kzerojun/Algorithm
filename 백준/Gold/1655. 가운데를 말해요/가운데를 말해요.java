import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

class Main{
	private static int T;
	private static PriorityQueue<Integer> min;
	private static PriorityQueue<Integer> max;

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			init(br);
			solution(br);
		}
	}

	private static void init(BufferedReader br) throws IOException {
		T = Integer.parseInt(br.readLine());
		min = new PriorityQueue<>();
		max = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
	}

	private static void solution(BufferedReader br) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			for (int i = 0; i < T; i++) {
				String number = br.readLine();
				addNum(Integer.parseInt(number));
				bw.write(findMedian() + "\n");
			}
			bw.flush();
		} finally {
			bw.close();
		}
	}

	public static int findMedian() {
		return max.peek();
	}

	private static void addNum(int num) {
		if (max.isEmpty() || num <= max.peek()) {
			max.add(num);
		} else {
			min.add(num);
		}

		if (max.size() > min.size() + 1) {
			min.add(max.poll());
		} else if (min.size() > max.size()) {
			max.add(min.poll());
		}
	}
}
