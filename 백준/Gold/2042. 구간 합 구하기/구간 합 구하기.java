import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr, N);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {  // Update operation
                segmentTree.update(1, 0, N - 1, b - 1, c);
            } else if (a == 2) {  // Sum operation
                sb.append(segmentTree.sum(1, 0, N - 1, b - 1, (int)c - 1)).append('\n');
            }
        }
        System.out.print(sb.toString());
    }

    static class SegmentTree {
        long[] tree;
        long[] arr;

        SegmentTree(long[] arr, int n) {
            this.arr = arr;
            tree = new long[n * 4];
            build(1, 0, n - 1);
        }

        void build(int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(node * 2, start, mid);
                build(node * 2 + 1, mid + 1, end);
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
            }
        }

        void update(int node, int start, int end, int idx, long val) {
            if (start == idx && end == idx) {
                tree[node] = val;
                return;
            }
            if (start <= idx && idx <= end) {
                int mid = (start + end) / 2;
                update(node * 2, start, mid, idx, val);
                update(node * 2 + 1, mid + 1, end, idx, val);
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
            }
        }

        long sum(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return 0;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = (start + end) / 2;
            return sum(node * 2, start, mid, l, r) + sum(node * 2 + 1, mid + 1, end, l, r);
        }
    }
}
