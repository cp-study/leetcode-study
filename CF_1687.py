class Solution:
    def boxDelivering(self, boxes: List[List[int]], portsCount: int, maxBoxes: int, maxWeight: int) -> int:
        n = len(boxes)
        
        @lru_cache(None)
        def dfs(i):
            if i > n-1: return 0

            j = lastest_port = i
            box_num = weight = port_change = 0
            while j < n and box_num < maxBoxes and weight + boxes[j][1] <= maxWeight:
                box_num += 1
                weight += boxes[j][1]
                if j != i and boxes[j][0] != boxes[j-1][0]:
                    port_change += 1
                    lastest_port = j
                j += 1

            trip = 2 + port_change + dfs(j)
            if lastest_port != i:
                trip = min(trip, 1 + port_change + dfs(lastest_port))
            return trip

        return dfs(0)
