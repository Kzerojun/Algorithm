import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine());
            String[] scores = br.readLine().split(" ");
            HashSet<Integer> set = new HashSet<>();
            set.add(0);
            for(int i=0; i<N; i++){
                HashSet<Integer> tmpSet = new HashSet<>();
                int score = Integer.parseInt(scores[i]);
                for(int num : set){
                    tmpSet.add(num+score);
                }
                set.addAll(tmpSet);
            }
            System.out.println("#"+tc+" "+set.size());
        }
    }
}
