import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution04 {
	
	public static String[][] transactions = {
	                                         {"1", "SAVE", "ACCOUNT2", "100"},
	                                         {"2", "WITHDRAW", "ACCOUNT1", "50"}, 
	                                         {"1", "SAVE", "ACCOUNT2", "100"}, 
	                                         {"4", "SAVE", "ACCOUNT3", "500"}, 
	                                         {"3", "WITHDRAW", "ACCOUNT2", "30"}
	                                       };
	public static String[][] snapshots = {
			{"ACCOUNT4", "100"}, 
{"ACCOUNT1", "150"},
{"ACCOUNT2", "100"}, 
{"ACCOUNT3", "100"}, 
{"ACCOUNT5", "100"}
										};
	
	public static boolean[] chk;
	
	public static void main(String[] args) {
		solution(snapshots, transactions);
	}
	
	public static String[][] solution(String[][] snapshots, String[][] transactions) {
        String[][] answer = {};
        PriorityQueue<User> q = new PriorityQueue<User>(new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.name.compareTo(o2.name)>0?1:-1;
			}
		});
        for(int i=0; i<snapshots.length; i++) {
        	q.add(new User(snapshots[i][0], snapshots[i][1]));
        }
        
        chk = new boolean[100000];
        int id, money;
        String action, account;
        boolean flag;
        for(int i=0; i<transactions.length; i++) {
        	id = Integer.parseInt(transactions[i][0]);
        	action = transactions[i][1];
        	account = transactions[i][2];
        	money = Integer.parseInt(transactions[i][3]);
        	if(!chk[id]) {
        		chk[id] = true;
        		flag = false;
        		for(User u : q) {
        			if(u.name.equals(account)) {
        				flag = true;
        				if(action.equals("SAVE")) {
        					u.money = ""+(Integer.parseInt(u.money) + money); 
        				}else {
        					u.money = ""+(Integer.parseInt(u.money) - money);
        				}
        			}
        		}
        		if(!flag) {
        			q.add(new User(account, ""+money));
        		}
        	}
        }
        
        int size = q.size();
        answer = new String[size][2];
        User poll;
        for(int i=0; i<size; i++) {
        	poll = q.poll();
        	answer[i][0] = poll.name;
        	answer[i][1] = poll.money;
        }
        
//        for(int i=0; i<answer.length; i++) {
//        	System.out.println(answer[i][0] + " " + answer[i][1]);
//        }
        
        
        return answer;
    }
	
	public static class User {
		String name;
		String money;
		public User(String name, String money) {
			this.name = name;
			this.money = money;
		}
	}

}
