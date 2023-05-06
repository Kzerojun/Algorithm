import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int X = scanner.nextInt();
    int N = scanner.nextInt();
    int sum = 0;
    for(int i =0; i< N; i++)
    {
        int item1 = scanner.nextInt();
        int number = scanner.nextInt();
        sum = sum + (item1*number);
    }
    if(sum == X) System.out.println("Yes");
    else System.out.println("No");
    }
}
