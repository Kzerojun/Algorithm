import java.util.*;
import java.io.*;

public class Solution {
	static int[] array;
	static BufferedReader br;
	static int N, M;

	public static void main(String[] args) throws IOException {
		init();
		play();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private static void play() throws IOException {
		for (int i = 1; i <= 10; i++) {
			int N = Integer.parseInt(br.readLine());
			String line = br.readLine();
			int result = calculate(line, N);
			System.out.println("#"+i+" "+result);

		}

	}

	private static int calculate(String line, int N) {
		Stack<Character> stack = new Stack<>();

		for (int k = 0; k < N; k++) {
			char ch = line.charAt(k);
			if (ch == '}') {
				if (stack.isEmpty()) {
					return 0;
				}

				if (stack.peek() != '{') {
					return 0;
				}
				stack.pop();
				continue;
			}

			if (ch == ')') {
				if (stack.isEmpty()) {
					return 0;
				}

				if (stack.peek() != '(') {
					return 0;
				}
				stack.pop();
				continue;
			}

			if (ch == '>') {
				if (stack.isEmpty()) {

					return 0;
				}

				if (stack.peek() != '<') {

					return 0;
				}
				stack.pop();
				continue;
			}

			if (ch == ']') {
				if (stack.isEmpty()) {

					return 0;
				}

				if (stack.peek() != '[') {

					return 0;
				}
				stack.pop();
				continue;
			}
			stack.push(ch);
		}

		if (!stack.isEmpty()) {
		
			return 0;
		}

		return 1;
	}

}
