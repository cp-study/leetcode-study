class Solution:
    def dfs(self,grid,x,y,cnt):
        dx = [-1,0,0,1]
        dy = [0,1,-1,0]
        if grid[x][y] == 2:
            return cnt == 1
        ans = 0
        cur = grid[x][y]
        grid[x][y] = -2
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < len(grid) and 0 <= ny < len(grid[0]) and grid[nx][ny] >= 0:
                ans += self.dfs(grid,nx,ny,cnt-1)
        grid[x][y] = cur
        return ans
    
    def uniquePathsIII(self, grid: List[List[int]]) -> int:
        cnt = 0
        start,end = 0,0
        for x in range(len(grid)):
            for y in range(len(grid[0])):
                if grid[x][y] >= 0:
                    cnt+=1
                if grid[x][y] == 1:
                    start,end = x,y
        return self.dfs(grid,start,end,cnt)
