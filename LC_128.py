class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        s = set(nums)
        answer = 0
        
        for n in s:
            if n-1 in s:
                continue
            
            l = 0
            next = n
            while next in s:
                l += 1
                next += 1
            answer = max(answer, l)

        return answer
