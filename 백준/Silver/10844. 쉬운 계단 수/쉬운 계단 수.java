import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[][] dp = new long[n][10];
		for(int i = 1; i <= 9; i++) {
			dp[0][i] = 1;
		}
		for(int i = 1; i < n; i++) {
			dp[i][0] = dp[i-1][1];
			for(int j = 1; j <= 8; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000; 
			}
			dp[i][9] = dp[i-1][8];
		}
		long ans = 0;
		for(int i = 0; i <= 9; i++) {
			ans += dp[n-1][i];
		}
		System.out.println(ans % 1000000000);
	}

}
