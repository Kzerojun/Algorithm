import java.util.*;
import java.io.*;

class Solution
{
    static String str;
	public static void main(String args[]) throws Exception
	{
			init();
        	play();
	}
 	private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
    }
    
    private static void play() {
        String[] tmp = str.split(" ");
        
        int playerA = Integer.parseInt(tmp[0]);
        int playerB = Integer.parseInt(tmp[1]);
        // true A false B
        if(whoWin(playerA,playerB)){
            System.out.println("A");
        }else {
        	System.out.println("B");
        }
        
    }  
    
    private static boolean whoWin(int playerA, int playerB) {
    	if(playerA > playerB) {
            return true;
    		}
        return false;
} 
}