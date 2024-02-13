import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {

 public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  
  int n = Integer.parseInt(br.readLine());
  
  String [][] word = new String [n][2];
  
  for(int i = 0; i < n; i++)
   word[i] = br.readLine().split(" ");
  
  for(int i = 0; i < n; i++){
   bw.write("Distances: ");
   for(int j = 0; j < word[i][0].length(); j++){
    if(word[i][1].charAt(j) - word[i][0].charAt(j) >= 0) 
     bw.write(String.valueOf((word[i][1].charAt(j) - word[i][0].charAt(j))) + " "); 
    else             
     bw.write(String.valueOf(26 + (word[i][1].charAt(j) - word[i][0].charAt(j))) + " "); 
   }
   bw.write("\n");
  }
  bw.flush();
 }
}

