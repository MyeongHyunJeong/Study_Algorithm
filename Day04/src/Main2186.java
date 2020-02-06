import java.io.FileInputStream;
import java.util.Scanner;

public class Main2186 {

	public static int n;
	public static int m;
	public static int k;
	public static char[][] map;
	public static String sp;
	public static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	public static int result = 0;
	public static boolean[][] chk;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2186.txt"));
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		m = s.nextInt();
		k = s.nextInt();
		map = new char[n][m];
		chk = new boolean[n][m];
		String temp = "";
		for(int i=0; i<map.length; i++) {
			temp = s.next();
			map[i] = temp.toCharArray();
		}
		sp = s.next();
		
		for(int i=0; i<chk.length; i++) {
			for(int j=0; j<chk[i].length; j++) {
				if(sp.contains(""+map[i][j])) {
					chk[i][j] = true;
				}
			}
		}
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j]==sp.charAt(0)) {
					getResult(i, j, ""+sp.charAt(0));
				}
			}
		}
		System.out.println(result);
	}
	
	public static void getResult(int x, int y, String temp) {
		if(x<0 || y<0 || x>=map.length || y>=map[0].length) {
			return;
		}
		if(temp.length()==sp.length()) {
			if(temp.equals(sp)) {
				result++;
			}else {
				return;
			}
		}
		if(temp.length()<sp.length()) {
			if(sp.contains(temp)) {
				int nx = 0;
				int ny = 0;
				for(int i=0; i<dir.length; i++) {
					for(int j=0; j<k; j++) {
						if(i==0) {
							nx = x + dir[i][0]-j;
							ny = y + dir[i][1];
						}else if(i==1) {
							nx = x + dir[i][0]+j;
							ny = y + dir[i][1];
						}else if(i==2) {
							nx = x + dir[i][0];
							ny = y + dir[i][1]-j;
						}else {
							nx = x + dir[i][0];
							ny = y + dir[i][1]+j;
						}
						if(nx>-1 && ny>-1 && nx<map.length && ny<map[0].length) {
							if(!sp.contains(temp+map[nx][ny])) {
								continue;
							}
							if(!chk[nx][ny]) {
								continue;
							}
							getResult(nx, ny, temp+map[nx][ny]);
						}
					}
				}
			}else {
				return;
			}
		}
		
	}

}
