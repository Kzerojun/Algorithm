import java.util.Scanner;


public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = B;
        System.out.println(A*(B%10));
        B = B/10;
        System.out.println(A*(B%10));
        B = B/10;
        System.out.println(A*(B%10));
        System.out.println(A*C);
    }
}