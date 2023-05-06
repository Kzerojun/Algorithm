import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();
        String x = "long";
        if (X >4)
        {
            int N = X /4;
            for(int i = 0; i<N-1; i++)
            {
                x = x + " long";
            }
            System.out.println(x+" " +"int");
        }
        else System.out.println("long int");

    }
}