import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
    static int[] arr;
    static int[] lisIndex;
    static List<Node> nodes;
    static int N;

    public static void main(String[] args) throws IOException {
        init();
        calculate();
        print();
    }

    private static void init()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        lisIndex = new int[N];
        nodes = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }

    private static void calculate() {
        for (int i = 0; i < N; i++) {
            if (nodes.isEmpty()) {
                nodes.add(new Node(arr[i], i));
                lisIndex[i] = 0;
            } else {
                if (nodes.get(nodes.size() - 1).val < arr[i]) {
                    nodes.add(new Node(arr[i], i));
                    lisIndex[i] = nodes.size() - 1;
                } else {
                    int index = lowerBound(0, nodes.size(), arr[i]);
                    nodes.set(index, new Node(arr[i], i));
                    lisIndex[i] = index;
                }
            }
        }
    }

    private static int lowerBound(int low, int high, int key) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (nodes.get(mid).val < key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }

    private static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int lisLength = nodes.size();
        bw.write(lisLength + "\n");

        int[] lis = new int[lisLength];
        int idx = lisLength - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (lisIndex[i] == idx) {
                lis[idx] = arr[i];
                idx--;
            }
        }

        for (int i = 0; i < lisLength; i++) {
            bw.write(lis[i] + " ");
        }

        bw.flush();
        bw.close();
    }
}

class Node{
    int val;
    int index;

    Node(int val, int index) {
        this.val = val;
        this.index = index;
    }
}
