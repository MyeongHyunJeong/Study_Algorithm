import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main3987 {
	
	public static int n;
	public static int m;
	public static char[][] map;
	public static int max = Integer.MIN_VALUE;
	public static char resultD;
	public static char dir;
	public static char currentD;
	public static Direct d;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3987.txt"));
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		m = s.nextInt();
		map = new char[n][m];
		for(int i=0; i<map.length; i++) {
			map[i] = s.next().toCharArray();
		}
		int px = s.nextInt()-1;
		int py = s.nextInt()-1;
		map[px][py] = '*';
		
		d = new Direct(0,0);

		int x = 0;
		int y = 0;
		int time = 0;
		String[][] chk = new String[n][m];
		for(int i=0; i<4; i++) {
			for(int j=0; j<chk.length; j++) {
				Arrays.fill(chk[j], "x");
			}
			time = 1;
			x = px;
			y = py;
			if(i==0) {
				dir = 'U';
				currentD = 'U';
				d.setX(-1);
				d.setY(0);
				chk[x][y] = "U";
			}else if(i==1) {
				dir = 'R';
				currentD = 'R';
				d.setX(0);
				d.setY(1);
				chk[x][y] = "R";
			}else if(i==2) {
				dir = 'D';
				currentD = 'D';
				d.setX(1);
				d.setY(0);
				chk[x][y] = "D";
			}else {
				dir = 'L';
				currentD = 'L';
				d.setX(0);
				d.setY(-1);
				chk[x][y] = "L";
			}
			while(true) {
				x += d.x;
				y += d.y;
				if(x>-1 && y>-1 && x<map.length && y<map[0].length && map[x][y]!='C') {
					if(chk[x][y].equals("x")) {
						chk[x][y]=""+currentD;
					}else if(chk[x][y].contains(""+currentD)) {
						break;
					}
					if(map[x][y]=='/' || map[x][y]=='\\'){
						changeD(map[x][y]);
					}
					chk[x][y]+=currentD;
					time++;
				}else {
					if(time > max) {
						max = time;
						resultD = dir;
					}
					break;
				}
			}
			
		}
		System.out.println(resultD);
		System.out.println(max);
		
		
	}
	
	public static void changeD(char change) {
		if(change=='/') {
			if(currentD == 'U') {
				d.setX(0);
				d.setY(1);
				currentD = 'R';
			}else if(currentD == 'R') {
				d.setX(-1);
				d.setY(0);
				currentD = 'U';
			}else if(currentD == 'D') {
				d.setX(0);
				d.setY(-1);
				currentD = 'L';
			}else {
				d.setX(1);
				d.setY(0);
				currentD = 'D';
			}
		}else {
			if(currentD == 'U') {
				d.setX(0);
				d.setY(-1);
				currentD = 'L';
			}else if(currentD == 'R') {
				d.setX(1);
				d.setY(0);
				currentD = 'D';
			}else if(currentD == 'D') {
				d.setX(0);
				d.setY(1);
				currentD = 'R';
			}else {
				d.setX(-1);
				d.setY(0);
				currentD = 'U';
			}
		}
	}
	
	public static class Direct {
		int x;
		int y;
		public Direct(int x, int y) {
			this.x = x;
			this.y = y;
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
