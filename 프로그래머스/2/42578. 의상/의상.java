import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String,Integer> types = new HashMap<>();
        
        for(String[] cloth : clothes) {
            types.put(cloth[1],types.getOrDefault(cloth[1],0)+1);
        }
        
        int result = 1;
        
        for(int value : types.values()) {
            result *= (value+1); // 각 종류별로 '안 입는 경우'를 포함
        }
        
        return result - 1; // 아무것도 선택하지 않는 경우를 제외
    }
}
