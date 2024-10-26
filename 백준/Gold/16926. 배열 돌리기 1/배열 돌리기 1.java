import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	static int N,M,R;
	static int[][] graph;

	public static void main(String[] args)throws  IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		graph = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int k = 0 ; k<R; k++) {
			for(int i = 0; i<Math.min(N/2,M/2); i++) {
				rotate(i,i,N-i-1,M-i-1);
			}
		}


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(graph[i][j]+" ");
			}
			System.out.println();
		}


	}


	//반시계 방향은 시계방향 시계방향은 반시계로 생각하면 편함
	private static void rotate(int x1, int y1, int x2, int y2) {
		int tmp = graph[x1][y1];

		//위
		for(int j = y1; j<y2; j++) {
			graph[x1][j] = graph[x1][j+1];
		}
		//오
		for(int i = x1; i<x2; i++) {
			graph[i][y2] = graph[i+1][y2];
		}
		//아
		for(int j = y2; j>y1; j--) {
			graph[x2][j] = graph[x2][j-1];
		}
		//왼
		for(int i = x2; i>x1; i--) {
			graph[i][y1] = graph[i-1][y1];
		}
		graph[x1 + 1][y1] = tmp;
	}
}