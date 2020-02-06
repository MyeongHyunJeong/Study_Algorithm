import java.io.FileInputStream;
import java.util.Scanner;

public class Solution01 {
	
	public static int n;
	public static Node K;
	public static Node S;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input01.txt"));
		Scanner s = new Scanner(System.in);
		String tempK = s.next();
		String tempS = s.next();
		n = s.nextInt();
		int xk, yk, xs, ys;
		xk = tempK.charAt(0)-64;
		yk = tempK.charAt(1)-48;
		xs = tempS.charAt(0)-64;
		ys = tempS.charAt(1)-48;
		K = new Node(yk, xk);
		S = new Node(ys, xs);
//		System.out.println(xk + " " + yk + " " + xs +  " " + ys);
//		System.out.println(convertDir("R"));
		
		for(int i=0; i<n; i++) {
			move(s.next());
		}
		
//		System.out.println(K.x + " " + K.y);
//		System.out.println(S.x + " " + S.y);
		String resultK = Character.toString((char)K.y);
		System.out.println(result(K.y)+K.x);
		System.out.println(result(S.y)+S.x);
		
		
	}
	
	public static String result(int y) {
		if(y==1) {
			return "A";
		}else if(y==2) {
			return "B";
		}else if(y==3) {
			return "C";
		}else if(y==4) {
			return "D";
		}else if(y==5) {
			return "E";
		}else if(y==6) {
			return "F";
		}else if(y==7) {
			return "G";
		}else {
			return "H";
		}
	}
	
	
	public static void move(String dir) {
		Node m = convertDir(dir);
		int x = K.x + m.x;
		int y = K.y + m.y;
		int movesx = S.x + m.x;
		int movesy = S.y + m.y;
		if(x>0 && y>0 && x<9 && y<9) {
			if(x==S.x && y==S.y) {
				if(movesx>0 && movesy>0 && movesx<9 && movesy<9) {
					S.setX(movesx);
					S.setY(movesy);
					K.setX(x);
					K.setY(y);
				}
			}else {
				K.setX(x);
				K.setY(y);
			}
		}
	}
	
	public static Node convertDir(String dir) {
		if(dir.equals("R")) {
			return new Node(0,1);
		}else if(dir.equals("L")) {
			return new Node(0,-1);
		}else if(dir.equals("B")) {
			return new Node(-1,0);
		}else if(dir.equals("T")) {
			return new Node(1,0);
		}else if(dir.equals("RT")) {
			return new Node(1,1);
		}else if(dir.equals("LT")) {
			return new Node(1,-1);
		}else if(dir.equals("RB")) {
			return new Node(-1,1);
		}else {
			return new Node(-1,-1);
		}
	}
	
	public static class Node {
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
	}

}
