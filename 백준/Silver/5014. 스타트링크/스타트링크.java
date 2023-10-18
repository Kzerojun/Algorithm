import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int F,G,S,U,D;
    static boolean[] visit;
    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        visit = new boolean[F+1];

        int sol = Elevator(S);
        if(sol == -1){
            System.out.println("use the stairs");
            return;
        }
        System.out.println(sol);
    }

    static int Elevator(int S){
        Queue<Node5014> q = new LinkedList<>();
        q.offer(new Node5014(S,0));

        visit[S] = true;

        while(!q.isEmpty()){
            Node5014 currentstage = q.poll();

            if( currentstage.index == G){
                return currentstage.count;
            }

            int[] nextstage = {currentstage.index+U,currentstage.index-D };

            for (int i = 0; i < 2; i++) {
                int nextIndex = nextstage[i];
                if (nextIndex >= 1 && nextIndex <= F && !visit[nextIndex]) {
                    visit[nextIndex] = true;
                    q.offer(new Node5014(nextIndex, currentstage.count + 1));
                }
            }

        }

        return -1;

    }

}

class Node5014{
    int index;
    int count;
    Node5014(int index, int count){
        this.index = index;
        this.count = count;
    }

}
