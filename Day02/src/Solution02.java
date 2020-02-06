import java.io.FileInputStream;
import java.util.Scanner;

public class Solution02 {
	
	public static int k;
	public static int n;
	public static int boom = 210;
	public static int time = 0;
	

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input02.txt"));
		Scanner s = new Scanner(System.in);
		
		k = s.nextInt();
		n = s.nextInt();
		String q = "";
		
		for(int i=0; i<n; i++) {
			time += s.nextInt();
			q = s.next();
			if(time >= boom) {
				break;
			}else {
				if(q.equals("T")) {
					k += 1;
					if(k==9) {
						k = 1;
					}
				}
			}
		}
		
		System.out.println(k);
		
	}
	
//	public static class Hold {
//		int p;
//
//		public Hold(int p) {
//			this.p = p;
//		}
//
//		public int getP() {
//			return p;
//		}
//
//		public void setP(int p) {
//			this.p = p;
//		}
//	}

}
