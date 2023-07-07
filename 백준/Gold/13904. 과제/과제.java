import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        List<int[]> hw = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            hw.add(new int[] {day, score});
        }
        // 마감일, 점수 순으로 내림차순 정렬
        hw.sort(((o1, o2) -> {
          if(o1[0] == o2[0]) return -Integer.compare(o1[1], o2[1]);
          return -Integer.compare(o1[0], o2[0]);
        }));
        int ans = 0;
        int day = hw.get(0)[0];
        for(int i = day; i >= 1; i--) {
            int max_score = 0;
            int cur = -1;
            for (int idx = 0; idx < hw.size(); idx++) {
                if(hw.get(idx)[0] >= i && hw.get(idx)[1] > max_score) {
                    max_score = hw.get(idx)[1];
                    cur = idx;
                }
            }
            if(cur >= 0) {
                ans += hw.get(cur)[1];
                hw.remove(cur);
            }
        }
        System.out.println(ans);

    }
}