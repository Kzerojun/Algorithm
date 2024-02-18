import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        // n 진법, t 미리 구할 숫자의 객수, 게임참여인원 m, 튜브의 순서
        StringBuilder sb = new StringBuilder();
        List<Character> list = calculate(n, t);

        int count = 0;
        for (int i = p - 1; i < list.size(); i += m) {
            sb.append(list.get(i));
            count++;
            if(count==t) {
                break;
            }
        }

        return sb.toString();
    }

    static List<Character> calculate(int n, int t) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            String string = "";

            int number = i;
            
            while (true) {
                int remainder = number % n;
                String word;
                if (remainder >= 10) {
                    word = String.valueOf((char) ('A' + remainder - 10));
                } else {
                    word = String.valueOf(remainder);
                }

                string = word + string;
                number = number / n;

                if (number == 0) {
                    break;
                }
            }

            for (int j = 0; j < string.length(); j++) {
                list.add(string.charAt(j));
            }
        }
        return list;
    }
}
