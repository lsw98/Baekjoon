import java.io.*;
import java.util.*;

public class Main {
	
	static int n, d, k, c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int[] sushi = new int[n];
		for(int i = 0; i < n; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		int[] eat = new int[d+1];
		int cnt = 0;
		int ans = 0;
		for(int i = 0; i < k; i++) { // 처음에 0부터 k개 먹기
			if(eat[sushi[i]] == 0) { // 새로운 종류
				cnt++;
			}
			eat[sushi[i]]++;
		}
		if(eat[c] == 0) cnt++;
		eat[c]++;
		ans = cnt;
		int start = 0;
		int end = k-1;
		while(start < n) {
			eat[sushi[start]]--;
			if(eat[sushi[start]] == 0) cnt--;
			start++;
			end = (end+1) % n;
			if(eat[sushi[end]] == 0) {
				cnt++;
			} 
			eat[sushi[end]]++;					
			ans = Math.max(ans, cnt);
		}
		System.out.println(ans);
	}

}
