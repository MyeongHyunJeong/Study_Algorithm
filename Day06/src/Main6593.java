import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main6593 {

	public static int L;
	public static int R;
	public static int C;
	public static char[][][] map;
	public static boolean[][][] chk;
	public static int[][][] numbering;
	public static Node start;
	public static Node end;
	public static int result;
	public static int[] dirL = {1,-1,0,0,0,0};
	public static int[] dirR = {0,0,1,-1,0,0};
	public static int[] dirC = {0,0,0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input6593.txt"));
		Scanner s = new Scanner(System.in);
		
		while(true) {
			L = s.nextInt();
			R = s.nextInt();
			C = s.nextInt();
			if(L==0 && R==0 && C==0) {
				break;
			}
			
			map = new char[L][R][C];
			chk = new boolean[L][R][C];
			numbering = new int[L][R][C];
			String temp = "";
			for(int l=0; l<map.length; l++) {
				for(int r=0; r<map[l].length; r++) {
					temp = s.next();
					map[l][r] = temp.toCharArray();
					for(int c=0; c<map[l][r].length; c++) {
						if(map[l][r][c]=='S') {
							start = new Node(l,r,c,0);
						}else if(map[l][r][c]=='E') {
							end = new Node(l,r,c,0);
						}else if(map[l][r][c]=='#') {
							chk[l][r][c] = true;
						}
					}
				}
			}
			
			result = 0;
			getResult();
			
			if(numbering[end.l][end.r][end.c]==0) {
				System.out.println("Trapped!");
			}else {
				System.out.println("Escaped in " + numbering[end.l][end.r][end.c] + " minute(s).");
			}
			
		}
		
	}
	
	public static void getResult() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(start);
		chk[start.l][start.r][start.c] = true;
		numbering[start.l][start.r][start.c] = start.depth; 
		Node poll;
		int l;
		int r;
		int c;
		int depth;
		int nl;
		int nr;
		int nc;
		while(!q.isEmpty()) {
			poll = q.poll();
			l = poll.l;
			r = poll.r;
			c = poll.c;
			depth = poll.depth;
			for(int d=0; d<dirL.length; d++) {
				nl = l + dirL[d];
				nr = r + dirR[d];
				nc = c + dirC[d];
				if(nl>-1 && nr>-1 && nc>-1 && nl<map.length && nr<map[0].length && nc<map[0][0].length && !chk[nl][nr][nc]) {
					q.add(new Node(nl,nr,nc,depth+1));
					numbering[nl][nr][nc] = depth+1;
					chk[nl][nr][nc] = true;
				}
			}
		}
		
	}
	
	public static class Node {
		int l;
		int r;
		int c;
		int depth;
		public Node(int l, int r, int c, int depth) {
			this.l = l;
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
	}

}
