import java.io.*;
import java.util.*;

public class Main {
	static Map<String, Integer> nicknames;
	static class node {
		Map<Character, node> child = new HashMap<Character, node>();
		boolean end;
		public node() {
			
		}
	}
	static class trie {
		node rootnode;
		public trie() {
			this.rootnode = new node();
		}
		String insert(String str) {
			// 1. input string 들어옴 
			// 2. 한글자씩 삽입, 새로 삽입(중복)일때까지 삽입하고 return
			// 2-2. 끝까지 삽입했는데 다 중복이면 닉네임 수 조회
			String s = "";
			node node = this.rootnode;
			boolean newWord = false;
			for(int i = 0; i < str.length(); i++) {
				if(node.child.get(str.charAt(i)) == null) { // 신규 삽입
					node.child.put(str.charAt(i), new node());
					if(!newWord) {
						s = str.substring(0, i+1);
						newWord = true;
					}
				}
				node = node.child.get(str.charAt(i));
				if(s.equals("") && i == str.length() - 1) { // 접두사 별칭 불가능
					int cnt = nicknames.get(str);
					if(cnt == 1) {
						s = str;
					} else {
						s = str.concat(String.valueOf(cnt));
					}
				}
			}
			return s;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		nicknames = new HashMap<>(); // 아이디 중복 몇명인지 저장할 맵
		trie trie = new trie();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			if(nicknames.get(s) == null) {
				nicknames.put(s, 1);
			} else {
				int cnt = nicknames.get(s);
				nicknames.put(s, cnt + 1);
			}
			sb.append(trie.insert(s)).append("\n");
		}
		System.out.print(sb.toString());
	}

}
