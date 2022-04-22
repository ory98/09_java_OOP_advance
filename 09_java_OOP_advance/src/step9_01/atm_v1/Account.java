package step9_01.atm_v1;

public class Account { // 계좌 
	
	String number = "";
	int money = 0;
	
	void printOwnAccount() { // 자신의 계좌 출력 
		System.out.println(number +  " : " + money); 
	}
	
}
