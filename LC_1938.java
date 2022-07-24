package leetcode;

import java.util.ArrayList;


class LC_1938 {
    TrieNode trieRoot = new TrieNode();
    public int[] maxGeneticDifference(int[] parents, int[][] qs) {
        int n = parents.length, m = qs.length, root = -1;
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            if (parents[i] == -1) root = i;
            else graph[parents[i]].add(i);
        }

        ArrayList<int[]>[] queryByNode = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            queryByNode[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            queryByNode[qs[i][0]].add(new int[]{qs[i][1], i});
        }

        int[] ans = new int[m];
        dfs(root, graph, queryByNode, ans);
        return ans;
    }
    void dfs(int node, ArrayList<Integer>[] graph, ArrayList<int[]>[] queryByNode, int[] ans) {
        trieRoot.add(node, 1);
        for (int[] valIdx : queryByNode[node]) {
            ans[valIdx[1]] = trieRoot.findMax(valIdx[0]);
        }
        for (int v: graph[node]) {
            dfs(v, graph, queryByNode, ans);
        }
        trieRoot.add(node, -1);
    }

    class TrieNode {
        TrieNode[] child = new TrieNode[2];
        int cnt = 0;
        public void add(int number, int diff) {
            TrieNode cur = this;
            for (int i = 17; i >= 0; --i) {
                int bit = (number >> i) & 1;
                if (cur.child[bit] == null)
                    cur.child[bit] = new TrieNode();
                cur.child[bit].cnt += diff;
                cur = cur.child[bit];
            }
        }
        public int findMax(int number) {
            TrieNode cur = this;
            int ans = 0;

            for (int i = 17; i >= 0; --i) {
                int bit = (number >> i) & 1;
                int oppBit = 1 - bit;
                // check opposite bit first and add 1 to location if exist.
                if (cur.child[oppBit] != null && cur.child[oppBit].cnt > 0) {
                    cur = cur.child[oppBit];
                    ans |= (1 << i);
                }
                // this exists if opposite bit does not so go to next bit without adding
                else {
                    cur = cur.child[bit];
                }
            }
            return ans;
        }
    }
}