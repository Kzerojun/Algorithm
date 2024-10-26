import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	public static void main(String[] args) throws IOException{
		simulate();

	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());


		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		SegmentTree segmentTree = new SegmentTree(arr);

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());

			if (query == 1) {
				int index = Integer.parseInt(st.nextToken())-1;
				int value = Integer.parseInt(st.nextToken());

				segmentTree.update(index, value);

			}else {
				int ts = Integer.parseInt(st.nextToken());
				int te = Integer.parseInt(st.nextToken());

				System.out.println(segmentTree.query(ts, te));
			}
		}


	}

}

class SegmentTree{

	int[] tree;

	int SIZE = 1<<18;
	int LENGTH = 1<<17;

	SegmentTree(int[] arr) {
		this.tree = new int[SIZE];
		init(arr);
	}

	private void init(int[] arr) {
		// 리프 노드에 값 채우기
		for(int i = 0; i < arr.length; i++) {
			tree[i + LENGTH] = arr[i];
		}

		// 부모 노드들의 값을 채우기
		for(int i = LENGTH-1; i >0; i--) {
			tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);  // 수정된 부분
		}
	}

	public void update(int index,int number) {
		index = index + LENGTH;
		tree[index] = number;

		index/=2;

		while(index>=1) {
			tree[index] = Math.min(tree[index * 2], tree[index * 2 +1]);
			index/=2;
		}
	}

	public int query(int ts, int te) {
		return query(1, 1, LENGTH, ts, te);
	}

	private int query(int index, int s, int e ,int ts, int te) {
		if(e<ts || s >te) {
			return Integer.MAX_VALUE;
		}else if(ts<=s && e<=te) {
			return tree[index];
		}

		int mid = (s+e)/2;
		int left = query(index * 2, s, mid, ts, te);
		int right= query(index * 2 + 1, mid + 1, e, ts, te);

		return Math.min(left,right);
	}
}