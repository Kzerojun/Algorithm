import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		int[] number = new int[9];

		for (int i = 0; i < 9; i++) {
			number[i] = Integer.parseInt(br.readLine());
		}

		int solIndex = -1;
		int solMax = Integer.MIN_VALUE;

		for (int i = 0; i < 9; i++) {
			if (solMax < number[i]) {
				solMax = number[i];
				solIndex = i;
			}
		}

		System.out.println(solMax);
		System.out.println(solIndex+1);
	}
}