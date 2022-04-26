package step9_01.atm_v1;

import java.util.Scanner;

public class UserManager { // 사용자 관리 
	
	Scanner scan = new Scanner(System.in);
	User[] user = null; 
	int userCount = 0; // 사용자 수 
	
	void printAllUser() { // 모든 사용자 출력 
		for(int i = 0; i < userCount; i++) {
			user[i].printAccount();
		}
	}
	
	
	
	void addUser() { // 회원가입 
		
		if(userCount == 0) { // 사용자가 0명 이면 
			user = new User[1]; 
		}
		else { // 0명이 아니면 
			User[] temp = user; 
			user = new User[userCount + 1]; // +1 추가된 새로운 배열 생성 
			for(int i = 0; i < userCount; i++) { 
				user[i] = temp[i]; // 내용 옮기기 
			}
			temp = null;
		}
		
		
		System.out.print("[가입] 아이디를 입력하세요 : "); 
		String id = scan.next();
		
		boolean isDuple = false; 
		for (int i = 0; i < userCount; i++) {
			if (user[i].id.equals(id)) {
				isDuple = true;
			}
		}
		if (!isDuple) { 
			user[userCount] = new User();
			user[userCount].id = id;
			System.out.println("[메시지] ID : '" + id+ "' 가입 되었습니다.\n");
			userCount++;
		}
		else { 
			System.out.println("[메시지] '"+ id + "'은 이미 가입된 아이디 입니다.\n");
		}
		
	}
	
	User getUser(int idx) {
		
		return user[idx];
	}
	
	
	
	
	int logUser() { // 로그인 
		
		int identifier = -1;
		System.out.print("[입력] 아이디를 입력하세요 : ");
		String name = scan.next();
		
		for (int i = 0; i < userCount; i++) {
			if (name.equals(user[i].id)) { 
				identifier = i;
				System.out.println("[" + user[identifier].id + "] 님 로그인.\n");
			}
		}
		
		return identifier;
		
	}
	
	
	
	void leave() { // 회원 탈퇴
		
		System.out.print("[입력] 탈퇴할 아이디를 입력하세요 : ");
		String name = scan.next();
		int identifier = -1;
		for (int i = 0; i < userCount; i++) {
			if (name.equals(user[i].id)) {
				identifier = i;			
			}
		}
		
		if(identifier == -1) {
			System.out.println("[메시지] 아이디를 다시 확인하세요.");
			return;
		}
		
		System.out.println("ID : '" +user[identifier].id + "' 가 탈퇴되었습니다.");
		
		User[] temp = user;
		user = new User[userCount - 1]; 
		
		for(int i = 0; i < identifier; i++) {
			user[i] = temp[i]; // 0~i까지 temp 내용 가져오기 
		}
		for(int i =identifier; i < userCount-1; i++) { 
			user[i] =temp[i + 1]; // i ~ identifier-1까지 한칸씩 왼쪽으로 옮겨 내용 가져오기 
		}
		
		userCount--;
		
	}
	
}
