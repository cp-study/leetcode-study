class Solution:
    def cherryPickup(self, grid):
        n = len(grid)
        lookup = {}
        
        def solve(x1, y1, x2, y2):
            # check if we reached bottom right corner
            if x1 == n-1 and y1 == n-1: 
                return grid[x1][y1] if grid[x1][y1] != -1 else float("-inf")
            
            # out of the grid and thorn check
            if x1 == n or y1 == n or x2 == n or y2 == n or grid[x1][y1] == -1 or grid[x2][y2] == -1: 
                return float("-inf")
            
	    # memorization check
            lookup_key = (x1, y1, x2, y2)
            if lookup_key in lookup: 
                return lookup[lookup_key]
            
	    # pick your cherries
            if x1 == x2 and y1 == y2:
                cherries = grid[x1][y1]
            else:
                cherries = grid[x1][y1] + grid[x2][y2]
                
            maxCherries = cherries + max(
                solve(x1+1, y1, x2+1, y2),  # right, right
                solve(x1, y1+1, x2, y2+1),  # down, down
                solve(x1+1, y1, x2, y2+1),  # right, down
                solve(x1, y1+1, x2+1, y2),  # down, right
            )
            
            lookup[lookup_key] = maxCherries
            return maxCherries
        
        ans = solve(0, 0, 0, 0)
        return ans if ans > 0 else 0
