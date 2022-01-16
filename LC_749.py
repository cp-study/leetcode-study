class Solution(object):
    def containVirus(self, grid):
        directions = [(0, 1), (0, -1), (-1, 0), (1, 0)]
 
        def dfs(grid, x, y, visited, regions, affectedArea, walls):
            if (x, y) in visited:
                return
            visited.add((x, y))
            regions[-1].add((x, y))
            for d in directions:
                nx, ny = x+d[0], y+d[1]
                if not (0 <= nx < len(grid) and 0 <= ny < len(grid[x])):
                    continue
                if grid[nx][ny] == 1:
                    dfs(grid, nx, ny, visited, regions, affectedArea, walls)
                elif grid[nx][ny] == 0:
                    affectedArea[-1].add((nx, ny))
                    walls[-1] += 1
 
        result = 0
        while True:
            visited, regions, affectedArea, walls = set(), [], [], []
            for x, row in enumerate(grid):
                for y, val in enumerate(row):
                    if val == 1 and (x, y) not in visited:
                        regions.append(set())
                        affectedArea.append(set())
                        walls.append(0)
                        dfs(grid, x, y, visited, regions, affectedArea, walls)
 
            if not regions: break
            
            maxAffectedAreaIdx = affectedArea.index(max(affectedArea, key = len))
            for i, region in enumerate(regions):
                if i == maxAffectedAreaIdx:
                    result += walls[i]
                    for x, y in region:
                        grid[x][y] = -1
                    continue
                for x, y in region:
                    for d in directions:
                        nx, ny = x+d[0], y+d[1]
                        if not (0 <= nx < len(grid) and 0 <= ny < len(grid[x])):
                            continue
                        if grid[nx][ny] == 0:
                            grid[nx][ny] = 1
 
        return result
