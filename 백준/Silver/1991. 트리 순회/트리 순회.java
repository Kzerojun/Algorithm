import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int n;
    static Node[] nodes;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = st.nextToken().charAt(0) - 'A';
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);

            int left = l == '.' ? -1 : l - 'A';
            int right = r == '.' ? -1 : r - 'A';

            nodes[x] = new Node(left, right);
        }

        preOrder(0);
        System.out.println();
        inorder(0);
        System.out.println();
        postorder(0);
        System.out.println();
    }

    static void preOrder(int x) {
        if (x == -1) {
            return;
        }
        System.out.print((char) (x + 'A'));
        preOrder(nodes[x].left);
        preOrder(nodes[x].right);
    }

    static void inorder(int x) {

        if (x == -1) {
            return;
        }

        inorder(nodes[x].left);
        System.out.print((char)(x+'A'));
        inorder(nodes[x].right);
    }

    static void postorder(int x) {

        if (x == -1) {
            return;
        }

        postorder(nodes[x].left);
        postorder(nodes[x].right);
        System.out.print((char)(x+'A'));
    }

}

class Node {
    int left;
    int right;

    Node(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
