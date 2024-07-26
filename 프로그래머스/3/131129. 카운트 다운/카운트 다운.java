import java.util.*;

class Solution {
    public int[] solution(int target) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[target + 1];
        
        pq.offer(new Node(0, 0, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            if (current.score == target) {
                return new int[]{current.darts, current.singles};
            }
            
            if (visited[current.score]) continue;
            visited[current.score] = true;
            
            // Single, Double, Triple scores
            for (int i = 1; i <= 20; i++) {
                for (int mul = 1; mul <= 3; mul++) {
                    int newScore = current.score + i * mul;
                    if (newScore <= target) {
                        pq.offer(new Node(newScore, 
                                          current.darts + 1, 
                                          current.singles + (mul == 1 ? 1 : 0)));
                    }
                }
            }
            
            // Bull's eye (50 points)
            int newScore = current.score + 50;
            if (newScore <= target) {
                pq.offer(new Node(newScore, current.darts + 1, current.singles + 1));
            }
        }
        
        // This line should never be reached if the input is valid
        return new int[]{-1, -1};
    }
    
    private class Node implements Comparable<Node> {
        int score;
        int darts;
        int singles;
        
        Node(int score, int darts, int singles) {
            this.score = score;
            this.darts = darts;
            this.singles = singles;
        }
        
        @Override
        public int compareTo(Node other) {
            if (this.darts != other.darts) {
                return Integer.compare(this.darts, other.darts);
            }
            return Integer.compare(other.singles, this.singles);
        }
    }
}