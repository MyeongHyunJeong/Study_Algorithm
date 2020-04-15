import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1062 {
	
	public static int n;
	public static int m;
	public static String canRead;
	public static String[] read;
	public static int[] ss;
	public static int max;
	public static ArrayList<Character> study;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1062.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		if(m<5) {
			System.out.println(0);
			return;
		}
		
		
		read = new String[n];
		canRead = "antic";
		String temp = "";
		study = new ArrayList<Character>();
		for(int i=0; i<n; i++) {
			temp = br.readLine();
			read[i] = temp;
			for(int j=4; j<temp.length()-4; j++) {
				if(!canRead.contains(""+temp.charAt(j))) {
					canRead += temp.charAt(j);
					study.add(temp.charAt(j));
				}
			}
		}
		
//		for(char c : study) {
//			System.out.println(c);
//		}
		
		canRead = "antic";
		max = Integer.MIN_VALUE;
		if(m-5>study.size()) {
			ss = new int[m-5];
			studySpell(study.size(), study.size(), 0, 0);
		}else {
			ss = new int[study.size()];
			studySpell(study.size(), m-5, 0, 0);
		}
		System.out.println(max);
		
	}
	
	public static void studySpell(int N, int r, int idx, int depth) {
		if(idx==r) {
//			System.out.println(Arrays.toString(ss));
			int count = n;
			for(int i=0; i<r; i++) {
				canRead += study.get(ss[i]);
			}
//			System.out.println(canRead);
			for(int j=0; j<n; j++) {
//				System.out.println(read[j]);
				for(int k=4; k<read[j].length()-4; k++) {
					if(!canRead.contains(""+read[j].charAt(k))) {
						count--;
						break;
					}
				}
			}
			if(count>max) max = count;
			canRead = "antic";
			return;
		}
		if(depth==N) return;
		
		ss[idx] = depth;
		studySpell(N,r,idx+1, depth+1);
		studySpell(N,r,idx, depth+1);
	}

}
