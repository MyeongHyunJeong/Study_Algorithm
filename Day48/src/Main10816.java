import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10816 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input10816.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] temp = new int[20000001];
		int n = Integer.parseInt(br.readLine());
		int a = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			a = Integer.parseInt(st.nextToken());
			if(a<0) {
				temp[(-a)+10000000]++;
			}else {
				temp[a]++;
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			a = Integer.parseInt(st.nextToken());
			if(a<0) {
				System.out.print(temp[(-a)+10000000] + " ");
			}else {
				System.out.print(temp[a] + " ");
			}
		}
		
		br.close();
	}

}
