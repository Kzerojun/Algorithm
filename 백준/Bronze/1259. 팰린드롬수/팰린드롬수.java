import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String input = br.readLine();
            if(Integer.valueOf(input) == 0) break;

            String solve = solve(input);
            System.out.println(solve);
        }
    }
    private static String solve(String input){
        StringBuffer sb = new StringBuffer(input);
        String str = sb.reverse().toString();
        return input.equals(str) ? "yes" : "no";
    }
}