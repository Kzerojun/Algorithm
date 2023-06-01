import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> positive= new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int sum =0;

        for(int i = 0; i<N; i++){
            int k = Integer.parseInt(br.readLine());
            if(k<=0) negative.add(k);
            else positive.add(k);
        }

        Collections.sort(positive,Collections.reverseOrder());
        Collections.sort(negative);

        for(int i = 0; i<positive.size(); i+=2){
            if(i+1<positive.size()){
                if(positive.get(i)==1 || positive.get(i+1)==1){
                    sum += positive.get(i);
                    sum += positive.get(i+1);
                    continue;
                }
                else sum+= positive.get(i)*positive.get(i+1);
            }
            else{
                if(positive.get(i) ==1){
                    sum += positive.get(i);
                    continue;
                }
                else sum +=positive.get(i);
            }
        }

        for(int i = 0; i<negative.size(); i+=2){
            if(i+1<negative.size())
                sum += negative.get(i)*negative.get(i+1);

            else sum+=negative.get(i);
        }
        System.out.println(sum);
    }
}