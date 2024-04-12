import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

	static char[][] stars;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		stars = new char[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				stars[i][j] = ' ';

			}
		}

		star(0, 0, N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(stars[i][j]);
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();

	}

	public static void star(int x, int y, int n) {

		if (n == 1) {
			stars[x][y] = '*';
			return;
		}

		int size = n / 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(i == 1 && j == 1)) {
					star(x + i * size, y + j * size, size);
				}
			}
		}
	}
}