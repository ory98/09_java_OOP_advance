package step9_02.atm_v2;

public class User {
	
	Account[] acc = new Account[UserManager.getInstance().ACC_MAX_CNT];	// 최대 개설 수
	int accCnt;	

	String id;											
	String pw;											
	
}


