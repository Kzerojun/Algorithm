import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	public static void main(String[] args) throws IOException {

		start();
	}

	public static void start() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			LinkedList linkedList = new LinkedList();

			for (int i = 0; i <N; i++) {
				int number = Integer.parseInt(st.nextToken());
				linkedList.push(number);
			}

			int commandCount = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < commandCount; i++) {
				String command = st.nextToken();
				if (command.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());

					LinkedList newLinkedList = new LinkedList();

					for (int j = 0; j < y; j++) {
						newLinkedList.push(Integer.parseInt(st.nextToken()));
					}
					linkedList.insert(x,y,newLinkedList);
				}

				if (command.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					linkedList.delete(x,y);
				}

				if (command.equals("A")) {
					int y = Integer.parseInt(st.nextToken());
					for (int k = 0; k < y; k++) {
						linkedList.push(Integer.parseInt(st.nextToken()));
					}
				}

			}

			linkedList.printSolution(tc,10);

		}
	}
}

class Node {
	int data;
	Node next;

	Node() {

	}
	Node(int data) {
		this.data = data;
	}
}

class LinkedList {

	Node head;
	Node tail;

	int size = 0;

	LinkedList() {
		head = new Node();
		tail = head;
	}


	public void addLast(int data) {
		Node newNode = new Node(data);
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	public void push(int data) {
		Node newNode = new Node(data);
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	public Node get(int index) {
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	public void insert(int x, int y, LinkedList s) {
		size += y;
		Node node = this.get(x);
		s.tail.next = node.next;
		node.next = s.head.next;
		if (size == x) {
			tail = s.tail;
		}


	}

	public void delete(int x, int y) {
		Node node = this.get(x);
		Node prev = this.get(x-1);

		for (int i = 0; i < y; i++) {
			node = node.next;
		}

		prev.next = node;
	}

	public void printSolution(int tc, int index) {
		Node node = head.next;

		System.out.print("#"+tc);
		for (int i = 0; i < index; i++) {
			System.out.print(" "+node.data+" ");
			node = node.next;
		}
		System.out.println();
	}





}


