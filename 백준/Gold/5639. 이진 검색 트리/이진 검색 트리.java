import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String input = br.readLine();
            if (input==null||input.isEmpty()) {
                break;
            }
            root.insert(Integer.parseInt(input));

        }
        print(root);


    }

    static void print(Node node) {
        if (node != null) {
            print(node.left);
            print(node.right);
            System.out.println(node.data);
        }
    }
}

class Node{
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    void insert(int input) {
        if (this.data > input) {
            if (this.left == null) {
                this.left = new Node(input);
                return;
            }
            this.left.insert(input);
        }

        if (this.data < input) {
            if (this.right == null) {
                this.right = new Node(input);
                return;
            }
            this.right.insert(input);

        }
    }
}