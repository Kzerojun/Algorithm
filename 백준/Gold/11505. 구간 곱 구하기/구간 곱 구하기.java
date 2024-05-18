import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, K;

    static int[] arr;
    static int INF = 1_000_000_007;

    static SegmentTree segmentTree;

    public static void main(String[] args) throws IOException {
        init();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        segmentTree = new SegmentTree(arr);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                segmentTree.update(b - 1, c); // 1-based index를 0-based index로 변환
            }
            if (a == 2) {
                int query = segmentTree.query(b - 1, c - 1); // 1-based index를 0-based index로 변환
                System.out.println(query);
            }
        }
    }

    private static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int[] arr) {
            this.n = arr.length;
            this.tree = new int[n * 4];
            build(arr, 0, 0, n - 1);
        }

        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start] % INF;
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2 + 1, start, mid);
                build(arr, node * 2 + 2, mid + 1, end);
                tree[node] = (int)((long)tree[node * 2 + 1] * tree[node * 2 + 2] % INF);
            }
        }

        public int query(int left, int right) {
            return query(0, 0, n - 1, left, right);
        }

        private int query(int node, int start, int end, int left, int right) {
            if (right < start || left > end) {
                return 1; // 곱셈 항등원
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            int leftChild = query(node * 2 + 1, start, mid, left, right);
            int rightChild = query(node * 2 + 2, mid + 1, end, left, right);

            return (int)((long)leftChild * rightChild % INF);
        }

        public void update(int index, int val) {
            update(0, 0, n - 1, index, val);
        }

        private void update(int node, int start, int end, int index, int val) {
            if (start == end) {
                tree[node] = val % INF;
            } else {
                int mid = (start + end) / 2;
                if (start <= index && index <= mid) {
                    update(node * 2 + 1, start, mid, index, val);
                } else {
                    update(node * 2 + 2, mid + 1, end, index, val);
                }
                tree[node] = (int)((long)tree[node * 2 + 1] * tree[node * 2 + 2] % INF);
            }
        }
    }
}
