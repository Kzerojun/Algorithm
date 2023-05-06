import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int i ;
    int sum = 0;
    for( i =0; i<N+1; i++){
        sum +=i;

    }
    System.out.println(sum);


    }
}
