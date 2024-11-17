
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		
		while((input = br.readLine())!=null) {
			int N = Integer.parseInt(input);

			Trie trie = new Trie();
			List<String> strs = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				String str = br.readLine();

				trie.insert(str);
				strs.add(str);
			}

			int sum = 0;
			for (int i = 0; i < N; i++) {
				int sol = trie.sol(strs.get(i));
				sum += sol;
			}

			float result = (float)sum / N;
			System.out.printf("%.2f \n", result);
		}

	}

}

class Trie {
	final TrieNode root;

	Trie() {
		root = new TrieNode();
	}

	public void insert(String str) {
		TrieNode current = root;

		for (char ch : str.toCharArray()) {
			current.child.putIfAbsent(ch, new TrieNode());
			current = current.child.get(ch);
		}

		current.isTerminal = true;
	}

	public int sol(String str) {
		int count = 1;

		TrieNode current = root.child.get(str.charAt(0));

		for (int i = 1; i < str.length(); i++) {
			if (current.isTerminal || current.child.size() > 1) {
				count++;
			}

			current = current.child.get(str.charAt(i));
		}
		return count;
	}
}

class TrieNode {
	boolean isTerminal;
	Map<Character, TrieNode> child;

	TrieNode() {
		this.isTerminal = false;
		this.child = new TreeMap<>();
	}
}