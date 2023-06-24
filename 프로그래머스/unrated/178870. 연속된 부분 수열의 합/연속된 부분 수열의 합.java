class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] index = new int[2];
        int length = Integer.MAX_VALUE;
        
        int start= 0;
        int end = 0;
        int sum = sequence[0];
        
        while(start<sequence.length && end< sequence.length){
            if(sum == k && end -start<length){
                length = end -start;
                index[0] = start;
                index[1] = end;
                sum -=sequence[start];
                start++;
                }
            if(sum>k){
                sum -=sequence[start];
                start++;
                }
            else{
                end++;
                if(end<sequence.length){
                    sum+=sequence[end];
                }
            }
}
        
        
        return index;
    }
}