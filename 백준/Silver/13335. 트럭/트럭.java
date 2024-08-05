import java.util.*;
import java.io.*;


class Main{
	static int N,W,L;
	static List<Integer> trucks;
	
	public static void main(String[]args) throws IOException {
		init();
		play();
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		trucks = new ArrayList<>();
		
		//트럭의 개수
		N = Integer.parseInt(st.nextToken());
		
		//다리의 길이
		W = Integer.parseInt(st.nextToken());
		
		//다리의 최대하중
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<N; i++) {
			trucks.add(Integer.parseInt(st.nextToken()));
		}
		
	}
	
	private static void play() {
		Queue<Truck> q = new LinkedList<>();
		int result = 0;
		
		for(int weight : trucks) {
			while(true) {
				result++;
				if(canLoad(q,weight)) {
					q.add(new Truck(0,weight));
					moveTruck(q);
					break;
				}else {
					moveTruck(q);
				}
				
				
			}
			
		}
		
		while(!q.isEmpty()) {
			moveTruck(q);
			result++;
		}
		
		System.out.println(result+1);
	}
	
	private static boolean canLoad(Queue<Truck> q,int nextWeight) {
		int weight = 0;
		
		if(q.size()>=W) {
			return false;
		}
		
		for(Truck truck : q) {
			weight += truck.weight;
		}
		
		if(weight+nextWeight>L) return false;
		
		return true;
	}
	
	private static void moveTruck(Queue<Truck> q) {
		int size = q.size();
		
		for(int i = 0 ; i<size; i++) {
			Truck truck = q.poll();
			truck.time++;
			if(truck.time == W) {
				continue;
			}
			q.add(truck);
		}
		
	}
	
}
class Truck{
	int time;
	int weight;
	
	Truck(int time, int weight) {
		this.time = time;
		this.weight = weight;
	}
}