/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.*;

class Solution
{
	static int RIGHT = 0;
	static int DOWN = 1;
	static int LEFT = 2;
	static int UP = 3;
	static Tank tank;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static char[][] map;
	static int H;
	static int W;
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	
    	for(int testcase = 1; testcase<=T; testcase++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		
    		H = Integer.parseInt(st.nextToken());
    		W = Integer.parseInt(st.nextToken());
    		
    		map = new char[H][W];
    		
    		//그래프 입력
    		for(int i = 0; i<H; i++) {
    			String input = br.readLine();
    			for(int j = 0; j<W; j++) {
    				char ch =  input.charAt(j);
    				map[i][j] = ch;
    				
    				if(ch == '>') {
    					tank = new Tank(i,j,RIGHT);
    					map[i][j] = '.';
    				}
    				
    				if(ch == 'v') {
    					tank = new Tank(i,j,DOWN);
    					map[i][j] = '.';
    				}
    				
    				if(ch == '<') {
    					tank = new Tank(i,j,LEFT);
    					map[i][j] = '.';
    				}
    				if(ch == '^') {
    					tank = new Tank(i,j,UP);
    					map[i][j] = '.';
    				}
    			}
    		}
    		
    		
    		//커맨드 입력받기
    		int commandCount = Integer.parseInt(br.readLine());
    		String commands = br.readLine();
    
    		//게임 슈웃
    		playGame(commands,commandCount);
    		
    		//결과 출력
    		System.out.print("#"+testcase+" ");
    		for(int i = 0 ; i<H; i++) {
    			for(int j = 0 ; j<W; j++) {
    				System.out.print(map[i][j]);
    			}
    			System.out.println();
    		}
    	}
    	
    }
    
    private static void playGame(String command, int commandCount) {
    	//커맨드 한개씩 나누기
    	String[] commands = command.split("");
    	for(String tmpCommand : commands) {
    		executeCommand(tmpCommand);
    		
//    		System.out.println("현재 탱크 위치"+tank.x+" "+tank.y);
//    		System.out.println("현재 탱크 포 위치"+tank.direction);
//    		System.out.println("명령 커맨드"+tmpCommand);
//    		System.out.println("명령 이후 맵");
//    		for(int i = 0 ; i<H; i++) {
//    			for(int j = 0 ; j<W; j++) {
//    				System.out.print(map[i][j]);
//    			}
//    			System.out.println();
//    		}
//    		System.out.println();
    		
    	}
    	
      	if(tank.direction == RIGHT) {
    		map[tank.x][tank.y] = '>';
    	}
    	if(tank.direction == LEFT) {
    		map[tank.x][tank.y] = '<';
    	}
    	if(tank.direction == DOWN) {
    		map[tank.x][tank.y] = 'v';
    	}
    	if(tank.direction == UP) {
    		map[tank.x][tank.y] = '^';
    	}
    }
    
    private static void executeCommand(String command) {
    	if(!command.equals("S")) {
    		move(command);
    		return;
    	}
    	shoot();
    }
    
    private static void shoot() {
    	int nx = tank.x;
    	int ny = tank.y;
    	
    	while(true) {
    		nx = dx[tank.direction] + nx;
    		ny = dy[tank.direction] + ny;
    		
        	if(nx < 0 ||ny<0 || nx>=H || ny>=W || map[nx][ny]=='#') break;
        	
        	if(map[nx][ny]=='*') {
        		map[nx][ny] = '.';
        		break;
        	}
    	}
    }
    
    private static void move(String move) {
    	
    	int x = tank.x;
    	int y = tank.y;
    	int direction = tank.direction;
    	
    	if(move.equals("R")) {
    		x = dx[0] + x;
    		y = dy[0] + y;
    		direction = RIGHT;
    		
    	}
    	if(move.equals("D")) {
    		x = dx[1] +x;
    		y = dy[1] + y;
    		direction = DOWN;
    	}
    	if(move.equals("L")) {
    		x = dx[2] + x;
    		y = dy[2]+ y;
    		direction = LEFT;
    	}
    	if(move.equals("U")) {
    		x = dx[3] + x;
    		y = dy[3] + y;
    		direction = UP;
    	}
    	
    	tank.direction = direction;
    	
    	if(x < 0 ||y<0 || x>=H || y>=W || map[x][y]=='-' || map[x][y] =='*' || map[x][y] == '#') return;
    	
    	tank.x = x;
    	tank.y = y;
    	
    
    	
    }
    

}

class Tank {
	int x;
	int y;
	int direction;
	
	Tank(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
} 