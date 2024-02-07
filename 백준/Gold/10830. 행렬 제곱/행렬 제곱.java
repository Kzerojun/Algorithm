import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static int[][] procession;
	static int MOD = 1000;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		procession = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				procession[i][j] = Integer.parseInt(st.nextToken())%MOD;
			}
		}

		int[][] result = pow(procession, B);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				System.out.print(result[i][j]+" ");

			}
			System.out.println();

		}
	}

	static int[][] pow(int[][] A, long exp) {
		if (exp == 1L) {
			return A;
		}

		int[][] ret = pow(A, exp / 2);

		ret = multiply(ret, ret);

		if (exp % 2 == 1L) {
			ret = multiply(ret, procession);
		}

		return ret;
	}

	static int[][] multiply(int[][] o1, int[][] o2) {
		int[][] tmp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					tmp[i][j] += o1[i][k] * o2[k][j];
					tmp[i][j] %=MOD;
				}
			}
		}

		return tmp;
	}


}
