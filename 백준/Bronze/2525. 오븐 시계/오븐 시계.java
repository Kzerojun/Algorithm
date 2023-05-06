import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int c = scanner.nextInt();
        if(y+c>=60){
            x= x+(y+c)/60;
            y= (y+c)%60;
            if(x>=24){
                x= x-24;
            }
        }
        else y= y+c;
        System.out.printf("%d %d",x,y);
    }
}
