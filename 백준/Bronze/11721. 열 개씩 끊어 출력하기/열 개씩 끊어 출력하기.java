import java.util.Scanner;
import java.io.*;

public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		for (int i = 0; i < input.length(); ++i) {
			System.out.print(input.charAt(i));
			if (i % 10 == 9) {
				System.out.println();
			}
		}
	}
}