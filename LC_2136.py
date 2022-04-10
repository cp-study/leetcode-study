class Solution:
    def earliestFullBloom(self, plantTime: List[int], growTime: List[int]) -> int:
        ans = prefix = 0 
        for p, g in sorted(zip(plantTime, growTime), key=lambda x: x[1], reverse=True): 
            prefix += p
            ans = max(ans, prefix + g)
        return ans 
