package step9_01.atm_v1;
import java.util.Random;
import java.util.Scanner;

public class ATM {
	
	Scanner scan = new Scanner(System.in);
	Random ran   = new Random();
	UserManager userManager = new UserManager();
	int identifier = -1;
	
	void printMainMenu() { 
				
		while (true) {
			
			System.out.println("\n[ MEGA ATM ]");
			System.out.print("[1.로그인] [2.로그아웃] [3.회원가입] [4.회원탈퇴] [0.종료] : ");
			int sel = scan.nextInt();
			
			if      (sel == 1) 	    login();
			else if (sel == 2) 		logout();
			else if (sel == 3) 		join();
			else if (sel == 4) 		leave();
			else if (sel == 0) 		break;
			
		}
		
		System.out.println("[메시지] 프로그램을 종료합니다.");
		
	}
	
	
	void login() { // 로그인 
		
		identifier = userManager.logUser(); // 아이디는 유저매니저의 로그유저
		
		if (identifier != -1) { // 아이디가 없지 않고, 입력한 아이디와 기존 아이디가 같을 때 
			printAccountMenu(); // 계좌메뉴 불러오기 
		}
		else { // 아이디가 없거나 입력한 아이디와 기존 아이디가 다를경우
			System.out.println("[메세지] 로그인실패."); 
		}
		
	}
	
	
	void join() { // 회원가입 
		
		userManager.addUser();
	}
	
	
	void logout() { // 로그아웃 
		
		if (identifier == -1) { // 입력된 아이디가 없을 경우
			System.out.println("[메시지] 로그인을 하신 후 이용하실 수 있습니다.");
		}
		else { 
			identifier = -1; // 아이디가 입력되지 않는 상태가 됨
			System.out.println("[메시지] 로그아웃 되었습니다.");
		}
		
	}
	
	
	void leave() { // 회원탈퇴
		
		userManager.leave();
		
	}
	
	
	void printAccountMenu() { // 계좌 메뉴 출력 
		
		while (true) {
			
			System.out.print("[1.계좌생성] [2.계좌삭제] [3.조회] [0.로그아웃] : ");
			int sel = scan.nextInt();
			
			String makeAccount = Integer.toString(ran.nextInt(90001) + 10000); 
			// 계좌 생성 >  문자열이였던 계좌를 숫자로 바꿔줌 > 
			 
			
			if (sel == 1) { // 계좌생성 
				if (userManager.user[identifier].accCount == 0) { // 계좌가 0개일 경우
					userManager.user[identifier].acc = new Account[1];
					
					userManager.user[identifier].acc[0] = new Account();
					userManager.user[identifier].acc[0].number = makeAccount; 
				
				}
				else { // 계좌가 0개가 아닐경우 
					Account[] temp = userManager.getUser(identifier).acc; // 임시 배열만들기 > 기존 배열 넣기(아이디 불러와서 계좌들 넣기)
					int tempAccCount = userManager.getUser(identifier).accCount; // 임시배열 계좌개수를 따로 만들기 > 만들어진 객체에서 불러오기 
					userManager.user[identifier].acc = new Account[tempAccCount+1]; //  기존 배열에 크기를 (기존+1) 만큼 키운, 새로운 배열로 덮음
					for (int i = 0; i < tempAccCount; i++) { 
						userManager.user[identifier].acc[i] = temp[i]; // 새로운 배열에 기존 배열 내용 넣기  
					}
					userManager.user[identifier].acc[tempAccCount] = new Account(); // 마지막 칸의 배열에는 추가된 내용 넣기 
					userManager.user[identifier].acc[tempAccCount].number = makeAccount; // 마지막 칸의 배열에 계좌를 생성 
					
				}
				userManager.user[identifier].accCount++; // 계좌 갯수 증가 
				System.out.println("[메시지]'"+makeAccount +"'계좌가 생성되었습니다.\n"); 
			} 	
			else if (sel == 2) { // 계좌삭제 
				
				if (userManager.user[identifier].accCount == 0) { // 계좌가 0개일 경우 
					System.out.println("[메시지] 더 이상 삭제할 수 없습니다.\n");
					continue;
				}
				
				if ( userManager.user[identifier].accCount == 1) { // 계좌가 1개일 경우 
					System.out.println("[메시지] 계좌번호 :'"+ userManager.user[identifier].acc[0].number+"' 삭제 되었습니다.\n");
					userManager.user[identifier].acc = null;
				}
				else { // 계좌가 2개 이상일 경우 
					
					System.out.print("삭제 하고 싶은 계좌 번호를 입력하세요 : ");
					String deleteAccount = scan.next();
					int tempAccCount = userManager.user[identifier].accCount; // 임시 int에 계좌 갯수 담기  
					int delIdx = -1; 
					for (int i = 0; i <tempAccCount; i++) {
						if (deleteAccount.equals(userManager.user[identifier].acc[i].number)) { // 입력한 계좌번호 = i번째의 계좌번호
							delIdx = i; // 삭제하는 아이디의 인덱스는 i
						}
					}
					
					if ( delIdx == -1 ) { // 해당하는 인덱스가 없을 경우 
						System.out.println("[메시지] 계좌번호를 확인하세요.\n");
						continue;
					}
					else { // 해당 인덱스가 있을 경우 
						System.out.println("[메시지] 계좌번호 :'"+ userManager.user[identifier].acc[delIdx].number+"' 삭제 되었습니다.\n");
						
						Account[] temp = userManager.user[identifier].acc; // 임시배열에 내용 담고 
						userManager.user[identifier].acc = new Account[tempAccCount-1]; // 기존 배열에 크기가 (기존-1)인, 새로운 배열로 덮음
						
						
						for (int i = 0; i < delIdx; i++) { // 0부터 인덱스가 삭제하는 인덱스보다 작을때까지 
							userManager.user[identifier].acc[i] = temp[i]; // 내용 그대로를 넣기 
						}
						for (int i = delIdx; i < tempAccCount - 1; i++) { // 삭제인덱스를 기준으로 (기존 배열의 -1)째까지 
							userManager.user[identifier].acc[i] = temp[i+1]; // 크기가 달라진 배열에 기존 배열의 것을 왼쪽으로 한칸씩 땡겨준다 
						}
					}
					
				}
				userManager.user[identifier].accCount--; // 계좌 개수가 하나씩 줄어든다
				
			}
			
			else if (sel == 3) { // 조회 
				if (userManager.user[identifier].accCount == 0) { // 계좌가 0개일 경우
					System.out.println("[메시지] 생성된 계좌가 없습니다.\n");
				}
				else { // 계좌가 1개 이상일 경우
					userManager.user[identifier].printAccount(); // 계좌 출력 
				}
			}   
			else if (sel == 0) { // 종료 
				logout();
				break;
			}
			
		}
		
	}	
}
