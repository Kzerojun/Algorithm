import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Main {

	static int N;
	static List<Integer> heights;
	static int solution = Integer.MIN_VALUE;


	public static void main(String[] args) throws IOException {
		init();
		start();
		print();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		heights = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			heights.add(Integer.parseInt(br.readLine()));
		}

		br.close();
	}

	private static void start() {
		Stack<Block> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			int lastWidth = 0;
			while (!stack.isEmpty() && stack.peek().height > heights.get(i)) {
				Block top = stack.pop();
				lastWidth += top.cnt;
				solution = Math.max(solution, top.height * lastWidth);
			}
			stack.push(new Block(heights.get(i), lastWidth + 1));
		}

		int lastWidth = 0;
		while (!stack.isEmpty()) {
			Block top = stack.pop();
			lastWidth += top.cnt;
			solution = Math.max(solution, top.height * lastWidth);
		}


	}

	private static void print() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(solution + "");
		bw.flush();
		bw.close();

	}
}

class Block {

	int height;
	int cnt;

	Block(int height, int cnt) {
		this.height = height;
		this.cnt = cnt;
	}
}