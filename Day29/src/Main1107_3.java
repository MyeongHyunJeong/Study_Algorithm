import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1107_3 {

	public static int wantCH;
	public static int errCount;
	public static boolean[] err;
	public static int click;
	public static ArrayList<Integer> pClick;
	public static int n;
	public static int r;
	public static int[] tempN;
	public static boolean[] chk;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input1107.txt"));
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));

		wantCH = Integer.parseInt(br.readLine());
		errCount = Integer.parseInt(br.readLine());
		err = new boolean[10];

		click = Math.abs(wantCH - 100);
		if(errCount==10) {
			System.out.println(click);
			br.close();
			return;
		}else if(wantCH==100) {
			System.out.println(0);
			br.close();
			return;
		}else {
			if(errCount!=0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int temp = 0;
				for(int i=0; i<errCount; i++) {
					temp = Integer.parseInt(st.nextToken());
					err[temp] = true;
				}
			}
			pClick = new ArrayList<Integer>();
			for(int i=0; i<10; i++) {
				if(!err[i]) pClick.add(i);
			}

			n = pClick.size();
			for(int i=1; i<=(wantCH+"").length()+1; i++) {
				r = i;
				tempN = new int[r];
				chk = new boolean[n];
				doClick(0,0);
			}
			System.out.println(click);
		}
		br.close();
	}

	public static void doClick(int idx, int depth) {
		if(idx==r) {
			//			System.out.println(Arrays.toString(tempN));
			String clickNum = "";
			for(int i=0; i<r; i++) {
				clickNum += pClick.get(tempN[i]);
			}
			int clickN = Integer.parseInt(clickNum);
//						System.out.println(clickN);
//						System.out.println("click :" + clickN);
			int count = (clickN+"").length() + Math.abs(wantCH-clickN);
//						System.out.println("count : " + count);
			if(count<click) {
				//				System.out.println(clickNum);
				click = count;
			}
			return;
		}
		if(depth==n) return;

		for(int i=0; i<n; i++) {
			tempN[idx] = i;
			doClick(idx+1, depth);
		}
	}

}
