import java.io.*;
import java.util.*;

public class Main {
	static int ans = Integer.MIN_VALUE;
	static int[] num;
	static char[] oper;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		num = new int[n/2 + 1];
		oper = new char[n/2];
		for (int i = 0; i < n; i++) {
			if((i+1) % 2 == 1) {
				num[i/2] = s.charAt(i) - '0'; // 숫자 저장
			} else {
				oper[i/2] = s.charAt(i); // 연산자 저장
			}
		}
		solve(0, num[0]);// 괄호 선택 
		System.out.println(ans);
	}
	static int calc(int a, int b, char oper) { // 계산
		if(oper == '+') {
			return a + b;
		} else if (oper == '-') {
			return a - b;
		} else {
			return a * b;
		}
	}
	static void solve(int idx, int sum) {
		if(idx == oper.length) {
			ans = Math.max(ans, sum);
			return;
		}
		
		solve(idx + 1, calc(sum, num[idx + 1], oper[idx])); // 괄호 치기
		
		if(idx + 2 <= oper.length) {
			solve(idx + 2, calc(sum, calc(num[idx + 1], num[idx+2], oper[idx + 1]), oper[idx])); // 괄호 안치기
		} 
	}
}
