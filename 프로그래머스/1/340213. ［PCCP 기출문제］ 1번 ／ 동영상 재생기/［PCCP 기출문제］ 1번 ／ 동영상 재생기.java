class Solution {
    
    private static int posTime;
    private static int opStartTime,opEndTime;
    private static int videoTime;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        init(pos,op_start,op_end,video_len);
        return simulate(commands);        
    }
    
    private static String simulate(String[] commands) {
        for(String command : commands) {
          
            if(posTime >= opStartTime && posTime <= opEndTime) {
                posTime = opEndTime;
            }
            
            if(command.equals("next")) {
                posTime = Math.min(videoTime,posTime+10);
            }
            
            if(command.equals("prev")) {
                posTime = Math.max(0,posTime-10);
            }
            
                
            if(posTime >= opStartTime && posTime <= opEndTime) {
                posTime = opEndTime;
            }
        }
        
        String result = convertTime(posTime);
        return result;
        
        
    }
    
    private static void init(String pos, String op_start, String op_end, String video_len) {
        posTime = parseTimeToMinutes(pos);
        opStartTime = parseTimeToMinutes(op_start);
        opEndTime = parseTimeToMinutes(op_end);
        videoTime = parseTimeToMinutes(video_len);
    }
    
    private static int parseTimeToMinutes(String time) {
        String[] timeSplit = time.split(":");
        int hour = Integer.parseInt(timeSplit[0]) * 60;
        int minute = Integer.parseInt(timeSplit[1]);
        return hour + minute;
    }
    
    private static String convertTime(int time) {
        StringBuilder sb = new StringBuilder();
        int hour = time / 60;
        int minute = time % 60;
        
        if(hour<10) {
            sb.append(0);
        }
        
        sb.append(hour).append(":");
        
        if(minute<10) {
            sb.append(0);
        }
        sb.append(minute);
        
        return sb.toString();
    }

    
    
    
}