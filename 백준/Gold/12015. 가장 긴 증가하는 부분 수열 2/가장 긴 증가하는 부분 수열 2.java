import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static List<Integer> lists;
	static int[] arr;

	public static void main(String[] args) throws IOException{
		init();
		calculate();
		print();
	}

	private static void init()throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		lists = new ArrayList<>();
	}

	private static void calculate() {
		for (int i = 0; i < N; i++) {
			if (lists.isEmpty()) {
				lists.add(arr[i]);
			}else {
				if (lists.get(lists.size() - 1) < arr[i]) {
					lists.add(arr[i]);
				}else {
					int index = find(0, lists.size() - 1, arr[i]);
					lists.set(index, arr[i]);
				}
			}
		}
	}

	private static int find(int left, int right, int key) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (lists.get(mid) < key) {
				left = mid + 1;
			}else {
				right = mid;
			}
		}
		return right;
	}

	private static void print() {
		System.out.println(lists.size());
	}

}
