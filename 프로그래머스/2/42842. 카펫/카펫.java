class Solution {
    public int[] solution(int brown, int yellow) {
        int square = brown + yellow;
        int[] answer = new int[2];
        
        for(int x = 3; x<square; x++) {
            if(square % x != 0) continue;
            int y = square / x;
            
            if(y>x) continue;
            
            int center = (x-2) * (y-2);
            
            if(center == yellow) {
                answer[0] = x;
                answer[1] = y;
                break;
            }
        }
        
        return answer;
    }
}
