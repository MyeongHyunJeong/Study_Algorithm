import java.io.FileInputStream;
import java.util.Scanner;

public class Main2174 {

	public static int n;
	public static int m;
	public static int[][] map;
	public static int rnum;
	public static int mnum;
	public static Robot[] robot;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2174.txt"));
		Scanner s = new Scanner(System.in);
		
		m = s.nextInt();
		n = s.nextInt();
		map = new int[n][m];
		rnum = s.nextInt();
		mnum = s.nextInt();
		
//		System.out.println(m + " " + n + " " + rnum + " " + mnum);
		
		robot = new Robot[rnum+1];
		int x = 0;
		int y = 0;
		String d = "";
		for(int i=1; i<rnum+1; i++) {
			y = s.nextInt();
			x = s.nextInt();
			d = s.next();
			robot[i] = new Robot(n-x, y-1, d);
			map[robot[i].x][robot[i].y] = i; 
		}
		
		int robotNum = 0;
		String command;
		int move = 0;
		boolean flag = false;
		int nx = 0;
		int ny = 0;
		
//		for(int i=1; i<robot.length; i++) {
//			System.out.println("I: " + i + " x: " + robot[i].x + " y: " + robot[i].y);
//		}
		for(int i=0; i<mnum; i++) {
			robotNum = s.nextInt();
			command = s.next();
			move = s.nextInt();
			flag = false;
//			System.out.println("==================================");
//			System.out.println(robotNum + " " + command + " " + move);
			for(int j=0; j<move; j++) {
				x = robot[robotNum].x;
				y = robot[robotNum].y;
				if(command.equals("F")) {
					if(robot[robotNum].d.equals("N")) {
						nx = x + dir[0][0];
						ny = y + dir[0][1];
					}else if(robot[robotNum].d.equals("S")) {
						nx = x + dir[1][0];
						ny = y + dir[1][1];
					}else if(robot[robotNum].d.equals("W")) {
						nx = x + dir[2][0];
						ny = y + dir[2][1];
					}else {
						nx = x + dir[3][0];
						ny = y + dir[3][1];
					}
					if(nx>-1 && ny>-1 && nx<map.length && ny<map[0].length) {
						if(map[nx][ny] != 0) {
							System.out.println("Robot " + robotNum + " crashes into robot " + map[nx][ny]);
							flag = true;
							break;
						}else {
							map[x][y] = 0;
							robot[robotNum].setX(nx);
							robot[robotNum].setY(ny);
							map[nx][ny] = robotNum;
						}
					}else {
						System.out.println("Robot " + robotNum + " crashes into the wall");
						flag = true;
						break;
					}
				}else if(command.equals("R")) {
					changeDirection(robotNum, command);
//					System.out.println(robot[robotNum].d);
				}else if(command.equals("L")) {
					changeDirection(robotNum, command);
//					System.out.println(robot[robotNum].d);
				}
//				for(int q=0; q<map.length; q++) {
//					for(int w=0; w<map[q].length; w++) {
//						System.out.print(map[q][w] + " ");
//					}System.out.println();
//				}System.out.println("=================================");
			}
			if(flag) {
				break;
			}
		}
		if(!flag) {
			System.out.println("OK");
		}
		
		
	}
	
	public static void changeDirection(int num, String command) {
		if(command.equals("R")) {
			if(robot[num].d.equals("N")) {
				robot[num].setD("E");
			}else if(robot[num].d.equals("S")) {
				robot[num].setD("W");
			}else if(robot[num].d.equals("W")) {
				robot[num].setD("N");
			}else {
				robot[num].setD("S");
			}
		}else {
			if(robot[num].d.equals("N")) {
				robot[num].setD("W");
			}else if(robot[num].d.equals("S")) {
				robot[num].setD("E");
			}else if(robot[num].d.equals("W")) {
				robot[num].setD("S");
			}else {
				robot[num].setD("N");
			}
		}
	}
	
	public static class Robot {
		int x;
		int y;
		String d;
		public Robot(int x, int y, String d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public String getD() {
			return d;
		}
		public void setD(String d) {
			this.d = d;
		}
	}

}
