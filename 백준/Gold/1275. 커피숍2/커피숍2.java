import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	static int N, Q;

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		SegmentTree tree = new SegmentTree(arr);

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			if(x>y) {
				int tmp = x;
				x = y;
				y = tmp;
			}

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());

			long result = tree.query(x, y);
			tree.update(a, b);
			System.out.println(result);

		}
	}
}

class SegmentTree {

	int SIZE = 1 << 18;
	int LEN = 1 << 17;

	long[] tree;

	SegmentTree(int[] arr) {
		tree = new long[SIZE];
		init(arr);
	}

	private void init(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			tree[LEN + i] = arr[i];
		}

		for (int i = LEN - 1; i >= 1; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	public void update(int index, int value) {
		index = LEN + index;

		tree[index] = value;

		index /= 2;

		while (index >= 1) {
			tree[index] = tree[index * 2] + tree[index * 2 + 1];
			index /= 2;
		}
	}

	public long query(int ts, int te) {
		return query(1, 0, LEN - 1, ts, te);
	}

	private long query(int index, int s, int e, int ts, int te) {
		if (e < ts || s > te) {
			return 0;
		} else if (ts <= s && e <= te) {
			return tree[index];
		}

		int mid = (s + e) / 2;

		long left = query(index * 2, s, mid, ts, te);
		long right = query(index * 2 + 1, mid + 1, e, ts, te);

		return left + right;
	}
}