import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2493 {

	public static int n;
	public static int[] map;
	public static int[] result;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input2493.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n];
		st = new StringTokenizer(br.readLine());
		map[0] = Integer.parseInt(st.nextToken());
		result = new int[n];
		result[0] = 0;
		int past = map[0], idx = 0;
		System.out.print("0 ");
		int max = map[0];
		for(int i=1; i<n; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			if(past>map[i]) {
				System.out.print((idx+1) + " ");
				result[i] = idx;
				past = map[i];
				idx = i;
			}else {
				if(max<map[i]) {
					System.out.print("0 ");
					result[i] = 0;
					max = map[i];
				}else {
					for(int j=result[idx]; ; j=result[j]) {
						if(map[j]>=map[i]) {
							System.out.print((j+1) + " ");
							result[i] = j;
							break;
						}
					}
				}
				past = map[i];
				idx = i;
			}
		}

		br.close();
	}

}
