import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] city;
    public static void main(String[]args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        city = new int[n+1][n+1];

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
               city[i][j] = 9900001;
               if(i==j){
                   city[i][j] = 0;
               }
            }
        }

        for(int i = 0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            city[a][b] = Math.min(city[a][b],c);
        }




        for(int k = 1; k<=n; k++){
            for(int i = 1; i<=n; i++){
                for(int j = 1; j<=n; j++){
                    if(city[i][k] + city[k][j] < city[i][j]){
                        city[i][j] = city[i][k]+city[k][j];
                    }
                }
            }
        }

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(city[i][j]==9900001){
                    city[i][j] =0;
                }
                System.out.print(city[i][j]+" ");
            }
            System.out.println();
        }
    }


}
