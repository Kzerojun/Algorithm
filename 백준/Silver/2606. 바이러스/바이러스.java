import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int [][] node ;
    static boolean[] visit;
    static int virus;
    public static void main(String[]args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine()); //컴퓨터의 수
        int network = Integer.parseInt(br.readLine()); // 간선의 수

        virus = 0;
        node = new int[count+1][count+1];
        visit = new boolean[count+1];

        for(int i = 0; i<network; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node[a][b] = node[b][a] = 1;
        }

        bfs(1);

    }

    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<Integer>();
        visit[start] = true;
        queue.offer(start);
        while(!queue.isEmpty()){
            int x = queue.poll();

            for(int i = 1; i< node.length; i++){
                if(!visit[i] && node[x][i] ==1){
                    queue.offer(i);
                    visit[i] = true;
                    virus++;

                }
            }
        }

        System.out.println(virus);
    }

}


