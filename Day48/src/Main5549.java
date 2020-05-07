import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5549 {
	
	public static int[][][] map;
	public static int n;
	public static int m;
	public static int k;
	public static Point start;
	public static Point end;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5549.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		map = new int[n][m][3];
		String temp = "";
		int jungle = 0;
		int ocean = 0;
		int ice = 0;
		for(int i=0; i<n; i++) {
			temp = br.readLine();
			for(int j=0; j<m; j++) {
				if(temp.substring(j,j+1).equals("J")) {
					map[i][j][0] = ++jungle;
					map[i][j][1] = ocean;
					map[i][j][2] = ice;
				}else if(temp.substring(j,j+1).equals("O")) {
					map[i][j][1] = ++ocean;
					map[i][j][0] = jungle;
					map[i][j][2] = ice;
				}else {
					map[i][j][2] = ++ice;
					map[i][j][1] = ocean;
					map[i][j][0] = jungle;
				}
			}
		}
//		System.out.println(map[0][1][0]);
//		System.out.println(map[0][1][1]);
//		System.out.println(map[0][1][2]);
		
		start = new Point(0,0);
		end = new Point(0,0);
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			start.x = Integer.parseInt(st.nextToken())-1;
			start.y = Integer.parseInt(st.nextToken())-1;
			end.x = Integer.parseInt(st.nextToken())-1;
			end.y = Integer.parseInt(st.nextToken())-1;
			jungle = map[start.x][end.y][0]-map[start.x][start.y][0] + map[end.x][end.y][0]-map[end.x][start.y][0];
			System.out.println(jungle);
		}
		
		
		br.close();
	}

}
