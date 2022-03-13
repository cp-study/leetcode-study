class Solution:
    def gridIllumination(self, n: int, lamps: List[List[int]], queries: List[List[int]]) -> List[int]:
        row,col,dig1,dig2 = defaultdict(int),defaultdict(int),defaultdict(int),defaultdict(int)

        def switch(i,j,isOn):
            val = 1 if isOn else -1
            row[i] += val
            col[j] += val
            dig1[i+j]+=val
            dig2[i-j]+=val

        def check(x,y):
            return 1 if row[x] or col[y] or dig1[x+y] or dig2[x-y] else 0

        seen = set()
        for x,y in set([tuple(lamp) for lamp in lamps]):
            seen.add((x,y))
            switch(x,y,1)

        res = []
        for x,y in queries:
            res.append(check(x,y))
            for dx,dy in [(0,0),(0,1),(0,-1),(1,0),(-1,0),(1,1),(-1,-1),(1,-1),(-1,1)]:
                if (x+dx,y+dy) in seen:
                    seen.remove((x+dx,y+dy))
                    switch(x+dx,y+dy,0)
        return res
