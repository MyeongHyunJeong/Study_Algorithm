import java.io.FileInputStream;
import java.util.Scanner;

public class Main2186_2 {

	public static int n;
	public static int m;
	public static int k;
	public static char[][] map;
	public static String st;
	public static boolean[][] chk;
	public static int result;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2186.txt"));
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		m = s.nextInt();
		k = s.nextInt();
		map = new char[n][m];
		for(int i=0; i<map.length; i++) {
			map[i] = s.next().toCharArray();
		}
		st = s.next();
		chk = new boolean[n][m];
		result = 0;
		for(int i=0; i<map.length; i++) {		//단어에 들어있으면 true
			for(int j=0; j<map[i].length; j++) {
				if(st.contains(""+map[i][j])) {
					chk[i][j] = true;
				}
			}
		}
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j]==st.charAt(0)) {	//첫단어랑 똑같으면
					getString(i, j, 0);
				}
			}
		}
		System.out.println(result);
		
		
	}
	
	public static void getString(int x, int y, int idx) {
		if(idx==st.length()-1) {		//제일 마지막까지 같으면 result++, 아니면 return
			if(map[x][y]==st.charAt(idx)) {
				result++;
				return;
			}else {
				return;
			}
		}
		if(idx<st.length()-1) {
			int nx = 0;
			int ny = 0;
			for(int i=0; i<dir.length; i++) {
				for(int j=1; j<=k; j++) {
					nx = x + dir[i][0]*j;
					ny = y + dir[i][1]*j;
					if(nx>-1 && ny>-1 && nx<map.length && ny<map[0].length) {
						if(chk[nx][ny] && map[nx][ny]==st.charAt(idx+1)) {
							getString(nx, ny, idx+1);
						}else {
							continue;
						}
					}else {
						break;
					}
				}
			}
			return;
		}
	}

}
