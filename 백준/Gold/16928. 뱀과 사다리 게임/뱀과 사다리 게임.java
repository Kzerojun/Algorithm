import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int N, M; //사다리 수 뱀수
    static int[] dice = {1, 2, 3, 4, 5, 6};
    static List<Ladder> list;
    static int[] visit;
    static int sol = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        visit = new int[101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Ladder(start, end));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Ladder(start, end));
        }
        Arrays.fill(visit, Integer.MAX_VALUE);

        bfs(1);
        System.out.println(sol);
    }

    static void bfs(int start){
        Queue<Location> q = new LinkedList<>();
        visit[start] = 0;
        q.offer(new Location(start,0));

        while (!q.isEmpty()) {
            Location now = q.poll();

            if(now.location == 100){
                sol = Math.min(sol, now.count);
            }

            for (Ladder ladder : list) {
                if(now.location==ladder.start){
                    now.location = ladder.end;
                    if(visit[now.location]<now.count){
                        visit[now.location] = now.count;
                    }
                    q.offer(new Location(now.location, now.count));
                }
            }

            for (int i = 0; i < 6; i++) {
                int nl = now.location + dice[i];
                if(nl>100) continue;
                if(visit[nl]< now.count+1) continue;
                q.offer(new Location(nl, now.count + 1));
                visit[nl] = now.count+1;
            }

        }




    }

}
class Location{
    int location;
    int count;

    Location(int location, int count) {
        this.location = location;
        this.count = count;
    }
}

class Ladder {
    int start;
    int end;

    Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

