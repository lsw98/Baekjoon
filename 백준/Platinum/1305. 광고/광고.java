import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = Integer.parseInt(br.readLine());
		String s = br.readLine();
		char[] pattern = s.toCharArray();
		int length = pattern.length;
		int[] pi = new int[length];
		for(int i = 1, j = 0; i < length; i++) {
			while(j > 0 && pattern[i] != pattern[j]) j = pi[j-1];
			
			if(pattern[i] == pattern[j]) pi[i] = ++j;
			else pi[i] = 0;
		}
		System.out.println(length - pi[length-1]);
	}

}