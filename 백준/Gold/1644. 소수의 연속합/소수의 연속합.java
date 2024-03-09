import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		boolean[] decimal = new boolean[N+1];
		List<Integer> decimalList = new ArrayList<>();

		Arrays.fill(decimal, true);

		decimal[0] = false;
		decimal[1] = false;

		for (int i = 2; i <= Math.sqrt(N); i++) {
			if(decimal[i]) {
				for (int j = i * i; j <= N; j += i) {
					decimal[j] = false;
				}
			}
		}

		for (int i = 0; i < decimal.length; i++) {
			if(decimal[i]) {
				decimalList.add(i);
			}
		}

		int left = 0;
		int right =0;
		int sum = 0;
		int solution = 0;

		while(true) {
			if(sum >= N ) sum -= decimalList.get(left++);
			else if(right == decimalList.size()) break;
			else sum += decimalList.get(right++);
			if(N==sum) solution++;
		}

		System.out.println(solution);
	}
}