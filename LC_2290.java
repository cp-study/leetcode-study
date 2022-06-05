package leetcode;

import java.util.LinkedList;
import java.util.Queue;

class LC_2290 {

    int[] dy = {0,0,-1,1};
    int[] dx = {1,-1,0,0};

    public int minimumObstacles(int[][] grid) {
        Queue<Coord> q = new LinkedList<>();
        boolean[][] visited =
                new boolean[grid.length][grid[0].length];

        q.add(new Coord(0,0,0));

        int ret = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Coord c = q.poll();
            visited[c.y][c.x] = false;
            int removalCnt = dfs(c.y, c.x, c.removalCnt, true, grid, q, visited);
            if(removalCnt != Integer.MAX_VALUE) {
                ret = removalCnt;
                break;
            }
        }
        return ret;
    }

    int dfs(int y, int x, int removalCnt, boolean isFirst,
             int[][] grid, Queue<Coord> q,
             boolean[][] visited) {
        if(y < 0 || x < 0 || y >= grid.length
            || x>=grid[0].length) return Integer.MAX_VALUE;
        if(visited[y][x]) return Integer.MAX_VALUE;
        visited[y][x] = true;
        if(y == grid.length-1 && x == grid[0].length-1) {
            return removalCnt;
        }

        int retMin = Integer.MAX_VALUE;
        if(grid[y][x] == 1 && !isFirst) {
            q.add(new Coord(y,x,removalCnt+1));
        } else {
            for(int i=0;i<4;i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                retMin = Math.min(retMin, dfs(ny,nx, removalCnt, false, grid, q, visited));
            }
        }
        return retMin;
    }

    class Coord {
        int y,x,removalCnt;
        public Coord(int y, int x, int removalCnt) {
            this.y = y;
            this.x = x;
            this.removalCnt = removalCnt;
        }
    }
}