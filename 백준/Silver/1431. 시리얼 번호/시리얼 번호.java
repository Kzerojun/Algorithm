import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

class Main {

	static int N;

	public static void main(String[] args)throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.length() == o2.length()) {
				if (calValue(o1) == calValue(o2)) {
					return o1.compareTo(o2);
				}
				return calValue(o1) - calValue(o2);
			}
			return o1.length() - o2.length();
		});

		for (int i = 0; i < N; i++) {
			pq.add(br.readLine());
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (!pq.isEmpty()) {
			bw.write(pq.poll() + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static int calValue(String word) {
		int result = 0;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (Character.isDigit(ch)) {
				result = result + (ch - '0');
			}
		}

		return result;
	}
}