import java.util.PriorityQueue;

class Solution {
    public int maximumScore(int[] A, int[][] edges) {
        int n = A.length;
        PriorityQueue<Integer>[] pq = new PriorityQueue[n];
        for (int i = 0; i < n; i++) {
            pq[i] = new PriorityQueue<>((a, b) -> A[a] - A[b]);
        }
        for (int[] e : edges) {
            pq[e[0]].offer(e[1]);
            pq[e[1]].offer(e[0]);
            if (pq[e[0]].size() > 3) pq[e[0]].poll();
            if (pq[e[1]].size() > 3) pq[e[1]].poll();
        }
        int res = -1;
        for (int[] edge : edges)
            for (int i : pq[edge[0]])
                for (int j : pq[edge[1]])
                    if (i != j && i != edge[1] && j != edge[0])
                        res = Math.max(res, A[i] + A[j] + A[edge[0]] + A[edge[1]]);
        return res;
    }
}