import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		char result;
		for(int i=0; i<str.length(); i++) { 
			if(str.charAt(i) <= 'C') 
				result = (char)(str.charAt(i) + 23); 
			else 
				result = (char)(str.charAt(i) - 3);
			System.out.print(result);
		}
		scan.close();
	}

}