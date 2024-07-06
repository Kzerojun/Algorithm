import java.util.*;

class Solution {
    static Map<String,List<Integer>> map;
    static int[] sol;
    static int index = 0;

    public int[] solution(String[] info, String[] query) {
        init(info);
        sol = new int[query.length];
        calculate(query);
        return sol;
    }

    private static void calculate(String[] query) {
        for (String str : query) {
            String[] tmp = str.replaceAll(" and ", " ").split(" ");
            String lq = tmp[0];
            String oq = tmp[1];
            String cq = tmp[2];
            String fq = tmp[3];
            int sq = Integer.parseInt(tmp[4]);
            int solCount = query(lq, oq, cq, fq, sq);
            sol[index++] = solCount;
        }
    }

    private static int query(String lq, String oq, String cq, String fq, int sq) {
        String nativeQuery = ""+lq+oq+cq+fq;
 
        int score = sq;
        
         List<Integer> value = map.getOrDefault(nativeQuery,new ArrayList<>());
        
        int left = 0;
        int right = value.size();
        
        while(left<right) {
            int mid = left +(right -left) /2;
            
            if(value.get(mid)<score) {
                left = mid+1;
            }else {
                right = mid;
            }
        }
        
        int sol = value.size() - left;
        return sol;
    }

    

    private static void init(String[] info) {

        map = new TreeMap<>();
        for (String information : info) {
            String[] tmp = information.split(" ");
            String language = tmp[0];
            String occupation = tmp[1];
            String career = tmp[2];
            String food = tmp[3];
            int score = Integer.parseInt(tmp[4]);
            makeType(tmp,"",0,score);
        }
        
        for(String key : map.keySet()) {
            List<Integer> value = map.get(key);
            value.sort((o1,o2)->{
                return o1 - o2;
            });
        }

    }
    
    private static void makeType(String[] tmp,String str,int index,int score) {
        if(index == 4) {
            List<Integer> value = map.getOrDefault(str,new ArrayList<>());
            value.add(score);
            map.put(str,value);
            return;
        }
        
        makeType(tmp,str + tmp[index],index+1,score);
        makeType(tmp,str + "-",index+1,score);
    }
    
}

class Applicant {
    String language;
    String occupation;
    String career;
    String food;
    int score;

    Applicant(String language, String occupation, String career, String food, int score) {
        this.language = language;
        this.occupation = occupation;
        this.career = career;
        this.food = food;
        this.score = score;
    }
}
