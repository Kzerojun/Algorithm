import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main{
    public static void main(String[] args)throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] alphabet = new int[26];
    String word = br.readLine().toUpperCase();

    for(int i  =0; i<word.length(); i++){
        int num = word.charAt(i) - 'A';
        alphabet[num]++;
    }
    int max = -1;
    char ch = '?';
    for( int j =0; j<alphabet.length;j++){
        if(max < alphabet[j]){
            max = alphabet[j];
            ch = (char)(j+'A');
        }
        else if (alphabet[j]==max){
            ch ='?';
        }
    }
    bw.write(ch+"");
    br.close();
    bw.flush();
    bw.close();

    }
}