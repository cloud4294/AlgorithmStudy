import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 2164번 - 카드2
 * 
 * 		https://www.acmicpc.net/problem/2164
 */

public class Main2164 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			if(queue.size() == 1) {
				System.out.println(queue.poll());
				break;
			}
			queue.poll();
			queue.offer(queue.poll());
		}

	}

}
