import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author     : junsang Hwang
 * @Date       : 2021.03.12
 * @DESC       : 첼시를 도와줘!
 * @see        : https://www.acmicpc.net/problem/11098
 */
public class Main {

    //===
    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));;
    static StringBuilder result = new StringBuilder();
    static StringTokenizer st;
    //===


    static int playerCnt = 0;
    static List<AbstractMap.SimpleImmutableEntry<Integer, String>> playerInfo = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        input();
        output();
    }

    public static void solution() throws Exception {

        int max = 0;
        String answer = "";

        for(int i=0; i<playerCnt; i++) {
            int x = playerInfo.get(i).getKey();

            if (max < x) {
                max = x;
                answer = playerInfo.get(i).getValue();
            }
        }
        result.append(answer + "\n");
        playerInfo.clear();
    }

    public static void input() throws Exception {
        int testCaseCnt = Integer.parseInt(br.readLine());

        for(int i=0; i<testCaseCnt; i++) {
            playerCnt = Integer.parseInt(br.readLine());
            for(int j=0; j<playerCnt; j++) {
                st = new StringTokenizer(br.readLine());
                playerInfo.add(new AbstractMap.SimpleImmutableEntry<>(Integer.parseInt(st.nextToken()), st.nextToken()));
            }
            solution();
        }
    }

    public static void output() throws Exception {
        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}