import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3184 {

	public static int n;
	public static int m;
	public static char[][] map;
	public static boolean[][] chk;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int resultO = 0;
	public static int resultV = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input3184.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		chk = new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]!='#' && !chk[i][j]) {
					chkAnimal(i,j);
				}
			}
		}
		
		System.out.println(resultO + " " + resultV);
		
		
		br.close();
	}
	
	public static void chkAnimal(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(i,j));
		chk[i][j] = true;
		
		Point poll;
		int x,y,nx,ny;
		int countO=0, countV=0;
		while(!q.isEmpty()) {
			poll = q.poll();
			x = poll.x;
			y = poll.y;
			if(map[x][y]=='v') countV++;
			else if(map[x][y]=='o') countO++;
			for(int k=0; k<4; k++) {
				nx = x + dir[k][0];
				ny = y + dir[k][1];
				if(nx>-1 && ny>-1 && nx<n && ny<m && map[nx][ny]!='#' && !chk[nx][ny]) {
					chk[nx][ny] = true;
					q.add(new Point(nx,ny));
				}
			}
		}
		
		if(countV<countO) {
			resultO += countO;
		}else {
			resultV += countV;
		}
	}
	
	

}
