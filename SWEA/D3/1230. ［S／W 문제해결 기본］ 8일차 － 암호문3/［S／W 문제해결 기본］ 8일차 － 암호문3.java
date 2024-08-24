
import java.util.*;
import java.io.*;

public class Solution {
	static BufferedReader br;
	static int N;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private static void start() throws IOException {
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			LinkedList lk = new LinkedList();
			
			for(int i = 0; i<N; i++) {
				int number = Integer.parseInt(st.nextToken());
				lk.add(number);
			}
			
			int commandCount = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0 ; i<commandCount; i++) {
				String command = st.nextToken();
				
				if(command.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					
					List<Integer> s = new ArrayList<>();
					
					for(int j = 0; j<y; j++) {
						s.add(Integer.parseInt(st.nextToken()));
					}
					
					lk.insert(x, y, s);
				}
				
				if(command.equals("D")) {
					int x= Integer.parseInt(st.nextToken());
					int y= Integer.parseInt(st.nextToken());
						
					lk.delete(x, y);
				}
				
				if(command.equals("A")) {
					int y =Integer.parseInt(st.nextToken());
					for(int j = 0 ; j<y; j++) {
						lk.add(Integer.parseInt(st.nextToken()));
					}
				}
			}
			
			List<Integer> result = lk.sol();
			
			System.out.print("#"+t+" ");
			for(int tmp : result) {
				System.out.print(tmp+" ");
			}
			System.out.println();


		}
	}

}

class Node{
	int data;
	Node next;
	
	Node(int data) {
		this.data = data;
		this.next = null;;
	}
}

class LinkedList {
	Node head;
	Node tail;
	
	LinkedList() {
		this.head = null;
		this.tail = null;
	}
	

	public void add(int data) {
		Node newNode = new Node(data);
		
		if(head == null) {
			head = newNode;
			tail = newNode;
		}else {
			tail.next= newNode;
			tail = newNode;
		}
	}
	
	public void insert(int x, int y, List<Integer> s) {
		Node now = head;
		Node prev = null;
		
		for(int i = 0 ; i<x; i++) {
			prev = now;
			now = now.next;
		}
		
		Node first = null;
		Node last = null;
		
		for(int num : s) {
			Node newNode = new Node(num);
			
			if(first == null) {
				first = newNode;
				last = newNode;
			}else {
				last.next = newNode;
				last = newNode;
			}
		}
		
		if(prev == null) {
			last.next = head;
			head = first;
		}else {
			last.next = prev.next;
			prev.next = first;
		}
		
		if(now == null) {
			tail = last;
		}
		
		
	}
	
    public void delete(int x, int y) {
        Node now = head;
        Node prev = null;
        

        for(int i = 0; i < x; i++) {
            prev = now;
            now = now.next;
        }
        
  
        for(int i = 0; i < y; i++) {
            now = now.next;
        }
        
        if(prev == null) {
            head = now;
        } else {
            prev.next = now;
        }
        
        // tail 업데이트
        if(now == null) {
            tail = prev;
        }
    }
	
	public List<Integer> sol() {
		Node now = this.head;
		List<Integer> result = new ArrayList<>();
		
		for(int i = 0 ; i<10; i++) {
			result.add(now.data);
			now = now.next;
		}
		
		return result;
	}
	
	
}

