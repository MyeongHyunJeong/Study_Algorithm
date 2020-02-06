import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2186_3 {
	
	public static int n;
	public static int m;
	public static int k;
	public static char[][] map;
	public static String word;
	public static int[][][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2186.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		word = br.readLine();
		chk = new int[n][m][word.length()];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				Arrays.fill(chk[i][j], -1);;
			}
		}
		result = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				result += getResult(i, j, 0);
			}
		}
		
		System.out.println(result);
		br.close();
	}
	
	public static int getResult(int x, int y, int idx) {
		if(idx==word.length()-1) {
			return 1;
		}
		if(chk[x][y][idx]!=-1) {
			return chk[x][y][idx];
		}
		if(map[x][y]!=word.charAt(idx)) {
			return chk[x][y][idx]=0;
		}
		
		int nx = 0;
		int ny = 0;
		chk[x][y][idx] = 0;
		for(int i=0; i<4; i++) {
			for(int j=1; j<=k; j++) {
				nx = x + dir[i][0]*j;
				ny = y + dir[i][1]*j;
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]==word.charAt(idx+1)) {
					chk[x][y][idx] += getResult(nx, ny, idx+1);
				}
			}
		}
		return chk[x][y][idx];
	}

}
