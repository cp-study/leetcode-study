class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort(key = lambda x:(x[0],x[1]))
        ans=[]
        cur=[-1,-1]
        for idx,current in enumerate(intervals):
            s,e = current
            #초기값
            if cur == [-1,-1]:
                cur = [s,e]
            else:
                #구간이 겹쳐질 수 있을때
                if s<=cur[1]:
                    cur = [cur[0],max(e,cur[1])]
                    
                else:
                    ans.append(cur)
                    cur = [s,e]
            if idx==len(intervals)-1:
                ans.append(cur)
        return ans
        
