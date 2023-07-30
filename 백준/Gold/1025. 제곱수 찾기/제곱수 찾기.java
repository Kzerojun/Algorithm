import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 입력 배열
    static String[] arr;
    static int[][] map;

    static int N, M;
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new String[N+1];
        map = new int[N][M];

        for(int i=0; i<N; ++i){
            arr[i] = br.readLine();
            for(int j=0; j<M; ++j){
                map[i][j] = arr[i].charAt(j) - '0';
            }
        }


        // 다중 반복문은 연산 과정이 좀 헷갈리니 나눔
        for(int i=0; i<N; ++i){ // 행 시작 부분 제한
            for(int j=0; j<M; ++j){ // 열 시작 부분 제한

                // 제한된 범위인 i x j를 파라미터로 메서드 호출
                slv(j, i);
            }
        }

        System.out.println(ans);

    }

    public static void slv(int c, int r){
        for(int i=-N; i<N; ++i){ // 행 공차
            for(int j=-M; j<M; ++j){ // 열 공차

                // 연두는 서로 다른 1개 이상의 칸을 선택 --> (1,1), (1,1)과 같은 경우는 무시
                if(i == 0 && j == 0) continue;

                int x = c; // 제한된 열 시작 부분
                int y = r; // 제한된 행 시작 부분

                // 만들어질 제곱수
                int sqr = 0;

                // 범위 체크
                while (0<=x && x<M && 0<=y && y<N){
                    // 제곱수 생성
                    sqr *= 10;
                    sqr += map[y][x];

                    // 제곱근 구하기
                    int root = (int)Math.sqrt(sqr);

                    // 제곱수 판별
                    if( Math.pow(root, 2) == sqr )
                        ans = Math.max(ans, sqr);

                    x += j;
                    y += i;
                }
            }
        }
    }

}