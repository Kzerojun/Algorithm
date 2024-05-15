import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static long[] arr;
	static int N;
	static long solution = 0 ;

	public static void main(String[] args) throws IOException {
		init();
		mergeSort(0,N-1);
		System.out.println(solution);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
	}

	private static void mergeSort(int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;

			//Divide
			mergeSort(left, mid);
			mergeSort(mid + 1, right);

			//Combine
			merge(left, mid, right);
		}
	}

	private static void merge(int left, int mid, int right) {
		long[] tmp = new long[right - left + 1];
		int i = left;
		int j = mid +1;
		int k = 0;

		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				tmp[k++] = arr[i++];
			}else {
				solution += (mid - i + 1);
				tmp[k++] = arr[j++];
			}
		}

		while (i <= mid) {
			tmp[k++] = arr[i++];
		}

		while (j <= right) {
			tmp[k++] = arr[j++];
		}

		for (i = left, k = 0; i <= right; i++, k++) {
			arr[i] = tmp[k];
		}
	}




}