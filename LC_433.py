class Solution:
    def minMutation(self, startGene: str, endGene: str, bank: List[str]) -> int:
        visited = defaultdict(bool)

        # 한글자만 다른지 체크
        def canMutate(gene, newGene):
            diff = 0
            for i in range(len(gene)):
                if gene[i] != newGene[i]:
                    diff += 1
            return diff == 1

        def solve(gene):
            if gene == endGene:
                return 0

            ret = float('inf')
            for b in bank:
                if visited[b]:
                    continue

                if canMutate(gene, b):
                    visited[b] = True
                    ret = min(ret, solve(b) + 1)
                    visited[b] = False

            return ret

        result = solve(startGene)
        if result == float('inf'):
            return -1
        return result
