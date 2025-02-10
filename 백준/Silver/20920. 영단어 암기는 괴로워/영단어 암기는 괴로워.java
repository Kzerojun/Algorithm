import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	static Map<String, Integer> maps;
	static int N, M;


	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		maps = new HashMap<>();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String word = br.readLine();

			if (word.length() >= M) {
				maps.put(word, maps.getOrDefault(word, 0) + 1);
			}
		}

		PriorityQueue<Word> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.count == o2.count) {
				if (o1.word.length() == o2.word.length()) {
					return o1.word.compareTo(o2.word);
				}
				return o2.word.length() - o1.word.length();
			}
			return o2.count - o1.count;
		});

		for (String key : maps.keySet()) {
			pq.add(new Word(key, maps.get(key)));
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (!pq.isEmpty()) {
			bw.write(pq.poll().word+"\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}


}

class Word {

	String word;
	int count;

	public Word(String word, int count) {
		this.word = word;
		this.count = count;
	}
}