import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static Map<String,Integer> results;
	static List<String> files;
	static List<Result> sol;
	public static void main(String[] args) throws IOException {
		init();
		play();
		print();
	}
	
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		results = new TreeMap<>();
		files = new ArrayList<>();
		sol = new ArrayList<>();
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i<N; i++) {
			files.add(br.readLine());
		}
	}
	
	private static void play() {
		for(String file : files) {
			int index = file.indexOf(".");
			String extension = file.substring(index+1,file.length());
			results.put(extension, results.getOrDefault(extension, 0)+1);
		}
	}
	
	private static void print() {
		for(Map.Entry<String, Integer> file : results.entrySet()) {
			String key = file.getKey();
			int value = file.getValue();
			
			sol.add(new Result(key,value));
		}
		
		sol.sort((o1,o2)->{
			return o1.extension.compareTo(o2.extension);
		});
		
		for(Result result : sol) {
			System.out.println(result.extension +" " + result.count);
		}
	}
}
class Result {
	String extension;
	int count;
	
	Result(String extension, int count) {
		this.extension = extension;
		this.count = count;
	}
}