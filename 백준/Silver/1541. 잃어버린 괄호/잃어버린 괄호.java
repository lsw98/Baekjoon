import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] l = br.readLine().split("-");
		int ans = 0;
		for(int i = 0; i < l.length; i++) {
			StringTokenizer st = new StringTokenizer(l[i], "+");
			int sum = 0;
			while(st.hasMoreTokens()) {
				sum += Integer.parseInt(st.nextToken());
			}
			if(i == 0) {
				ans += sum;
			} else {
				ans -= sum;
			}
		}
		System.out.println(ans);
	}

}
