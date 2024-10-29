import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{

	public static void main(String[] args) throws IOException {
		simulate();
	}

	private static void simulate() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String star = "*";
		for (int i = 1; i <= N; i++) {
			String format = "%"+N+"s";
			System.out.println(String.format(format,star.repeat(i)));
		}
	}
}