import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13460_5 {
	
	public static int n;
	public static int m;
	public static char[][] map;
	public static Ball red;
	public static Ball blue;
	public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	public static int result = Integer.MAX_VALUE;
	
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
					red = new Ball(i,j,'R');
					map[i][j] = '.';
				}else if(map[i][j]=='B') {
					blue = new Ball(i,j,'B');
					map[i][j] = '.';
				}
			}
		}
		
		for(int k=0; k<4; k++) {
			moveBall(red.x,red.y,blue.x,blue.y,1,k);
		}
		if(result==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
		
		br.close();
	}
	
	public static void moveBall(int rx, int ry, int bx, int by, int count, int direction) {
		if(count>10) return;
		
		Ball first=new Ball(rx,ry,'R'), second=new Ball(bx,by,'B'),temp;
		if(direction==0) {	//상
			if(ry==by) {
				if(rx>bx) {
					temp = first;
					first = second;
					second = temp;
				}
			}
		}else if(direction==1) {	//하
			if(ry==by) {
				if(rx<bx) {
					temp = first;
					first = second;
					second = temp;
				}
			}
		}else if(direction==2) {	//좌
			if(rx==bx) {
				if(ry>by) {
					temp = first;
					first = second;
					second = temp;
				}
			}
		}else {	//우
			if(rx==bx) {
				if(ry<by) {
					temp = first;
					first = second;
					second = temp;
				}
			}
		}
		
		//move first
		int nx=first.x,ny=first.y;
		while(true) {
			nx = nx + dir[direction][0];
			ny = ny + dir[direction][1];
			if(nx>-1 && ny>-1 && nx<n && ny<m) {
				if(map[nx][ny]=='.') continue;
				else if(map[nx][ny]=='#') {
					first.x = nx - dir[direction][0];
					first.y = ny - dir[direction][1];
					break;
				}else if(map[nx][ny]=='O') {
					first.x = nx;
					first.y = ny;
					break;
				}
			}
		}
		//move second
		nx=second.x;
		ny=second.y;
		while(true) {
			nx = nx + dir[direction][0];
			ny = ny + dir[direction][1];
			if(nx>-1 && ny>-1 && nx<n && ny<m) {
				if(nx==first.x && ny==first.y) {
					if(map[nx][ny]=='O') {
						second.x = nx;
						second.y = ny;
						break;
					}else {
						second.x = nx - dir[direction][0];
						second.y = ny - dir[direction][1];
						break;
					}
				}else if(map[nx][ny]=='#') {
					second.x = nx - dir[direction][0];
					second.y = ny - dir[direction][1];
					break;
				}else if(map[nx][ny]=='O') {
					second.x = nx;
					second.y = ny;
					break;
				}else if(map[nx][ny]=='.') continue;
			}
		}
		
		if(first.color=='R') {
			if(map[second.x][second.y]=='O') return;
			if(map[first.x][first.y]=='O') {
				if(count<result) result = count;
//				System.out.println(count);
				return;
			}
		}else {
			if(map[first.x][first.y]=='O') return;
			if(map[second.x][second.y]=='O') {
				if(count<result) result = count;
//				System.out.println(count);
				return;
			}
		}
		
		for(int k=0; k<4; k++) {
			if(direction==k) continue;
			if(direction==0) {
				if(k==1) continue;
			}else if(direction==1) {
				if(k==0) continue;
			}else if(direction==2) {
				if(k==3) continue;
			}else {
				if(k==2) continue;
			}
			
			if(first.color=='R') moveBall(first.x, first.y, second.x, second.y, count+1, k);
			else moveBall(second.x, second.y, first.x, first.y, count+1, k);
		}
		
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
