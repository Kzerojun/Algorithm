import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static int N;
	static int[] inOrder;
	static int[] postOrder;
	static int[] pre;

	static int idx;
	public static void main(String[] args) throws IOException {
		init();
		calculate(0,N-1,0,N-1);

		for (int i : pre) {
			System.out.print(i+" ");
		}
	}

	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		inOrder = new int[N];
		postOrder = new int[N];
		pre = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			postOrder[i] = Integer.parseInt(st.nextToken());
		}

	}

	private static void calculate(int is, int ie, int ps, int pe) {
		if (is <= ie && ps <= pe) {
			pre[idx++] = postOrder[pe];

			int pos = -1;

			for (int i = is; i <= ie; i++) {
				if (inOrder[i] == postOrder[pe]) {
					pos = i;
					break;
				}
			}

			calculate(is, pos - 1, ps, ps + pos - is - 1);
			calculate(pos+1, ie, ps + pos - is ,pe-1 );
		}

	}
}