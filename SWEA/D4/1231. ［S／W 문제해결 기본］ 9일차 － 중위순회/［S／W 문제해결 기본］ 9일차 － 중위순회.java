import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static BufferedReader br;
	static int[] parentLeft;
	static int[] parentRight;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		simulateEachCase();
	}

	private static void simulateEachCase() throws IOException {
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			nodes = new Node[N + 1];
			parentLeft = new int[N+1];
			parentRight = new int[N+1];
				
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int number = Integer.parseInt(st.nextToken());
				String word = st.nextToken();
                int left = 0;
                int right = 0;

                if (st.hasMoreTokens()) left = Integer.parseInt(st.nextToken());
                if (st.hasMoreTokens()) right = Integer.parseInt(st.nextToken());
				Node node = new Node(number, word);
				
				nodes[number] = node;
				parentLeft[left] = number;
				parentRight[right] = number;
				
				if (number == 1) {
					nodes[1] = node;
				} else if (parentLeft[number] != 0) {
					int index = parentLeft[number];
					Node parent = nodes[index];
					parent.left = node;
				} else if (parentRight[number] != 0) {
					int index = parentRight[number];
					Node parent = nodes[index];
					parent.right = node;
				}
			}
			StringBuilder sb = new StringBuilder();
            inOrder(nodes[1], sb);
            System.out.println("#" + t + " " + sb.toString().trim());
		}
	}

	private static void inOrder(Node node, StringBuilder sb) {
	    if (node != null) {
	        inOrder(node.left, sb);
	        sb.append(node.word);
	        inOrder(node.right, sb);
	    }
	}
}

class Node {
	int number;
	String word;

	Node left;
	Node right;

	Node(int number, String word) {
		this.number = number;
		this.word = word;
	}
}
