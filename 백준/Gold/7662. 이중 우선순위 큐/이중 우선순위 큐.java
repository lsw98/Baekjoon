import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < t; tc++) {
           TreeMap<Integer, Integer> map = new TreeMap<>();
           int k = Integer.parseInt(br.readLine());
           for(int i = 0; i < k; i++) {
               st = new StringTokenizer(br.readLine());
               char cmd = st.nextToken().charAt(0);
               int input = Integer.parseInt(st.nextToken());

               if(cmd == 'I') {
                   map.put(input, map.getOrDefault(input, 0) + 1);
               } else {
                   if(map.size() == 0) {
                       continue;
                   } else {
                        int key = input == 1 ? map.lastKey() : map.firstKey();
                        int cnt = map.get(key);
                        if(cnt == 1) {
                            map.remove(key);
                        } else {
                            map.put(key, cnt - 1);
                        }
                   }
               }
           }
           if(map.size() == 0) System.out.println("EMPTY");
           else System.out.println(map.lastKey() + " " + map.firstKey());
        }
    }
}
