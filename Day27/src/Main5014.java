import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5014 {
	
	public static int f;
	public static int s;
	public static int g;
	public static int u;
	public static int d;
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input5014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		f = Integer.parseInt(st.nextToken());	//총 높이
		s = Integer.parseInt(st.nextToken());	//현재 층
		g = Integer.parseInt(st.nextToken());	//가야할 층
		u = Integer.parseInt(st.nextToken());	//올라가는 버튼
		d = Integer.parseInt(st.nextToken());	//내려가는 버튼
		
		int upstairs = g-s;
		int count = 0;
		if(upstairs==0) {
			result = 0;
		}else if(upstairs>0) {
			while(s<g) {
				if(u==0) break;
				s += u;
				count++;
				if(s==g) {
					result = count;
					break;
				}
			}
			while(s>g) {
				if(d==0) break;
				s -= d;
				count++;
				if(s==g) {
					result = count;
					break;
				}
			}
		}else {
			while(s>g) {
				if(d==0) break;
				s -= d;
				count++;
				if(s==g) {
					result = count;
					break;
				}
			}
			while(s<g) {
				if(u==0) break;
				s += u;
				count++;
				if(s==g) {
					result = count;
					break;
				}
			}
		}
		if(result == Integer.MAX_VALUE) System.out.println("use the stairs");
		else System.out.println(result);
	}

}
