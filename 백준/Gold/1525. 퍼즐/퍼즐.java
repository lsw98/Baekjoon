import java.io.*;
import java.util.*;
public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    String map = "";
    for(int i = 0; i < 3; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < 3; j++) {
        String num = st.nextToken();
        if(num.equals("0")) {
          num = "9";
        }
        map = map.concat(num);
      }
    }
    Map<String, Integer> move = new HashMap<String, Integer>();
    move.put(map, 0);
    Queue<String> queue = new ArrayDeque<>();
    queue.offer(map);
    int[][] d = {{1, 3}, {-1, 1, 3}, {-1, 3},
        {-3, 1, 3}, {-3, -1, 1, 3}, {-3, -1, 3},
        {-3, 1}, {-3, -1, 1}, {-3, -1}};
    int ans = Integer.MAX_VALUE;
    while(!queue.isEmpty()) {
      String cur = queue.poll();
      int moveCnt = move.get(cur);
      int empty = cur.indexOf("9");
      if(cur.equals("123456789")) {
        ans = Math.min(ans, moveCnt);
      }

      for(int i = 0; i < d[empty].length; i++) {
        int next = empty + d[empty][i];
        if(0 <= next && next <= 8) {
          StringBuilder nextMap = new StringBuilder(cur);
          nextMap.setCharAt(empty, cur.charAt(next));
          nextMap.setCharAt(next, '9');
          String newMap = nextMap.toString();

          if(move.get(newMap) == null || move.get(newMap) > moveCnt + 1) {
            queue.offer(newMap);
            move.put(newMap, moveCnt + 1);
          }
        }
      }
    }
    if(ans == Integer.MAX_VALUE) {
      ans = -1;
    }
    System.out.println(ans);
  }
}
