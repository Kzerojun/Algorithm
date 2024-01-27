import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static int[] board;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n];
		move(board, 0);
		System.out.println(answer);
	}

	public static void move(int[] board, int depth) {
		if (depth == n) {
			answer++;
			return;
		}

		for (int i = 0; i < n; i++) {
			board[depth] = i;
			if (isPossible(depth)) {
				move(board, depth + 1);
			}
		}
	}

	public static boolean isPossible(int depth) {
		for (int i = 0; i < depth; i++) {
			if (board[depth] == board[i]) {
				return false;
			}
			if (Math.abs(depth - i) == Math.abs(board[depth] - board[i])) {
				return false;
			}
		}
		return true;
	}
}