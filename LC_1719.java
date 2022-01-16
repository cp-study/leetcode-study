class Solution {
    private static final int MAX_N = 501;
    Map<Integer, List<Integer>> adj = new HashMap<>();
    boolean[][] connected = new boolean[MAX_N][MAX_N];
    boolean multiple = false;

    public int checkWays(int[][] pairs) {
        int root = -1;
        for (int[] pair : pairs) {
            int x = pair[0];
            int y = pair[1];
            adj.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            adj.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
            connected[x][y] = connected[y][x] = true;
        }
        int size = adj.keySet().size();
        for (int node : adj.keySet()) {
            adj.get(node).sort((x, y) -> Integer.compare(getDegree(y), getDegree(x))); // sort the neighbors
            if (getDegree(node) == size - 1) {
                root = node; // found the root
            }
        }
        if (root < 0) return 0;
        if (!dfs(root, new ArrayList<>(), new boolean[MAX_N])) return 0;
        return multiple ? 2 : 1;
    }
    
    public int getDegree(int node){
        return adj.get(node).size();
    }

    boolean dfs(int x, List<Integer> ancestors, boolean[] vst) {
        if (vst[x]) return true;
        for (int p : ancestors) {
            if (!connected[p][x]) return false; // do the check here
        }
        vst[x] = true;
        ancestors.add(x);
        for (int y : adj.get(x)) {
            if (getDegree(y) == getDegree(x)) multiple = true;
            if (!dfs(y, ancestors, vst)) return false;
        }
        ancestors.remove(ancestors.size() - 1);
        return true;
    }
}
