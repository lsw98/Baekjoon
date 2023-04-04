import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = Integer.parseInt(br.readLine());
		String s = br.readLine();
		char[] pattern = s.toCharArray();
		int[] pi = new int[l];
		for(int i = 1, j = 0; i < l; i++) {
			while(j > 0 && pattern[i] != pattern[j]) j = pi[j-1];
			if(pattern[i] == pattern[j]) pi[i] = ++j;
			else pi[i] = 0;
		}
		System.out.println(l - pi[l-1]);
	}

}
