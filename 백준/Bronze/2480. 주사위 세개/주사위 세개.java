import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int c = scanner.nextInt();
        int max = Math.max(x,y);
        if (max <c){
            max = c;
        }
        if(x ==y && y==c){
            System.out.println(10000+ x*1000);
        }
        else if (x==y && y!=c){
            System.out.println(1000+x*100);
        }
        else if (x!=y && y==c){
            System.out.println(1000+y*100);
        }
        else if( x==c && y!=c) {
            System.out.println(1000 + c * 100);
        }
        else System.out.println(max*100);
    }
}
