import java.io.*;
import java.util.*;

public class Main {
	static int n; 
	static long p = 1_000_000_000;
	static long[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new long[n+1][10][1 << 10];
		for(int i = 1; i <= 9; i++) {
			dp[1][i][1 << i] = 1;
		}
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j <= 9; j++) {
				for(int k = 0; k < 1024; k++) {
					if(j == 0) {
						dp[i][0][1 << 0 | k] = (dp[i][0][1 << 0 | k] + dp[i-1][1][k]) % p;
					} else if(j == 9) {
						dp[i][9][1 << 9 | k] = (dp[i][9][1 << 9 | k] + dp[i-1][8][k]) % p;
					} else {
						dp[i][j][1 << j | k] = (dp[i][j][1 << j | k]+ dp[i-1][j+1][k] + dp[i-1][j-1][k]) % p;
					}
					
 				}
			}		
		}
		long ans = 0;
		for(int i = 0; i <= 9; i++) {
			ans = (ans + dp[n][i][(1 << 10) - 1]) % p;
		}
		System.out.println(ans);
	}
}
