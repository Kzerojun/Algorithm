import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[8];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 8; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        boolean asc = true;
        boolean dsc = true;
        for (int i = 0; i < 7; i++) {

            if (arr[i+1] > arr[i]) dsc = false;

            if (arr[i+1] < arr[i]) asc = false;
        }
        if (asc) {
            System.out.print("ascending");
        } else if (dsc) {
            System.out.print("descending");
        } else {

            System.out.print("mixed");
        }
    }
}