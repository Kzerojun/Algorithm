import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> index = new HashMap<>();
        int[][] data = new int[friends.length][friends.length];
        
        for(int i = 0; i<friends.length; i++) {
            index.put(friends[i],i);
        }
        
        for(int i = 0; i<gifts.length; i++){
            String[] s = gifts[i].split(" ");
            String give = s[0];
            String receive = s[1];
            data[index.get(give)][index.get(receive)]++;
        }
        
        int[] count = new int[friends.length];
        
        for(int i = 0 ; i<friends.length; i++){
            int give = 0;
            int receive = 0;
            for(int j = 0 ; j<friends.length; j++){
                if(data[i][j]!=0){
                    give += data[i][j];
                }
                if(data[j][i]!=0){
                    receive +=data[j][i];
                }
            }
            count[i] = give - receive;
        }
        
    
        
        int[] result = new int[friends.length];
        
        for(int i = 0 ; i< friends.length; i++){
            for(int j =0; j< friends.length; j++){
                if(data[i][j]!=0 || data[j][i]!=0){ //주고받은 기록이 존재하다
                
                    if(data[i][j]>data[j][i]){
                        result[i]++;
                        continue;
                    }
                    
                    if(data[i][j]==data[j][i]){
                        if(count[i]>count[j]){
                        result[i]++;
                        continue;
                        }
                    }
                }
                if(data[i][j]==0 && data[j][i]==0){
                    if(count[i]>count[j]){
                        result[i]++;
                    }
                }
            }
        }
        int sol = Integer.MIN_VALUE;
        for(int x : result){
            sol = Math.max(x,sol);
            System.out.println(x);
        }
        return sol;
        
        

    }
}
