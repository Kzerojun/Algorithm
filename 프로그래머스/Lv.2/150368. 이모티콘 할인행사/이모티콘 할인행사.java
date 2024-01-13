import java.util.*;

class Solution {
    static int[] discountPercent = {10,20,30,40};
    static int[] solution;
    static List<User> userList;
    
    public int[] solution(int[][] users, int[] emoticons) {
        solution = new int[2]; // 0은 이모티콘 플러스 서비스 가입자 최대 1은 이모티콘 판매액 최대
        userList = new ArrayList<>();
        
        for(int i = 0 ; i<users.length; i++) {
            userList.add(new User(users[i][0],users[i][1]));
        }
        
        discountCombination(emoticons,0,new LinkedList<>());
        return solution;
        
    }
    static void discountCombination(int[]emoticons,int count,List<Integer> discount) {
        if(count == emoticons.length) {
            calculate(discount,emoticons);
            return;
        }
        
        for(int i = 0; i < discountPercent.length; i++) {
            discount.add(discountPercent[i]);
            discountCombination(emoticons, count +1,discount);
            discount.remove(discount.size()-1);
        }
        
        
    }
    
    static void calculate(List<Integer>discount,int[]emoticons) {
        
        int[] discountEmoticonsPrice = new int[emoticons.length];
        
        //이모티콘 할인된 가격 적용
        for(int i = 0 ; i<discount.size(); i++) {
            discountEmoticonsPrice[i] = emoticons[i] - (emoticons[i]/100*discount.get(i));
        }
        System.out.println();
        
        
        
        int personCount = 0 ;
        int maxPrice = 0;
        
        for(int i = 0 ; i<userList.size(); i++) {
            User now = userList.get(i);
            int sum = 0;
            boolean check = true;
            for(int j = 0 ; j<discountEmoticonsPrice.length; j++) {
                if(now.purchasePercent<= discount.get(j)) {
                    if(sum+discountEmoticonsPrice[j]>=now.price) {
                        personCount+=1;
                        check = false;
                        break;
                    }
                    if(sum+ discountEmoticonsPrice[j]<now.price) {
                        sum += discountEmoticonsPrice[j];
                    }
                }
            }
            if(check) {
                maxPrice += sum;
            }
            
        }
        
        if(personCount>solution[0]) {
            solution[0] = personCount;
            solution[1] = maxPrice;
        }
        
        if(personCount == solution[0]) {
            solution[1] = Math.max(maxPrice,solution[1]);
        }
        
    }
    
}
class User {
    int purchasePercent;
    int price;
    
    User(int purchasePercent, int price) {
        this.purchasePercent = purchasePercent;
        this.price = price;
    }
}

