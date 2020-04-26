import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main02 {

	public static String[] team;
	public static String[] participate;
	public static Set<String> set;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input02.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		team = br.readLine().split(" ");
		participate = br.readLine().split(" ");

//		System.out.println(Arrays.toString(team));
//		System.out.println(Arrays.toString(participate));

		int count = 0;
		set = new HashSet<String>();
		boolean[] chk = new boolean[participate.length];
		for(int i=0; i<team.length; i++) {
			set.add(team[i]);
		}
		for(int i=0; i<participate.length; i++) {
			if(set.contains(participate[i])) {
				continue;
			}else {
				set.add(participate[i]);
				chk[i] = true;
				count++;
			}
		}
		
		String[] answer = new String[count];
		int idx = 0;
		for(int i=0; i<chk.length; i++) {
			if(chk[i]) {
				answer[idx] = participate[i];
				idx++;
			}
		}
		
		System.out.println(Arrays.toString(answer));

		br.close();
	}

}
