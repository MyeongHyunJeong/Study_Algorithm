import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15684 {
	
	public static int n;
	public static int m;
	public static int h;
	public static boolean[][] map;
	public static boolean pos;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input15684.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		 
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new boolean[h+1][n+1];
		int x, y;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			map[x][y] = true;
		}
//		printMap();
		
		pos = false;
		//다리 개수는 최대 3개(0개 부터 시작)
		for(int i=0; i<4; i++) {
			go(1,1,0,i);
			if(pos) {
				System.out.println(i);
				break;
			}
		}
		if(!pos) {
			System.out.println(-1);
		}
		
		
	}
	
	public static void go(int x, int y, int draw, int bri) {
		if(draw==bri) {
			if(chkMap()) {
				pos = true;
			}else {
				pos = false;
			}
			return;
		}
		if(pos) {
			return;
		}
		if(y>n-1) {
			x++;
			y=1;
		}
		if(x>h) {
			return;
		}
		
		if(!map[x][y]) {
			if(map[x][y-1] || map[x][y+1]) {	//양옆에 줄이 그어져 있으면 못그림
				go(x,y+1,draw,bri);
			}else {
				map[x][y] = true;	//선을 그리는 경우
				go(x,y+1,draw+1,bri);
				
				map[x][y] = false;	//안그리는 경우
				go(x,y+1,draw,bri);
			}
		}else {
			go(x,y+1,draw,bri);
		}
		
	}
	
	public static boolean chkMap() {
		int current, end;
		for(int i=1; i<n+1; i++) {
			current = i;
			end = i;
			for(int j=1; j<h+1; j++) {
				if(map[j][current]) {	//true면 오른쪽라인으로 이동
					current++;
				}else if(map[j][current-1]) {	//왼쪽이 true면 왼쪽라인으로 이동
					current--;
				}
			}
			if(current!=end) {
				return false;
			}
		}
		return true;
	}
	
	public static void printMap() {
		for(int i=0; i<h+1; i++) {
			for(int j=0; j<n+1; j++) {
				System.out.print(map[i][j] + "\t");
			}System.out.println();
		}
	}

}
