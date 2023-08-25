import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][][] dp = new int[n+1][m][3];
        for(int i = 1; i<= n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], 9999999);
            }
        }
        int[][] map = new int[n+1][m];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < m; j++) {
                if(j == 0) {
                    dp[i][j][0] = Math.min(dp[i-1][j+1][1] + map[i][j], dp[i-1][j+1][2] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i-1][j][0] + map[i][j], dp[i-1][j][2] + map[i][j]);
                } else if (j == m - 1) {
                    dp[i][j][2] = Math.min(dp[i-1][j-1][0] + map[i][j], dp[i-1][j-1][1] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i-1][j][0] + map[i][j], dp[i-1][j][2] + map[i][j]);;
                } else {
                    dp[i][j][0] = Math.min(dp[i-1][j+1][1] + map[i][j], dp[i-1][j+1][2] + map[i][j]);
                    dp[i][j][1] = Math.min(dp[i-1][j][0] + map[i][j], dp[i-1][j][2] + map[i][j]);
                    dp[i][j][2] = Math.min(dp[i-1][j-1][0] + map[i][j], dp[i-1][j-1][1] + map[i][j]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < 3; j++) {
                ans = Math.min(ans, dp[n][i][j]);
            }
        }
        System.out.println(ans);
    }
}
