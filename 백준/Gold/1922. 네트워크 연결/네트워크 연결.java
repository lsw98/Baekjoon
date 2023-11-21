import java.io.*;
import java.util.*;
public class Main {
    static class node {
        int to;
        int distance;

        public node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<List<node>> graph = new ArrayList<>();

        for(int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph.get(from).add(new node(to, distance));
            graph.get(to).add(new node(from, distance));
        }
        boolean[] connect = new boolean[n+1];
        int ans = 0;
        PriorityQueue<node> nodeList = new PriorityQueue<>(Comparator.comparingInt(node::getDistance));
        for(int i = 0; i < graph.get(1).size(); i++) {
            nodeList.offer(graph.get(1).get(i));
        }
        connect[1] = true;
        while(!nodeList.isEmpty()) {
            node current = nodeList.poll();

            if(connect[current.to]) {
                continue;
            }

            connect[current.to] = true;
            ans += current.getDistance();
            for(int i=0; i <graph.get(current.to).size(); i++) {
                nodeList.offer(graph.get(current.to).get(i));
            }
        }
        System.out.println(ans);
    }
}
