import java.util.*;

class Solution {
    
    //이동 횟수, 위치, 겹친수
    static Map<Integer, Map<String, Integer>> records;
    static int dangerCount;
    
    public int solution(int[][] points, int[][] routes) {
        init();
        sol(points, routes);
        return countDangerSituations();
    }
    
    private static void init() {
        records = new HashMap<>();
        dangerCount = 0;
    }
    
    private static void sol(int[][] points, int[][] routes) {
        for(int[] route : routes) {
            int startPoint = route[0];
            Robot robot = new Robot(points[startPoint-1][0], points[startPoint-1][1], 0);
            
            records.putIfAbsent(robot.moveCount, new HashMap<>());
            Map<String, Integer> inRecords = records.get(robot.moveCount);
            inRecords.put(robot.x + " " + robot.y, inRecords.getOrDefault(robot.x + " " + robot.y, 0) + 1);
            
            for(int i = 0; i < route.length - 1; i++) {
                int nextPoint = route[i + 1];
                
                while(true) {
                    boolean result = robot.move(points[nextPoint-1][0], points[nextPoint-1][1]);
                    
                    records.putIfAbsent(robot.moveCount, new HashMap<>());
                    Map<String, Integer> currentRecords = records.get(robot.moveCount);
                    currentRecords.put(robot.x + " " + robot.y, currentRecords.getOrDefault(robot.x + " " + robot.y, 0) + 1);
                    
                    if(result) {
                        break;
                    }
                }                
            }
        }   
    }
    
    private static int countDangerSituations() {
        int count = 0;
        
        for(Map<String, Integer> timeRecord : records.values()) {
            for(int robotCount : timeRecord.values()) {
                if(robotCount >= 2) {
                    count++;
                }
            }
        }
        
        return count;
    }
}

class Robot {
    int x;
    int y;
    int moveCount;
    
    Robot(int x, int y, int moveCount) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
    }
    
    public boolean move(int goalX, int goalY) {
        if(this.x == goalX && this.y == goalY) {
            return true;
        }
        
        if(this.x != goalX) {
            if(this.x < goalX) this.x++;
            else this.x--;
        } else if(this.y != goalY) {
            if(this.y < goalY) this.y++;
            else this.y--;
        }
        
        this.moveCount++;
        
        return (this.x == goalX && this.y == goalY);
    }
}