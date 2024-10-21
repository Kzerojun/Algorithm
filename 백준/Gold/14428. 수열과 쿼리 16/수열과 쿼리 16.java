

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        simulate();
    }

    private static void simulate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 수열 크기 N
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        // 수열 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 세그먼트 트리 생성
        SegmentTree tree = new SegmentTree(arr);
        
        // 쿼리 개수 M
        int m = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());
            
            if(query == 2) {
                // 쿼리 2: i ~ j 구간에서 가장 작은 값의 인덱스를 찾는 작업
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                
                int result = tree.query(start, end);
                // 1-based로 인덱스 반환
                System.out.println(result + 1);
            } else {
                // 쿼리 1: Ai 값을 v로 업데이트
                int index = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                tree.update(index, value);
            }
        }
    }
}

class SegmentTree {
    private int[] arr;
    private int[] tree;
    private int[] index;
    private int n;

    SegmentTree(int[] arr) {
        this.arr = arr;
        this.n = arr.length;
        this.tree = new int[2 * n];
        this.index = new int[2 * n];
        init();
    }

    private void init() {
        // 리프 노드 초기화
        for (int i = 0; i < n; i++) {
            tree[i + n] = arr[i];
            index[i + n] = i;
        }

        // 내부 노드 초기화
        for (int i = n - 1; i > 0; i--) {
            // 자식 노드들의 최솟값을 저장
            tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
            if (tree[i << 1] <= tree[i << 1 | 1]) {
                index[i] = index[i << 1];
            } else {
                index[i] = index[i << 1 | 1];
            }
        }
    }

    // 인덱스 i의 값을 value로 업데이트
    public void update(int idx, int value) {
        idx += n;
        tree[idx] = value;

        // 부모 노드 갱신
        while (idx > 1) {
            idx >>= 1;
            int left = idx << 1;
            int right = idx << 1 | 1;
            
            // 왼쪽 자식이 더 작거나 같을 때
            if (tree[left] <= tree[right] ) {
                tree[idx] = tree[left];
                this.index[idx] = this.index[left];
            } else {
                // 오른쪽 자식이 더 작을 때
                tree[idx] = tree[right];
                this.index[idx] = this.index[right];
            }
        }
    }
    
    // 구간 [start, end]에서 최솟값의 인덱스 반환
    public int query(int start, int end) {
        int left = n + start;
        int right = n + end;
        
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        
        // 구간 탐색
        while (left <= right) {
            if ((left & 1) == 1) {
                // 왼쪽 구간이 포함될 때
                if (tree[left] < minValue || (tree[left] == minValue && index[left] < minIndex)) {
                    minValue = tree[left];
                    minIndex = index[left];
                }
                left++;
            }
            
            if ((right & 1) == 0) {
                // 오른쪽 구간이 포함될 때
                if (tree[right] < minValue || (tree[right] == minValue && index[right] < minIndex)) {
                    minValue = tree[right];
                    minIndex = index[right];
                }
                right--;
            }
            
            left >>= 1;
            right >>= 1;
        }
        
        return minIndex;    
    }
}
