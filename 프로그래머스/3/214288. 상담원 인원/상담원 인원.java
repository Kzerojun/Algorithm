import java.util.*;

class Solution {
    private static List<List<Time>> timeForEachType;
    private static int[][] waitTimes;
    private static int[] counselors;
    private static int sol;
        
    public int solution(int k, int n, int[][] reqs) {
        init(reqs,k,n);
        return calculate(k,n);
    }
    
    // 1. 초기화
    private static void init(int[][] reqs, int k,int n) {
        timeForEachType = new ArrayList<>();
        sol = 0;
        waitTimes = new int[k+1][n+1];
        counselors = new int[k+1];
        
        for(int i = 0 ; i<=k; i++) {
            timeForEachType.add(new ArrayList<>());
        }
        
        for(int [] req : reqs) {
            int startTime = req[0];
            int remainTime = req[1];
            int type = req[2];
            
            timeForEachType.get(type).add(new Time(startTime,startTime+remainTime));
            
        }
    }
    
    // 2. 각 유형별 상담원이 몇명일대 몇초의 대기시간이 걸리는지
    private static int calculate(int k, int n) {
        for(int type = 1; type <=k; type ++) {
            for(int workerCount = 1; workerCount<=(n-k)+1; workerCount++) {
                int waitTime = calculateTimeForWorkerCount(timeForEachType.get(type),workerCount);
                waitTimes[type][workerCount] = waitTime;
            }
        }
        
        //상담원 한명씩 우선 배치
        for(int type = 1 ; type<=k; type ++) {
            counselors[type]++;
        }
        
        int remainWorkerCount = n-k;
        
        while(remainWorkerCount-- > 0){
            int maxReduceTime = 0;
            int typeNumber = 0;
            
            for(int type = 1; type<=k; type++ ){
                int counselorsNumber = counselors[type];
                
                int nowTime = waitTimes[type][counselorsNumber];
                int nextTime = waitTimes[type][counselorsNumber+1];
                
                int reduceTime = Math.abs(nextTime - nowTime);
                
                if(reduceTime>maxReduceTime) {
                    maxReduceTime = reduceTime;
                    typeNumber = type;
                }
            }
            
            if(maxReduceTime == 0 ) {
                break;
            }
            
            counselors[typeNumber]++;
        }
        
        for(int type = 1; type<=k; type ++) {
            if(timeForEachType.get(type).size()==0) continue;
            
            int counselorsNumber = counselors[type];
            sol += waitTimes[type][counselorsNumber];
        }
        return sol;
        
    }
    
    
    //상담원 계산 구현부분
    private static int calculateTimeForWorkerCount(List<Time> type, int workerCount) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int waitTime = 0;
        
        for(Time time : type) {
            if(pq.isEmpty() || pq.size() < workerCount) {
                pq.add(time.endTime);
            }else {
                int earlyEndTime = pq.poll();
                
                if(time.startTime < earlyEndTime) {
                    waitTime += earlyEndTime - time.startTime;
                    pq.add(earlyEndTime + (time.endTime - time.startTime));
                }else {
                    pq.add(time.endTime);
                }
            }
        }
        
        return waitTime;
    }
    
    
}

class Time {
    int startTime;
    int endTime;
    
    Time(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}