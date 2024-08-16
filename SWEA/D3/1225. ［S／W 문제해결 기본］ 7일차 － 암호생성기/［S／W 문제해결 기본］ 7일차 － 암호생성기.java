import java.util.*;
import java.io.*;

public class Solution {

	private static int T;
	private static BufferedReader br;

	public static void main(String[] args) throws IOException {
		init();
		start();
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	private static void start() throws IOException{
		for(int t = 1; t<=10; t++) {
			br.readLine();
			Queue<Integer> q = new LinkedList<>();
			initGame(q);
			playGame(q);
			
			System.out.print("#"+t+" ");
			
			while(!q.isEmpty()) {
				System.out.print(q.poll()+" ");
			}
			System.out.println();
		}
	}

	private static void initGame(Queue<Integer> q) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 8; i++) {
			int number = Integer.parseInt(st.nextToken());
			q.add(number);
		}
	}

	private static void playGame(Queue<Integer> q) {
		int count = 1;
		
		while (true) {
			int number = q.poll();
			number = number - count;
			count++;
			
			if(count ==6) {
				count = 1;
			}
			if (number <= 0) {
				number = 0;
				q.add(number);
				break;
			}
			q.add(number);
		}
	}

}
