class Solution {
    static int[] parent;
    public int solution(int n, int[][] computers) {
        parent = new int[n];
        
        for(int i = 0 ; i<n; i++) {
            parent[i] = i;
        }
        
        for(int i = 0 ; i<n; i++) {
            for(int j = 0 ; j<n; j++) {
                if(i!=j&&computers[i][j] == 1) {
                    union(i,j);
                }
            }
        }
        
        int result = 0;
        
        for(int i = 0; i<n; i++) {
            if(parent[i]==i) result++;
        }
        
        return result;
        
    }
    
    static boolean union(int x, int y) {
        int a = find(x);
        int b = find(y);
        
        if(a==b) return false;
        
        if(a<=b) {parent[b] = a;}
        else {parent[a] = b;}
        
        return true;
        
    }
    
    static int find(int number) {
        if(parent[number]==number) return number;
        return parent[number] = find(parent[number]);
    }
}