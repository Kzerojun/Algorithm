import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static Map<String, Integer> places;
	static Map<String, int[]> placeTime;
	static List<Information> informations;
	static Set<String> students;
	
	
	public static void main(String[] args) throws IOException {
		init();
		play();
	}
	
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		places = new TreeMap<>();
		students = new TreeSet<>();
		placeTime = new TreeMap<>();
		informations = new LinkedList<>();
		
		for(int i = 0 ; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String student = st.nextToken();
			String place = st.nextToken();
			
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			
			if(students.contains(student)) continue;
			
			students.add(student);
			places.put(place, places.getOrDefault(place, 0)+1);
			int[] time = placeTime.getOrDefault(place, new int[50001]);
			
			time[startTime]++;
			time[endTime]--;
			placeTime.put(place, time);
		}
	}
	
	private static void play() {
		
		
		for(String key : placeTime.keySet()) {
			
			int[] time = placeTime.get(key);
			
			int startTime = -1;
			int endTime = -1;
			int maxTime = -1;
			
			for(int i = 1; i<=50_000; i++) {
				time[i] = time[i]+time[i-1];
			}
			
			for(int i = 1 ; i<=50_000; i++) {
				if(time[i]>maxTime) {
					maxTime = time[i];
					startTime = i;
					
					while(true) {
						if(time[i] == maxTime) {
							endTime = i;
							i++;
						}else {
							i--;
							break;
						}
					}
				}
				

				
			}

			
			informations.add(new Information(key,startTime,endTime,maxTime));	
		}
		
		informations.sort((o1,o2)->{
			if(o1.size == o2.size) {
				return o1.place.compareTo(o2.place);
			}
			return o2.size - o1.size;
		});
	
		
		
		Information result = informations.get(0);
		
		System.out.println(result.place + " "+ result.startTime + " "+ (result.endTime+1));
		
	}
	
}

class Information {
	String place;
	int startTime;
	int endTime;
	int size;
	
	Information(String place, int startTime, int endTime, int size) {
		this.place = place;
		this.startTime = startTime;
		this.endTime = endTime;
		this.size = size;
	}
}