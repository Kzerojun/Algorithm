import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static Map<Character, Integer> maps;
	static String word;
	static int max;


	public static void main(String[] args) throws Exception {
		init();
		char result = simulate();
		printResult(result);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		maps = new HashMap<>();
		word = br.readLine();

		max = Integer.MIN_VALUE;
	}

	private static char simulate() {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);

			char upperCase = Character.toUpperCase(ch);
			maps.put(upperCase, maps.getOrDefault(upperCase, 0) + 1);
			max = Math.max(max, maps.get(upperCase));
		}

		boolean check = false;
		char result =  ' ';
		for (char key : maps.keySet()) {
			int value = maps.get(key);

			if (value == max) {
				if (check) {
					result = '?';
					return result;
				}
				result = key;
				check = true;
			}
		}

		return result;
	}


	private static void printResult(char result) throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(result + " ");
		bw.flush();
		bw.close();
	}

}
