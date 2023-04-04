import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String s = br.readLine();
			if(s.equals(".")) break;
			
			char[] pattern = s.toCharArray();
			int l = pattern.length;
			int[] pi = new int[l];
			for(int i = 1, j = 0; i < l; i++) {
				while(pattern[i] != pattern[j] && j > 0) j = pi[j-1];
				
				if(pattern[i] == pattern[j]) pi[i] = ++j;
				else pi[i] = 0;
			}
			int ans = Integer.MAX_VALUE;
			for(int p : pi) {
				if(p > 0) ans = Math.min(ans, p);
			}
			if(l % (l-pi[l-1]) == 0) {
				System.out.println(l/(l-pi[l-1]));
			} else {
				System.out.println(1);
			}		
		}

	}

}
