import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
      
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();	
        StringBuilder sb = new StringBuilder();		
       
        for(int i=0;i<input.length();i++){
            char temp = input.charAt(i);
            if(temp >= 65 && temp <=90)		
                sb.append((char) (temp + 32));	
            else		
                sb.append((char) (temp - 32));	
        }
        bw.write(sb.toString());		
        bw.flush();		
        bw.close();
        br.close();
    }
}