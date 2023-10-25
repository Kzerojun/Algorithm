import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> gears;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gears  = new ArrayList<>();

        for(int i = 0; i<4; i++){
            gears.add(new ArrayList<>());
            String number = br.readLine();
            for(int j = 0; j<8; j++){
                gears.get(i).add(number.charAt(j)-'0');
            }
        }

        int count = Integer.parseInt(br.readLine());
        List<Spin> spins = new ArrayList<>();

        for(int i = 0; i<count; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gear = Integer.parseInt(st.nextToken())-1;
            int direction = Integer.parseInt(st.nextToken());
            spins.add(new Spin(gear,direction));
        }

        for(int i = 0; i< spins.size(); i++){
            visit = new boolean[4];
            int gearNum = spins.get(i).gear;
            int direction = spins.get(i).direction;
            rotate(visit,gearNum,direction);
        }

        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gears.get(i).get(0) == 1) {
                score += (int) Math.pow(2, i);
            }
        }

        System.out.println(score);
    }

    static void rotate(boolean[] visit, int gearNum, int direction ){
        visit[gearNum] = true;

        if(gearNum-1 >= 0 && !visit[gearNum-1]){ //왼쪽
            if(gears.get(gearNum).get(6) != gears.get(gearNum-1).get(2)){
                rotate(visit,gearNum-1,-direction);
            }
        }
        if(gearNum+1 < 4 && !visit[gearNum+1]){ //오른쪽
            if(gears.get(gearNum).get(2) != gears.get(gearNum+1).get(6)){
                rotate(visit,gearNum+1,-direction);
            }
        }

        if(direction == 1){
            gears.get(gearNum).add(0,gears.get(gearNum).remove(7));
        }else{
            gears.get(gearNum).add(gears.get(gearNum).remove(0));
        }
    }
}

class Spin{
    int gear;
    int direction;
    Spin(int gear, int direction){
        this.gear = gear;
        this.direction = direction;
    }
}
