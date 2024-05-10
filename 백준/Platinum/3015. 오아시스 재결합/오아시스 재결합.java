import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Main {

	static int N;
	static long cnt;

	static Stack<Node> stack;

	public static void main(String[] args) throws IOException {
		init();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			Node now = new Node(height, 1);

			while (!stack.isEmpty() && stack.peek().height <= height) {
				Node top = stack.pop();
				cnt += top.cnt;
				if (top.height == height) {
					now.cnt += top.cnt;
				}
			}

			if (!stack.isEmpty()) {
				cnt++;
			}

			stack.push(now);
		}

		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
	}


	static class Node{
		int height;
		int cnt;

		Node(int height, int cnt) {
			this.height = height;
			this.cnt = cnt;
		}
	}

}