import java.util.*;

class Solution {
    static long sol = 0;
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        LinkedList<Item> go = new LinkedList<>();
        LinkedList<Item> back = new LinkedList<>();
        
        for(int i = 0 ; i<deliveries.length; i++) {
            if(deliveries[i]!=0) {
                go.add(new Item(i+1,deliveries[i]));
            }
            if(pickups[i]!=0) {
                back.add(new Item(i+1,pickups[i]));
            }
        }
        
        while(!go.isEmpty() || !back.isEmpty()) {
            long length = 0;
            int sum = 0;
            while(!go.isEmpty() && sum < cap){
                Item item = go.pollLast();
                length = (long) Math.max(length, item.distance);
                
                if(item.count + sum <= cap){
                    sum +=item.count;
                }else{
                    item.count -= (cap - sum);
                    go.add(item);
                    break;
                }
            }
            sum = 0;
            while(!back.isEmpty() && sum < cap){
                Item item = back.pollLast();
                length = (long)Math.max(length, item.distance);
                
                if(item.count + sum <= cap){
                    sum +=item.count;
                }else{
                    item.count -= (cap - sum);
                    back.add(item);
                    break;
                }
            }
            sol += length*2;
        }
            
        return sol;
    }
}

class Item {
    long distance;
    int count;
    
    Item(long distance, int count) {
        this.distance = distance;
        this.count = count;
    }
}
