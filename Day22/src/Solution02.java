public class Solution02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] temp = {"3241523133","4121314445","3243523133","4433325251","2412313253"};
		solution("4132315142", temp);

	}
	public static int solution(String answer_sheet, String[] sheets) {
		int answer = -1;

		boolean[][] chk = new boolean[sheets.length][answer_sheet.length()];
		boolean[][] resultChk = new boolean[sheets.length][answer_sheet.length()];
		char correct;
		for(int i=0; i<answer_sheet.length(); i++) {
			correct = answer_sheet.charAt(i);
			for(int j=0; j<sheets.length; j++) {
				if(correct!=sheets[j].charAt(i)) {
					chk[j][i] = true;
				}
			}
		}
		int countA = 0;
		int countB = Integer.MIN_VALUE;
		int countWrong = 0;
		int max = Integer.MIN_VALUE;
		int cal = 0;
		for(int i=0; i<chk.length; i++) {
			for(int j=0; j<chk.length; j++) {
				if(i==j) continue;
				for(int k=0; k<chk[i].length; k++) {
					if(chk[i][k] && chk[j][k]) {
						if(sheets[i].charAt(k)==sheets[j].charAt(k)) {
							resultChk[i][k] = true;
						}
					}
				}
				
				countA = 0;
				countB = 0;
				countWrong = 0;
				cal = 0;
				for(int k=0; k<resultChk[i].length; k++) {
					if(resultChk[i][k]) {
						countA++;
					}else {
						if(countA>countB) {
							countB = countA;
						}
						countWrong += countA;
						countA = 0;
					}
				}
				if(countA>0) {
					countWrong += countA;
					if(countA>countB) {
						countB = countA;
						countA = 0;
					}
				}
				cal = countWrong + (int)Math.pow(countB, 2);
				if(cal>answer) answer = cal;
//				System.out.println(countWrong + " " + countB);

//				for(int k=0; k<resultChk[i].length; k++) {
//					System.out.print(resultChk[i][k] + " " );
//				}System.out.println();
				resultChk = new boolean[sheets.length][answer_sheet.length()];
			}
		}




//		System.out.println(answer);

		return answer;
	}

}
