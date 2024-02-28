import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	static int[][] board;
	static List<Integer> result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[9][9];
		result = new ArrayList<>();

		int size = 0;

		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				int number = line.charAt(j) - '0';
				if (number == 0)
					size++;
				board[i][j] = number;
			}
		}

		dfs(0);

		Collections.sort(result);
	}

	static void dfs(int count) {
		if (count == 81) {
			printBoard();
			return;
		}

		int i = count / 9;
		int j = count % 9;

		if (board[i][j] > 0) {
			dfs(count + 1);
		} else {
			for (int number = 1; number <= 9; number++) {
				if (validate(i, j, number)) {
					board[i][j] = number;
					dfs(count + 1);
					board[i][j] = 0;  // Backtrack
				}
			}
		}
	}

	static boolean validate(int i, int j, int number) {
		return validateRow(i, number) && validateColumn(j, number) && validateNineToNine(i, j,
				number);
	}

	static boolean validateRow(int i, int number) {
		for (int column = 0; column < 9; column++) {
			if (board[i][column] == number) {
				return false;
			}
		}
		return true;
	}

	static boolean validateColumn(int j, int number) {
		for (int row = 0; row < 9; row++) {
			if (board[row][j] == number) {
				return false;
			}
		}
		return true;
	}

	static boolean validateNineToNine(int i, int j, int number) {
		int startX = (i / 3) * 3;
		int startY = (j / 3) * 3;

		for (int x = startX; x < startX + 3; x++) {
			for (int y = startY; y < startY + 3; y++) {
				if (board[x][y] == number) {
					return false;
				}
			}
		}
		return true;
	}

	static void printBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		System.exit(0);
	}
}
