import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
	static char[][] graph;
	static char star = '*';
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new char[N][N*2-1];

		for (int i = 0; i < N; i++) {
			Arrays.fill(graph[i],' ');
		}

		StringBuilder sb = new StringBuilder();


		calculate(0,N-1,N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N*2-1; j++) {
				sb.append(graph[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	static void calculate(int row, int col, int N) {
		if (N == 3) {
			graph[row][col] = star;
			graph[row+1][col-1] = graph[row+1][col+1] = star;
			graph[row+2][col-2] = graph[row+2][col+2] = graph[row+2][col-1]= graph[row+2][col+1] =graph[row+2][col] =star;

		} else {
			int divide = N/2;
			calculate(row,col,divide);
			calculate(row+(N/2),col+(N/2),divide);
			calculate(row+(N/2),col-(N/2),divide);
		}
	}
}