import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] result = new int[4];
        int nodeCount = 0;
        Map<Integer,Data> nodes = new HashMap<>();
            
        for(int[] data : edges) {
            int from = data[0];
            int to = data[1];
            
            nodeCount = Math.max(from,Math.max(to,nodeCount));
            
            Data giveNode = nodes.getOrDefault(from,new Data(0,0));
            giveNode.giveCount++;
            nodes.put(from,giveNode);
            
            Data receiveNode = nodes.getOrDefault(to, new Data(0,0));
            receiveNode.receiveCount++;
            nodes.put(to,receiveNode);
        }
        
        for(int i = 1; i<=nodeCount; i++) {
            Data node = nodes.get(i);
            
            if(node.giveCount>=2 && node.receiveCount==0){
                result[0] = i;
                continue;
            }
            
            if(node.giveCount==0){
                result[2]++;
                continue;
            }
            
            if(node.giveCount>=2 && node.receiveCount>=2){
                result[3]++;
            }
        }
        
        result[1] = nodes.get(result[0]).giveCount - result[2] - result[3];
        
        return result;
        
        
    }
}

class Data{
    int giveCount;
    int receiveCount;
    
    Data(int giveCount, int receiveCount) {
        this.giveCount = giveCount;
        this.receiveCount = receiveCount;
    }
}