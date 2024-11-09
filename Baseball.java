package javaStudy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Baseball {
	private String ranNum = ""; //랜덤 숫자
	private String userNum = ""; //사용자가 입력한 숫자
	private boolean flag = true; 
	private int [] numArr = new int [10]; //랜덤 숫자 생성하기 위한 숫자 배열 선언
	private Scanner scanner = new Scanner(System.in);
	
	public void startGame() {
		setNumArray(); 
		createRandomNum(); 
		
		while(flag) {
			enterUserNum(); //사용자로부터 입력받기
			chkInputNum(); //사용자가 입력한 값 스트라이크, 볼 여부 확인
		}
	}
	
	public void createRandomNum() { //랜덤한 숫자 3자리 생성하는 메소드 
		Random random = new Random();
		int count = 0;
		
		while(true) {
			int idx = random.nextInt(9); //랜덤 인덱스 생성
			int num = numArr[idx]; 
			
			if(num != 99) { //해당 인덱스의 값이 이미 사용한 값이 아니라면 
				ranNum += num;
				numArr[idx] = 99;
				count ++;
			}
			
			if(count == 3) {
				break;
			}
		}
		
		System.out.println("랜덤 숫자 3자리가 생성되었습니다." + ranNum);
	}
	
	public void enterUserNum() {
		System.out.print("숫자 3자리를 입력해주세요 > ");
		userNum = scanner.next();
	}
	
	public void chkInputNum() {
		String [] inputArr = new String[3];
		String [] ranArr = new String[3];
		
		for(int i=0; i<3; i++) {
			inputArr[i] = userNum.substring(i, i+1);
			ranArr[i] = ranNum.substring(i, i+1);
		}
		
		int strikeCount = chkStrikeCount(ranArr, inputArr);
		int ballCount = chkBallCount(ranArr, inputArr);
		
		if(strikeCount == 3) { //정답 입력
			System.out.println("정답입니다!");
			flag = false;
		} 
		else if(strikeCount == 0 && ballCount == 0){ //해당되는 숫자가 하나도 없음
			System.out.println("해당되는 숫자가 없습니다.");
		}
		else { //하나라도 겹치는 숫자가 있을 경우
			System.out.println(strikeCount + " 스트라이크, " + ballCount + " 볼 입니다.");
		}
	}
	
	public int chkStrikeCount(String[] ranArr, String[] inputArr) {
		int count = 0;
		
		for(int i=0; i<3; i++) {
			if(ranArr[i].equals(inputArr[i])) {
				count ++;
			}
		}
		
		return count;
	}
	
	public int chkBallCount(String[] ranArr, String[] inputArr) {
		int count = 0;
		String num1 = "";
		String num2 = "";
		
		for(int i=0; i<3; i++) {
			num1 = ranArr[i];
			
			for(int j=0; j<3; j++) {
				num2 = inputArr[j];
				
				if(i != j && num1.equals(num2)) {
					count ++;
				}
			}
		}
		
		return count;
	}
	
	//숫자 배열 생성
	public void setNumArray() {
		for (int i = 0; i < 10; i++) {
			numArr[i] = i;
		}
	}
}
