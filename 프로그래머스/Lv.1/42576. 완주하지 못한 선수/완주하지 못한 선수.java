import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String,Integer> map = new HashMap<>();
        
        for(String string : participant) {
            map.put(string,map.getOrDefault(string,0)+1);
        }
        
        for(String string : completion) {
            map.put(string,map.get(string)-1);
        }
        
        for(String key : map.keySet()) {
            if(map.get(key)!=0) {
                return key;
            }
        }
        return "";
    }
}