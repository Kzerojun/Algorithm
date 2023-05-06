import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        if (x == 0 && y - 45 < 0) {
            x = 23;
            y = y + 15;
        } else if (y - 45 < 0) {
            x = x - 1;
            y = y + 15;
        }
        else{
            y= y-45;
        }
        System.out.printf("%d %d",x,y);
    }
}
