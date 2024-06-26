import java.util.*;

class Solution {
    private static Map<String,List<String>> in;
    private static Map<String,List<String>> out;
    private static List<Result> result;
    private static final String OUT_TIME = "23:59";
    public int[] solution(int[] fees, String[] records) {
        init(records);
        calculate(fees);
        result.sort((o1,o2)->{
            return o1.carNumber.compareTo(o2.carNumber);
        });
        int[] sol = new int[result.size()];
        for(int i = 0 ; i<result.size(); i++) {
            System.out.println("결과"+result.get(i).carNumber+" "+result.get(i).cost);
            sol[i] = result.get(i).cost;
        }
        return sol;
    }
    
    private static void calculate(int[] fees) {
        for(String key : in.keySet()) {
            List<String> inTimes = in.get(key);
            List<String> outTimes = out.getOrDefault(key,new ArrayList<>());
            int totalMinutes = 0;
            
            for(int i = 0 ; i<inTimes.size(); i++) {
                String inTime = inTimes.get(i);
                String outTime = (i < outTimes.size()) ? outTimes.get(i) : OUT_TIME;
                
                int inHourTime = Integer.parseInt(inTime.split(":")[0]);
                int inMinuteTime = Integer.parseInt(inTime.split(":")[1]);
                
                int outHourTime = Integer.parseInt(outTime.split(":")[0]);
                int outMinuteTime = Integer.parseInt(outTime.split(":")[1]);
                
                inMinuteTime = inMinuteTime + (inHourTime * 60);
                outMinuteTime = outMinuteTime + (outHourTime * 60);
                
                int diff = outMinuteTime - inMinuteTime;
                totalMinutes += diff;
            }
            if(totalMinutes <= fees[0]) {
                result.add(new Result(key, fees[1]));
            } else {
                int cost = fees[1] + (int) Math.ceil((double)(totalMinutes - fees[0]) / fees[2]) * fees[3];
                result.add(new Result(key, cost));
            }
            
        }
    }
    
    private static void init(String[] records) {
        in = new TreeMap<>();
        out = new TreeMap<>();
        result = new ArrayList<>();
        
        for(String record : records) {
            String[] tmpStr = record.split(" ");
            String time = tmpStr[0];
            String carNumber =tmpStr[1];
            String type = tmpStr[2];
            
              if(type.equals("IN")) {
                in.putIfAbsent(carNumber, new ArrayList<>());
                in.get(carNumber).add(time);
            }
            
            if(type.equals("OUT")) {
                out.putIfAbsent(carNumber, new ArrayList<>());
                out.get(carNumber).add(time);
            }
            
        }
    }
}

class Car {
    String time;
    String carNumber;
    
    Car(String time, String carNumber) {
        this.time = time;
        this.carNumber = carNumber;
    }
}

class Result {
    String carNumber;
    int cost;
    
    Result(String carNumber, int cost) {
        this.carNumber = carNumber;
        this.cost = cost;
    }
}