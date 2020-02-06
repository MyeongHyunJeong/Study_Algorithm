import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {
		String[][] chk = new String[3][4];
		chk[0][0] = "" + 'U';
		System.out.println(Arrays.deepToString(chk));
		if(chk[0][1]==null) {
			System.out.println("hi");
		}
	}

}
