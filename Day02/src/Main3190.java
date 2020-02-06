import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main3190 {

	public static int n;
	public static int k;
	public static int[][] map;
	public static int l;
	public static int x = 0;
	public static String c;
	public static Direct dir;
	public static Queue<Direct> sn = new LinkedList<Direct>();
	public static int t = 1;
	public static int time;
	public static boolean flag = true;
	public static boolean eat = false;
	public static int count = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input03.txt"));
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		k = s.nextInt();
		map = new int[n][n];
		map[0][0] = 2;
		time = 0;
		for(int i=0; i<k; i++) {
			map[s.nextInt()-1][s.nextInt()-1] = 1;
		}
		
		l = s.nextInt();
		dir = new Direct(0,1,"R");
		sn.add(new Direct(0,0,"R"));
		Direct head;
		Direct tail;
		int nx = 0;
		int ny = 0;
		int cx = 0;
		int cy = 0;
		for(int i=0; i<101; i++) {
			if(i<l) {
				x = s.nextInt()-time;
				c = s.next();
			}else {
				x = Integer.MAX_VALUE;
			}
//			System.out.println("time change dir : " + x);
//			for(int p=0; p<map.length; p++) {
//				for(int o=0; o<map[0].length; o++) {
//					System.out.print(map[p][o] + " ");
//				}System.out.println();
//			}System.out.println();
			for(int j=0; j<x; j++) {
				head = sn.poll();
//				System.out.println(map[head.x][head.y]);
				t = sn.size();
				nx = dir.x+head.x;
				ny = dir.y+head.y;
				time++;
				if(nx>-1&&ny>-1&&nx<map.length&&ny<map[0].length&&map[nx][ny]<2) {
					if(map[nx][ny]==1) {
						eat = true;
					}else {
						eat = false;
					}
					map[nx][ny] = 2; 
					map[head.x][head.y] = 0;
					sn.add(new Direct(nx, ny, head.d));
					cx = head.x;
					cy = head.y;
					if(sn.size()>1) {
						for(int e=0; e<t; e++) {
							tail = sn.poll();
							map[tail.x][tail.y] = 0;
							map[cx][cy] = 2;
							sn.add(new Direct(cx, cy, head.d));
							cx = tail.x;
							cy = tail.y;
							if(e==t-1 && eat) {
								map[tail.x][tail.y] = 2; 
								sn.add(new Direct(tail.x, tail.y, head.d));
							}
						}						
					}else {
						if(eat) {
							map[cx][cy] = 2;
							sn.add(new Direct(cx,cy,head.d));
						}
					}
				}else {
					flag = false;
					break;
				}
//				System.out.println("TIME : " + time);
//				for(int p=0; p<map.length; p++) {
//					for(int o=0; o<map[0].length; o++) {
//						System.out.print(map[p][o] + " ");
//					}System.out.println();
//				}System.out.println();
			}
			if(flag) {
//				System.out.println("come!!!");
				changeDir();
			}else {
				break;
			}
		}
//		System.out.println("T : " + (time+count));
		System.out.println(time);
		
	}
	
	public static void changeDir() {
		count++;
//		System.out.println(sn.peek().x + " " + sn.peek().y + " " + sn.peek().d + " " + c);
		if(sn.peek().d=="U") {
			if(c.equals("D")) {
				sn.peek().setD("R");
				dir.setX(0);
				dir.setY(1);
				dir.setD("R");
			}else {
				sn.peek().setD("L");
				dir.setX(0);
				dir.setY(-1);
				dir.setD("L");
			}
		}else if(sn.peek().d=="D") {
			if(c.equals("D")) {
				sn.peek().setD("L");
				dir.setX(0);
				dir.setY(-1);
				dir.setD("L");
			}else {
				sn.peek().setD("R");
				dir.setX(0);
				dir.setY(1);
				dir.setD("R");
			}
		}else if(sn.peek().d=="L") {
			if(c.equals("D")) {
				sn.peek().setD("U");
				dir.setX(-1);
				dir.setY(0);
				dir.setD("U");
			}else {
				sn.peek().setD("D");
				dir.setX(1);
				dir.setY(0);
				dir.setD("D");
			}
		}else if(sn.peek().d=="R") {
			if(c.equals("D")) {
				sn.peek().setD("D");
				dir.setX(1);
				dir.setY(0);
				dir.setD("D");
			}else {
				sn.peek().setD("U");
				dir.setX(-1);
				dir.setY(0);
				dir.setD("U");
			}
		}
//		System.out.println(dir.x + " " + dir.y + " " + dir.d);
//		System.out.println("¹æÇâ : " + sn.peek().d);
	}
	
	public static class Direct {
		int x;
		int y;
		String d;
		public Direct(int x, int y, String d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public String getD() {
			return d;
		}
		public void setD(String d) {
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
	}

}
