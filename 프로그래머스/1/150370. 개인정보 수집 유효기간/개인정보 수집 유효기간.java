import java.util.*;

class Solution {
    static int year, month, date;
    static List<Integer> result;

    public int[] solution(String today, String[] terms, String[] privacies) {
        String[] tmp = today.split("\\.");
        
        year = Integer.parseInt(tmp[0]);
        month = Integer.parseInt(tmp[1]);
        date = Integer.parseInt(tmp[2]);
        result = new ArrayList<>();
        
        Map<String, Integer> values = new HashMap<>();
        
        for (String term : terms) {
            String[] tmp2 = term.split(" ");
            values.put(tmp2[0], Integer.parseInt(tmp2[1]));
        }
        
        for (int i = 0; i < privacies.length; i++) {
            String[] tmp3 = privacies[i].split("[. ]");
            
            int calYear = Integer.parseInt(tmp3[0]);
            int calMonth = Integer.parseInt(tmp3[1]);
            int calDate = Integer.parseInt(tmp3[2]);
            String key = tmp3[3];
            
            int presentDate = (year - calYear)*12*28 + (month - calMonth)*28 + (date-calDate);  
            System.out.println(presentDate);
            if(values.get(key)*28<=presentDate){
                result.add(i+1);
            }
            

        }
        
        return result.stream().mapToInt(i -> i).toArray(); // ArrayList를 배열로 변환
    }
}
