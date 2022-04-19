package step9_01.atm_v1;

public class Account { // 계좌 
	
	String number = "";
	int money = 0;
	
	void printOwnAccount() {
		System.out.println(number +  " : " + money);
	}
	
}
