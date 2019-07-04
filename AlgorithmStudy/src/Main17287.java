import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;
/*
 * 		Baekjoon Online Judge 17287번 - The Deeper, The Better
 * 
 * 		https://www.acmicpc.net/problem/17287
 */

public class Main17287 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line = br.readLine();
		int[] point = new int[10];
		Stack<String> stack = new Stack<>();
		int max = 0;
		for (int i = 0; i < line.length(); i++) {
			String now = line.substring(i, i + 1);

			if (now.equals("(") || now.equals("[") || now.equals("{"))
				stack.push(now);
			else if (now.equals(")")) {
				stack.pop();
			} else if (now.equals("]")) {
				stack.pop();
			} else if (now.equals("}")) {
				stack.pop();
			} else {

				int index = Integer.parseInt(now);
				Iterator<String> itr = stack.iterator();
				int count = 0;
				while (itr.hasNext()) {
					String check = itr.next();
					if (check.equals("("))
						count += 1;
					else if (check.equals("{"))
						count += 2;
					else if (check.equals("["))
						count += 3;
				}

				point[index] = Math.max(count, point[index]);

			}

		}
		for (int j = 0; j < 10; j++) {
			max = Math.max(max, point[j]);
		}
		System.out.print(max);

	}

}
