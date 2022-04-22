package step9_01.atm_v1;

public class User {
	
	String id;
	int accCount;
	Account[] acc; // 번호랑 금액의 배열 
	
	void printAccount() { // 계좌 출력 객체 
		
		for (int i = 0; i < accCount; i++) { // 범위안에서 
			acc[i].printOwnAccount(); // 자신의 계좌 다 출력 (number +  " : " + money) 
		}	
		
	}
	
}
