import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 		Baekjoon Online Judge 5076번 - Web Pages
 * 
 * 		https://www.acmicpc.net/problem/5076
 */

public class Main5076 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;

		while (!(input = br.readLine()).equals("#")) { // #이 입력될때까지 입력을받음 
			int s = 0, d = 0;
			String str = null;
			Stack<String> stack = new Stack<>();
			boolean check = true;
			
			for (int i = 0; i < input.length(); i++) { // 입력받은 한줄을 한글자씩 확인 
				if (input.charAt(i) == '<') { // < 이면 시작지점을 s에 저장
					s = i;
				}else if(input.charAt(i) == '>') { // > 이면 마지막 지점을 d에저장 
					d = i;
					if(d <= s) { 
						check = false;
						break;
					} // d가 s보다 작은경우 illegal 
					
					str = input.substring(s+1, d); // s+1 부터 d 까지 문자열을 잘라냄
					if(str.equals("br /")) { // 잘라낸 문자열이 br / 이면 다음문자를탐색
						continue;
					}else if(str.contains(" ")) { // 잘라낸 문자열이 공백을 포함하면 앞문자열만 잘라냄
						String[] split = str.split(" ");				
						str = split[0];
					}
					
					if(str.charAt(0)=='/') { // 잘라낸 문자열 앞에 /가있으면 
						if(stack.isEmpty()) { // 스택이 비어있으면 illegal
							check = false;
							break;
						}
						
						if(stack.peek().equals(str)) { // 스택 상단의 문자열과 잘래라낸 문자열이 일치하면 스택에서빼냄
							stack.pop();
						}else { // 일치하지 않으면 illegal
							check = false;
							break;
						}		
					}else { // 앞에 /가 없으면 /을 붙여서 스택에 넣음 
						stack.push("/"+str);
						
					}
					
				}
				
			}
			if(!stack.isEmpty()) //탐색이 끝난후 스택이 비어있지않으면 illegal
				check = false;

			System.out.println(check == true ? "legal" : "illegal");
			
		}

	}

}
