import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[]dx = {-1,1,0,0,1,1,-1,-1}; //대각선 포함
    static int[]dy = {0,0,-1,1,1,-1,1,-1};



    public static void main(String[]args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); //  열
            int h = Integer.parseInt(st.nextToken()); // 행
            if(w == 0 && h ==0){
                break;
            }

            int[][] island = new int[h][w];
            boolean[][] visit = new boolean[h][w];
            int count = 0;

            for(int i = 0; i<h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j =0; j<w; j++){
                    island[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0 ; i<h; i++){
                for(int j = 0; j<w; j++){
                    if(!visit[i][j] && island[i][j]==1){

                        Queue<Node> queue = new LinkedList<>();
                        queue.offer(new Node(i,j));
                        visit[i][j]= true;
                        count++;

                        while(!queue.isEmpty()){
                            Node tmp = queue.poll();

                            for(int k =0; k<8; k++){
                                int ddx = tmp.x + dx[k];
                                int ddy = tmp.y + dy[k];
                                if(ddx>=0 && ddy>=0 && ddx < h && ddy < w){
                                    if(!visit[ddx][ddy] && island[ddx][ddy]==1){
                                        visit[ddx][ddy]= true;
                                        queue.offer(new Node(ddx,ddy));
                                    }
                                }
                            }

                        }

                    }
                }
            }
            System.out.println(count);

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
