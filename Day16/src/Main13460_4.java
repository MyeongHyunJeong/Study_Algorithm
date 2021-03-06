import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13460_4 {
	
	public static int n;
	public static int m;
	public static char[][] map;
	public static Ball red;
	public static Ball blue;
	public static boolean rgoal;
	public static boolean bgoal;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result = Integer.MAX_VALUE;
	public static boolean[][][] chk;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				if(map[i][j]=='R') {
					red = new Ball(i,j,'r'); 
					map[i][j] = '.';
				}
				else if(map[i][j]=='B') {
					blue = new Ball(i,j,'b');
					map[i][j] = '.';
				}
			}
		}
		printMap();
		
		
		chk = new boolean[n][m][4];
		moveBall(red, blue, 0);
		System.out.println(result);
		
		br.close();
	}
	
	public static void moveBall(Ball r, Ball b, int depth) {
		if(depth>10) return;
		
		Ball first, second;
		int rx,ry,bx,by;
		rx = r.x;
		ry = r.y;
		bx = b.x;
		by = b.y;
		first = r;
		second = b;
		for(int k=0; k<4; k++) {
			bgoal = false;
			rgoal = false;
			if(chk[rx][ry][k]) continue;
			//먼저 움직일 것 선택
			if(k==0) {
				if(rx<bx) {
					first = r;
					second = b;
				}else {
					first = b;
					second = r;
				}
			}else if(k==1) {
				if(rx>bx) {
					first = r;
					second = b;
				}else {
					first = b;
					second = r;
				}
			}else if(k==2) {
				if(ry<by) {
					first = r;
					second = b;
				}else {
					first = b;
					second = r;
				}
			}else if(k==3) {
				if(ry<by) {
					first = r;
					second = b;
				}else {
					first = b;
					second = r;
				}
			}
			
			int nx,ny;
			nx = first.x;
			ny = first.y;
			if(first.color=='r') chk[nx][ny][k] = true;
			while(true) {	//첫번째 공 움직임
				nx = nx + dir[k][0];
				ny = ny + dir[k][1];
				if(map[nx][ny]=='.') {
					first.x = nx;
					first.y = ny;
					if(first.color=='r') chk[nx][ny][k] = true;
				}else if(map[nx][ny]=='#') {
					break;
				}else if(map[nx][ny]=='O') {
					first.x = nx;
					first.y = ny;
					if(first.color=='r') chk[nx][ny][k] = true;
					if(first.color=='r') rgoal = true;
					else bgoal = true;
					break;
				}
			}
			
			nx = second.x;
			ny = second.y;
			if(second.color=='r') chk[nx][ny][k] = true;
			while(true) {	//두번째 공 움직임
				nx = nx + dir[k][0];
				ny = ny + dir[k][1];
				if(nx==first.x &&  ny==first.y) break;
				if(map[nx][ny]=='.') {
					second.x = nx;
					second.y = ny;
					if(second.color=='r') chk[nx][ny][k] = true;
				}else if(map[nx][ny]=='#') {
					break;
				}else if(map[nx][ny]=='O') {
					second.x = nx;
					second.y = ny;
					if(second.color=='r') chk[nx][ny][k] = true;
					if(second.color=='r') rgoal = true;
					else bgoal = true;
					break;
				}
			}
			
			if(bgoal) continue;
			else if(rgoal) {
				if(result>depth) result = depth;
				continue;
			}
			
			if(first.color=='r') moveBall(first, second, depth+1);
			else moveBall(second, first, depth+1);
		}
		
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}System.out.println();
	}
	
	public static class Ball {
		int x;
		int y;
		char color;
		public Ball(int x, int y, char color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

}
