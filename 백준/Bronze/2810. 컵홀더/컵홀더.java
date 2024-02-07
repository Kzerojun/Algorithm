import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split("");
        int count = 1;

        for (int i = 0; i < num; i++) {
            if(s[i].equals("S")) count++;
            else{
                i++;
                count++;
            }
        }

        System.out.println(num> count? count: num);

    }
}