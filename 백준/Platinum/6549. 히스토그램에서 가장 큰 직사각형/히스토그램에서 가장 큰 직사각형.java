import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	private static int[] histogram;
	private static SegmentTree segmentTree;

	private static int N;

	public static void main(String[] args) throws IOException {
		init();
	}

	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			if (N == 0) {
				return;
			}

			histogram = new int[N];

			for (int i = 0; i < N; i++) {
				histogram[i] = Integer.parseInt(st.nextToken());
			}

			start();
		}

	}

	private static void start() {
		segmentTree = new SegmentTree(histogram);
		System.out.println(segmentTree.getMaxArea(0,N-1));
	}

	private static class SegmentTree{
		int[] tree;
		int n;


		public SegmentTree(int[] arr) {
			this.n = arr.length;
			tree = new int[n * 4];
			build(arr, 0, 0, n - 1);
		}

		private void build(int[] arr, int node, int start, int end) {
			if (start == end) {
				tree[node] = start;
			}else {
				int mid = (start + end) / 2;
				build(arr, node * 2 + 1, start, mid);
				build(arr, node * 2 + 2, mid + 1, end);
				tree[node] = (arr[tree[2 * node + 1]] <= arr[tree[2 * node + 2]]) ? tree[2 * node + 1] : tree[2 * node + 2];
			}
		}

		public int query(int l, int r) {
			return query(0, 0, n - 1, l, r);
		}

		private int query(int node, int start, int end, int l, int r) {
			if (l > end || r < start) {
				return -1;
			}

			if (l <= start && end <= r) {
				return tree[node];
			}

			int mid = (start + end) / 2;
			int leftChild = query(node * 2 + 1, start, mid, l, r);
			int rightChild = query(node * 2 + 2, mid + 1, end, l, r);

			if (leftChild == -1) {
				return rightChild;
			}
			if (rightChild == -1) {
				return leftChild;
			}
			return (histogram[leftChild] <= histogram[rightChild]) ? leftChild : rightChild;
		}

		public long getMaxArea(int left, int right) {
			if (left > right) {
				return 0;
			}

			int minIndex = query(left, right);
			long area = (long) (right - left + 1) * histogram[minIndex];

			long leftArea = getMaxArea(left, minIndex - 1);
			long rightArea = getMaxArea(minIndex+1, right);

			return Math.max(area, Math.max(leftArea, rightArea));
		}
	}
}