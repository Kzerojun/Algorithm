import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static int N,M;

	public static void main(String[] args)throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		SegmentTree segmentTree = new SegmentTree(arr);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int ts = Integer.parseInt(st.nextToken())-1;
			int te = Integer.parseInt(st.nextToken())-1;

			int result = segmentTree.query(ts, te);
			System.out.println(result);
		}
	}
}

class SegmentTree{
	int SIZE = 1 << 18;
	int LENGTH = 1<<17;

	int[] tree;

	SegmentTree(int[] arr) {
		this.tree = new int[SIZE];
		init(arr);

	}

	private void init(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			tree[i+LENGTH] = arr[i];
		}

		for (int i = LENGTH - 1; i >= 1; i--) {
			tree[i] = Math.min(tree[i*2],tree[i*2+1]);
		}
	}

	public int query(int ts, int te) {
		return query(1, 0, LENGTH - 1, ts, te);
	}

	private int query(int index, int s, int e, int ts, int te) {
		if (e < ts || s > te) {
			return Integer.MAX_VALUE;
		} else if (ts <= s && e <= te) {
			return tree[index];
		}

		int mid = (s+e)/2;
		int left = query(index * 2, s, mid, ts, te);
		int right = query(index * 2 + 1, mid + 1, e, ts, te);

		return Math.min(left, right);
	}
}