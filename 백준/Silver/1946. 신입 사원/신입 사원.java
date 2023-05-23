import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int num1 = Integer.parseInt(br.readLine());
            int[][] arr1 = new int[num1][2];
            int count = 1;

            for (int i = 0; i < num1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                arr1[i][0] = Integer.parseInt(st.nextToken());
                arr1[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr1, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[0],o2[0]);
                }
            });

            int grade = arr1[0][1];

            for(int i =1; i< num1; i++){
                if(arr1[i][1]<grade){
                    count++;
                    grade = arr1[i][1];
                }
            }

            System.out.println(count);
        }
    }
}