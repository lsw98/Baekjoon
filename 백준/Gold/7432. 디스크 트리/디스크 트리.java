import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static node trie;
	static class node {
		Map<String, node> child = new TreeMap<String, node>();
		boolean end;
		int depth;
		
		public node(int depth) {
			this.depth = depth;
		}
		
		void insert(String str) {
			node current = this;
			String[] dir = str.split("\\\\");
			for(int i = 0; i < dir.length; i++) {
				int d = current.depth;
				current = current.child.computeIfAbsent(dir[i], key -> new node(d+1));
			}
		}
		
		void print() {
			for(String s : child.keySet()) {
				for(int i = 1; i < child.get(s).depth; i++) {
					sb.append(" ");
				}
				sb.append(s).append("\n");
				child.get(s).print();
			}
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		trie = new node(0);
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			trie.insert(line);
		}
		trie.print();
		System.out.println(sb);
	}

}
