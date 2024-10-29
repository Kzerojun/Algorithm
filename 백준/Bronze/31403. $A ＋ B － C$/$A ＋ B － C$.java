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

		String A = br.readLine();
		String B = br.readLine();
		String C = br.readLine();

		int sum = Integer.parseInt(A)+Integer.parseInt(B)-Integer.parseInt(C);

		String contact = A+B;

		int result2 = Integer.parseInt(contact)- Integer.parseInt(C);

		System.out.println(sum);
		System.out.println(result2);

	}
}