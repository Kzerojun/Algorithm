import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        HashSet<String> workArray = new HashSet<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String key = st.nextToken(); //키 (사람 이름)
            String value = st.nextToken(); // 값(출퇴근 여부)

            if(value.equals("enter")){ //출근일 경우 HashSet에 저장하고(WorkArray)
                workArray.add(key);
            }else if(value.equals("leave")){ //퇴근일 경우 HashSet에서 지운다.
                workArray.remove(key);
            }
        }

        br.close();

        //정렬을 위해 list를 따로 만들어줌(Collections.sort()위해)
        ArrayList<String> list = new ArrayList<String>(workArray);
        Collections.sort(list);

        for(int i = list.size()-1;  i >= 0; i--){ //역순 정렬을 위해 뒤부터 출력
            bw.write(list.get(i) + "\n");
        }

        bw.flush();
        bw.close();
    }

}