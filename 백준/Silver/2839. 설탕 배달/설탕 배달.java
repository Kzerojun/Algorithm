import java.io.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int count =0;
        while(true){
                if( N==3 || N == 6 || N ==9  || N ==12){
                    count = count+ N/3;
                    bw.write(count +"");
                    break;
                }
                else if( N==5){
                    count++;
                    bw.write(count +"");
                    break;
                }
                else if( N<5){
                    bw.write("-1");
                    break;
                }
            N = N -5;
            count++;
        }

        br.close();
        bw.flush();
        bw.close();


    }

}