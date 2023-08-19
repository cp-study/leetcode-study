package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class LC_2812 {
    public static void main(String[] args) {

    }
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int gridCosts[][] = new int[grid.size()][grid.get(0).size()];
        initGridCosts(grid,gridCosts);
        for(int i=0;i<gridCosts.length;i++) {
            for(int j=0; j<gridCosts[i].length;j++) {
                System.out.print(gridCosts[i][j] + " ");
            }
            System.out.println();
        }
        int l = 0;
        int r = 800;
        int m = 0;
        int ans = 0;
        while(l<=r) {
            m = (l+r)/2;
            boolean isPossible = bfsAns(m, gridCosts);
            if(isPossible) {
                ans = m;
                l = m+1;
            } else {
                r = m-1;
            }
        }
        return ans;
    }

    int dy[] = {1,-1,0,0};
    int dx[] = {0,0,1,-1};

    private boolean bfsAns(int minSafeness, int gridCosts[][]) {
        boolean visited[][] = new boolean[gridCosts.length][gridCosts[0].length];

        Queue<CoordAns> q = new LinkedList<>();
        q.add(new CoordAns(0,0));

        while(!q.isEmpty()) {
            CoordAns c = q.poll();
            if(visited[c.y][c.x]) continue;
            if(gridCosts[c.y][c.x] < minSafeness) continue;
            if(c.y == gridCosts.length-1 && c.x == gridCosts[0].length-1) {
                return true;
            }
            visited[c.y][c.x] = true;

            for(int i=0;i<4;i++) {
                int ny = c.y + dy[i];
                int nx = c.x + dx[i];
                if(ny <0 || nx <0 || ny >= gridCosts.length || nx >= gridCosts[0].length) continue;
                q.add(new CoordAns(ny,nx));
            }
        }
        return false;
    }

    private void initGridCosts(List<List<Integer>> grid, int gridCosts[][]) {
        bfs(grid,gridCosts);
    }

    private void bfs(List<List<Integer>> grid, int gridCosts[][]) {
        Queue<Coord> q = new LinkedList<>();
        boolean visited[][] = new boolean[gridCosts.length][gridCosts[0].length];
        for(int i=0;i<grid.size();i++) {
            for(int j=0;j<grid.get(i).size();j++) {
                if(grid.get(i).get(j) == 1) {
                    q.add(new Coord(i,j,i,j));
                }
            }
        }

        while(!q.isEmpty()) {
            Coord currCoord = q.poll();
            if(visited[currCoord.currY][currCoord.currX]) continue;
            visited[currCoord.currY][currCoord.currX] = true;
            gridCosts[currCoord.currY][currCoord.currX] = currCoord.getManhattanDist();

            for(int i=0;i<4;i++) {
                int ny = currCoord.currY + dy[i];
                int nx = currCoord.currX + dx[i];
                if(ny <0 || nx <0 || ny >= gridCosts.length || nx >= gridCosts[0].length) continue;
                q.add(new Coord(ny,nx,currCoord.initY,currCoord.initX));
            }
        }
    }

    class Coord {
        int currY, currX;
        int initY, initX;
        public Coord(int currY, int currX, int initY, int initX) {
            this.currY = currY;
            this.currX = currX;
            this.initY = initY;
            this.initX = initX;
        }

        public int getManhattanDist() {
            return Math.abs(currX-initX) + Math.abs(currY-initY);
        }
    }

    class CoordAns {
        int y, x;
        public CoordAns(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}