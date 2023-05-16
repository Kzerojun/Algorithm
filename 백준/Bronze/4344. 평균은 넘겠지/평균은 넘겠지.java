import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int num = Integer.parseInt(br.readLine());
    for(int i =0; i < num; i++){
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        double avg = 0;
        double count =0;
        for(int j =0; j<N; j++){
            arr[j] = Integer.parseInt(st.nextToken());
            avg = avg+arr[j];
        }
        avg = avg/N;
        for( int j =0; j<N; j++){
            if(arr[j]>avg){
                count++;
            }
        }

        bw.write(String.format("%.3f",count/N*100));
        bw.write("%");
        bw.newLine();
    }
    br.close();
    bw.flush();
    bw.close();
    }
}