import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] normalSection;
    static char[][] blindSection;
    static boolean[][] normalVisit;
    static boolean[][] blindVisit;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int normalCount = 0;
    static int blindCount = 0;


    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        normalSection = new char[N][N];
        blindSection = new char[N][N];
        
        normalVisit = new boolean[N][N];
        blindVisit = new boolean[N][N];

        for(int i = 0; i< N; i++){
            String color = br.readLine();
            for(int j = 0; j<N; j++){
                normalSection[i][j] = color.charAt(j);
                if(color.charAt(j)=='G'){
                    blindSection[i][j]= 'R';
                    continue;
                }
                blindSection[i][j] = color.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!normalVisit[i][j]) {
                    normalbfs(i, j, normalSection[i][j]);
                    normalCount++;
                }
                if (!blindVisit[i][j]) {
                    blindbfs(i, j, blindSection[i][j]);
                    blindCount++;
                }
            }
        }

        System.out.println(normalCount + " " + blindCount);

    }

    static void normalbfs(int x, int y, char color){
        Queue<Node> queue = new LinkedList<>();
        normalVisit[x][y]= true;
        queue.offer(new Node(x,y));

        while(!queue.isEmpty()){
            Node tmp = queue.poll();

            for(int i = 0; i< 4; i++){
                int ddx = tmp.x + dx[i];
                int ddy = tmp.y + dy[i];

                if(ddx>=0 && ddy>=0 && ddx <N && ddy < N && !normalVisit[ddx][ddy] && normalSection[ddx][ddy]==color){

                        normalVisit[ddx][ddy] = true;
                        queue.offer(new Node(ddx,ddy));


                }
            }

        }

    }

    static void blindbfs(int x, int y, char color){
        Queue<Node> queue = new LinkedList<>();
        blindVisit[x][y]= true;
        queue.offer(new Node(x,y));

        while(!queue.isEmpty()){
            Node tmp = queue.poll();

            for(int i = 0; i< 4; i++){
                int ddx = tmp.x + dx[i];
                int ddy = tmp.y + dy[i];

                if(ddx>=0 && ddy>=0 && ddx <N && ddy < N){
                    if(!blindVisit[ddx][ddy] && blindSection[ddx][ddy]==color){
                        blindVisit[ddx][ddy] = true;
                        queue.offer(new Node(ddx,ddy));

                    }
                }
            }

        }

    }
}
class Node{
    int x;
    int y;
    
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
