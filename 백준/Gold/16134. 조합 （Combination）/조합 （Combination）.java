import java.io.*;
import java.util.*;

public class Main {
	static int n, r, p = 1000000007;
	static long[] fac;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		fac = new long[n+1];
		fac[0] = 1;
		for(int i = 1; i <= n; i++) {
			fac[i] = fac[i-1] * i % p;
		}
		System.out.println(nCr(n, r));
	}
	static long nCr(int n, int r) {
		if(r == 0) return 1L;
		
		return (fac[n] * power(fac[r], p-2) % p * power(fac[n-r], p-2) % p) % p;
	}
	static long power(long x, long y) {
		long ans = 1;
		x = x % p;
		while(y > 0) {
			if(y % 2 == 1) {
				ans = (ans * x) % p; // 홀수면 나눌때 사라지므로 1 미리 계산
			}
			y = y >> 1;
			x = (x * x) % p;
		}
		return ans;
	}
}
