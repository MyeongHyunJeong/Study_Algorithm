import java.io.FileInputStream;
import java.util.Scanner;

public class Main3987_2 {

	public static int n;
	public static int m;
	public static char[][] map;
	public static int startX;
	public static int startY;
	public static char d;
	public static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static int dn;
	public static int result;
	public static char resultD;
	public static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3987.txt"));
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		m = s.nextInt();
		map = new char[n][m];
		String temp = "";
		
		for(int i=0; i<map.length; i++) {
			temp = s.next();
			map[i] = temp.toCharArray();
		}
		
		startX = s.nextInt();
		startY = s.nextInt();
			
//		for(int i=0; i<map.length; i++) {
//			for(int j=0; j<map[i].length; j++) {
//				System.out.print(map[i][j] + " ");
//			}System.out.println();
//		}
		for(int i=0; i<4; i++) {
			if(i==0) {
				d = 'U';
				dn = 0;
			}else if(i==1) {
				d = 'R';
				dn = 1;
			}else if(i==2) {
				d = 'D';
				dn = 2;
			}else {
				d = 'L';
				dn = 3;
			}
			result = 0;
			sendSignal(startX-1, startY-1);
			if(max==Integer.MAX_VALUE) {
				break;
			}
		}
		
		if(max==Integer.MAX_VALUE) {
			System.out.println(resultD);
			System.out.println("Voyager");
		}else {
			System.out.println(resultD);
			System.out.println(max);
		}
		
	}
	
	public static void sendSignal(int x, int y) {
		String[][] chk = new String[n][m];	//무한인지 확인
		char direct = d;	//처음 어디로 쐇는지 저장
		boolean flag = false;	//무한인지 아닌지 확인
		while(x>-1 && y>-1 && x<map.length && y<map[0].length && map[x][y]!='C') {
			if(map[x][y]=='/' || map[x][y]=='\\') {
				changeD(map[x][y]);
			}
			if(chk[x][y]==null) {
				chk[x][y] = ""+d;
				x += dir[dn][0];
				y += dir[dn][1];
				result++;
			}else {
				if(chk[x][y].contains(""+d)) {
					flag = true;
					break;
				}else {
					chk[x][y] += d;
					x += dir[dn][0];
					y += dir[dn][1];
					result++;
				}
			}
		}
		if(flag) {
			max = Integer.MAX_VALUE;
			resultD = direct;
			return;
		}
		if(result>max) {
			max = result;
			resultD = direct;
		}
	}
	
	public static void changeD(char c) {
		if(d=='U') {
			if(c=='/') {
				d = 'R';
				dn = 1;
			}else {
				d = 'L';
				dn = 3;
			}
		}else if(d=='R') {
			if(c=='/') {
				d = 'U';
				dn = 0;
			}else {
				d = 'D';
				dn = 2;
			}
		}else if(d=='D') {
			if(c=='/') {
				d = 'L';
				dn = 3;
			}else {
				d = 'R';
				dn = 1;
			}
		}else {
			if(c=='/') {
				d = 'D';
				dn = 2;
			}else {
				d = 'U';
				dn = 0;
			}
		}
	}

}
