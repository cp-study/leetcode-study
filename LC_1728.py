class Solution:
    def canMouseWin(self, grid: List[str], catJump: int, mouseJump: int) -> bool:
        m, n = len(grid), len(grid[0])
        walls = set()
        for i in range(m):
            for j in range(n):
                if grid[i][j] == "F": food = (i, j)
                elif grid[i][j] == "C": cat = (i, j)
                elif grid[i][j] == "M": mouse = (i, j)
                elif grid[i][j] == "#": walls.add((i, j))
                    
        dx = [0,0,1,-1]
        dy = [1,-1,0,0]
                    
        @lru_cache(None)
        def dfs(cat, mouse, turn): 
            if cat == food or cat == mouse or turn >= m*n*2: return False 
            if mouse == food: return True 
            
            if not turn & 1:
                x, y = mouse
                for i in range(4):
                    for jump in range(0, mouseJump+1):
                        nx, ny = x+jump*dx[i], y+jump*dy[i]
                        if not (0 <= nx < m and 0 <= ny < n) or (nx, ny) in walls: break 
                        if dfs(cat, (nx, ny), turn+1): return True 
                return False 
            else:
                x, y = cat
                for i in range(4): 
                    for jump in range(0, catJump+1):
                        nx, ny = x+jump*dx[i], y+jump*dy[i]
                        if not (0 <= nx < m and 0 <= ny < n) or (nx, ny) in walls: break 
                        if not dfs((nx, ny), mouse, turn+1): return False
                return True
                    
        return dfs(cat, mouse, 0)
