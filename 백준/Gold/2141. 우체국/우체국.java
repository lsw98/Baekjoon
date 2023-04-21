import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] v = new int[n][2];
		long sum = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v[i][0] = Integer.parseInt(st.nextToken());
			v[i][1] = Integer.parseInt(st.nextToken());
			sum += v[i][1];
		}
		Arrays.sort(v, (v1, v2) -> {
			return Integer.compare(v1[0], v2[0]);}
		);
		long cur = 0;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			cur += v[i][1];
			/*if (cur > sum / 2) {
				if(i == n - 1) {
					ans = v[i][0];
					break;
				}
				int cmp = Long.compare(Math.abs(cur - (sum / 2)), Math.abs((cur + v[i+1][1]) - (sum / 2)));
				if(cmp <= 0) { // 여러개인 경우 포함
					ans = v[i][0];
					break;
				} else {
					ans = v[i+1][0];
					break;
				}
			} else if (cur == sum / 2) {
				ans = v[i][0];
				break;
			}*/
			if(cur >= (sum/2.0)) {
				ans = v[i][0];
				break;
			}
		}
		System.out.println(ans);
	}

}
