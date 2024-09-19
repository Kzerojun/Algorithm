import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {

	static BufferedReader br;
	static int T;
	static int N,A;
	static long result;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
	}

	private static void start() throws IOException {
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			result = 0;

			PriorityQueue<Integer> maxQ = new PriorityQueue<>((o1, o2) -> {
				return o2 - o1;
			});

			PriorityQueue<Integer> minQ = new PriorityQueue<>((o1, o2) -> {
				return o1 - o2;
			});

			maxQ.add(A);

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				if (minQ.size() + 1 <= maxQ.size()) {
					minQ.add(a);
				}else {
					maxQ.add(a);
				}

				if (minQ.size() + 1 <= maxQ.size()) {
					minQ.add(b);
				}else {
					maxQ.add(b);
				}

				if (maxQ.peek() > minQ.peek()) {
					int tmp = minQ.poll();
					minQ.add(maxQ.poll());
					maxQ.add(tmp);
				}

				result = (result+maxQ.peek())%20171109;
			}

			System.out.println("#"+t+" "+result);

		}
	}


}
