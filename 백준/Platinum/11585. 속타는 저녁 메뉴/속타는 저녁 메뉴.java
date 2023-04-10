import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] pattern = new char[n * 2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			pattern[i] = pattern[i+n] = st.nextToken().charAt(0);
		}
		char[] input = new char[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		int[] pi = new int[n];
		for(int i = 1, j = 0; i < n; i++) {
			while(j > 0 && input[i] != input[j]) j = pi[j-1];
			if(input[i] == input[j]) pi[i] = ++j;
			else pi[i] = 0;
		}
		int cnt = 0;
		for(int i = 0, j = 0; i < n * 2 - 1; ++i) {
			while(j > 0 && pattern[i] != input[j]) j = pi[j-1];
			if(pattern[i] == input[j]) {
				if(j == n - 1) {
					cnt++;
					j = pi[j];
				} else {
					j++;
				}
			} 
		}
		int gcd = gcd(n, cnt);
		if(n == 1) {
			System.out.println("1/1");
		} else {
			System.out.println(cnt/gcd + "/" + n/gcd);
		}
	}
	static int gcd(int a, int b) {
		while(b > 0) {
			int tmp = a;
			a = b;
			b = tmp % b;
		}
		return a;
	}
} 
