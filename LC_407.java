package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;

        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));

        boolean[][] visited = new boolean[n][m];
        for(int i=0;i<n;i++) {
            visited[i][0] = true;
            visited[i][m-1] = true;
            pq.offer(new Cell(i,0, heightMap[i][0]));
            pq.offer(new Cell(i,m-1, heightMap[i][m-1]));
        }
        for(int i=0;i<m;i++) {
            visited[0][i] = true;
            visited[n-1][i] = true;
            pq.offer(new Cell(0,i, heightMap[0][i]));
            pq.offer(new Cell(n-1,i, heightMap[n-1][i]));
        }

        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,1,-1};

        long ret = 0;
        while(!pq.isEmpty()) {
            Cell cell = pq.poll();

            for(int i =0;i<4;i++) {
                int nr = dr[i] + cell.r;
                int nc = dc[i] + cell.c;
                if(nr < 0 || nc < 0 || nr >= n || nc >=m || visited[nr][nc]) continue;
                else {
                    visited[nr][nc] = true;
                    if(heightMap[nr][nc] < cell.height) {
                        ret += cell.height - heightMap[nr][nc];
                    }
                    pq.offer(new Cell(nr,nc, Math.max(cell.height, heightMap[nr][nc])));
                }
            }
        }

        return (int) ret;
    }

    private class Cell {
        int r,c, height;
        public Cell(int r, int c, int height) {
            this.r = r;
            this.c = c;
            this.height = height;
        }
    }
}