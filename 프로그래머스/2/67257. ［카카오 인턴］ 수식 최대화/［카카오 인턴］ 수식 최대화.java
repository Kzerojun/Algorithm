import java.util.*;

class Solution {
    static String[] EXPRESSIONS = {"*","-","+"};
    static List<String> combinations;
    static List<String> divided;
    static List<Long> numbers;
    static List<String> copy;
    static long sol = Long.MIN_VALUE;
    
    public long solution(String expression) {
        init();
        permutation("",new boolean[3]);
        divide(expression);
        play();
        return sol;
    }
    
    private static void init() {
        combinations = new ArrayList<>();
        divided = new ArrayList<>();
        numbers = new ArrayList<>();
    }
    
    private static void permutation(String str,boolean[] check) {
        if(str.length()== 3) {
            combinations.add(str);
            return;
        }
        for(int i = 0 ; i < 3; i++) {
            if(!check[i]) {
                check[i] = true;
                permutation(str+EXPRESSIONS[i],check);
                check[i] = false;
            }
        }
        
    }
    
    private static void play() {
        for(String combination : combinations) {
            String[] str = combination.split("");
            String first = str[0];
            String second = str[1];
            String third = str[2];
            
            copy = new ArrayList<>();
            
            for (String item : divided) {
                copy.add(item);
            }
            // for (String item : copy) {
            //     System.out.println(item);
            // }
            
            calculate(first);
            calculate(second);
            calculate(third);
            
            long sum = 0;
            
            for(String tmp : copy) {
                sum += Long.parseLong(tmp);
            }

            sol = Math.max(sol,Math.abs(sum));
            
        }
    }
    
    private static void calculate(String ex){
        List<String> list = new ArrayList<>();

        for(int i = 0 ; i<copy.size(); i++) {
            String tmp = copy.get(i);
            // System.out.println(tmp);
            
            if(tmp.equals(ex)) {
                list.remove(list.size()-1);
                if(tmp.equals("-")) {
                    Long before = Long.parseLong(copy.get(i-1));
                    Long after= Long.parseLong(copy.get(i+1));
                    long tmpResult = before - after;
                    
                    list.add(String.valueOf(tmpResult));
                    copy.set(i+1,String.valueOf(tmpResult));
                    i++;
                    continue;
                }
                if(tmp.equals("+")) {
                    long before = Long.parseLong(copy.get(i-1));
                    long after= Long.parseLong(copy.get(i+1));
                    long tmpResult = before + after;
                    copy.set(i+1,String.valueOf(tmpResult));
                    list.add(String.valueOf(tmpResult));
                    i++;
                    continue;
                }
                
                if(tmp.equals("*")) {
                    long before = Long.parseLong(copy.get(i-1));
                    long after= Long.parseLong(copy.get(i+1));
                    long tmpResult = before * after;
                    copy.set(i+1,String.valueOf(tmpResult));
                    list.add(String.valueOf(tmpResult));
                    i++;
                }
            }else {
                list.add(tmp);
            }
        }
        
//         System.out.println("바뀐 리스트");
//         for(String str : list) {
//             System.out.println(str);
//         }
//           System.out.println("");
        copy = list;
        
        
    }
    
    private static void divide(String expression) {
        StringBuilder sb = new StringBuilder();
        char[] ch = expression.toCharArray();
        
        for(int i = 0 ; i<expression.length(); i++) {
            if(Character.isDigit(ch[i])) {
                sb.append(String.valueOf(ch[i]));
            }else {
                if(sb.length()>0) {
                    divided.add(sb.toString());
                    sb = new StringBuilder();
                }
                divided.add(String.valueOf(ch[i]));
            }
        }
        
        if(sb.length()>0) {
            divided.add(sb.toString());
        }
    }
}