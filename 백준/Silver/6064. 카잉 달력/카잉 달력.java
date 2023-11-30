import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main{
    static int sol;
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sol = -1;
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int count = 0;
            int lcm = M * N / GCD(M, N);
            while(count*M<lcm){
                if((count*M+x-y)%N==0){
                    sol = count*M+x;
                    break;
                }
                count++;
            }
            System.out.println(sol);
        }
    }

    static int GCD(int n1, int n2) {
        if(n2==0){
            return n1;
        }

        return GCD(n2, n1 % n2);
    }




}
