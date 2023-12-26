import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BinaryTree tree = new BinaryTree();

        while(true){
            String input = br.readLine();
            if(input == null || input.isEmpty()) break;
            int data = Integer.parseInt(input);
            tree.insert(data);
        }
        print(tree.root);
    }
    
    static void print(Node node){
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
}

class BinaryTree{
    Node root;

    Node insertValue(Node node, int value) {
        if (node == null) {
            node = new Node(value);
            return node;
        }

        if (value < node.data) {
            node.left = insertValue(node.left, value);
        }
        if (value > node.data) {
            node.right = insertValue(node.right, value);
        }

        return node;
    }

    void insert(int data) {
        root = insertValue(root, data);
    }
}
