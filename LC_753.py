class Solution:
    def crackSafe(self, n: int, k: int) -> str:
        ans = list()
        nodeNum = k**(n-1)
        edges = [k-1]*nodeNum

        print(nodeNum, edges)
        node = 0
        while edges[node] >= 0:
            edge = edges[node]
            edges[node] -= 1
            node = (node * k + edge) % nodeNum
            print(edge, edges[node], node)
            ans.append(str(edge))
    
        return  "0"*(n-1)  + "".join(ans)
