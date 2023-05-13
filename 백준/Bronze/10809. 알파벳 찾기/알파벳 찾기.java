import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[26];
        for(int i = 0; i<arr.length; i++){
            arr[i] = -1;
        }
        String s = br.readLine();

        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(arr[ch -'a']== -1){
                arr[ch - 'a'] = i;
            }

        }
        for(int val : arr) {	// 배열 출력
            System.out.print(val + " ");
        }
        br.close();

    }
}