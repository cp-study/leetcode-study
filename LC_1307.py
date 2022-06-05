from collections import Counter, defaultdict
from itertools import permutations
from typing import List


class Solution:
    def isSolvable(self, words: List[str], result: str) -> bool:
        c = Counter()
        non_leading_zero = set()
        for word in words:
            if len(word) > 1:
                non_leading_zero.add(word[0])
            for i in range(len(word)):
                c[word[i]] += 10 ** (len(word) - i - 1)

        if len(result) > 1:
            non_leading_zero.add(result[0])
        for i in range(len(result)):
            c[result[i]] -= 10 ** (len(result) - i - 1)

        pos = []
        neg = []
        for l, v in c.items():
            if v > 0:
                pos.append((l, v))
            elif v < 0:
                neg.append((l, -v))

        if len(pos) > len(neg):
            neg, pos = pos, neg

        sum_map = defaultdict(list)
        for permu in permutations(list(range(10)), len(pos)):
            if 0 in permu and pos[permu.index(0)][0] in non_leading_zero:
                continue

            s = 0
            idx = 0
            for (l, v) in pos:
                s += v * permu[idx]
                idx += 1
            sum_map[s].append(set(permu))

        for permu in permutations(list(range(10)), len(neg)):
            if 0 in permu and neg[permu.index(0)][0] in non_leading_zero:
                continue

            s = 0
            idx = 0
            for (l, v) in neg:
                s += v * permu[idx]
                idx += 1

            if s in sum_map:
                for s2 in sum_map[s]:
                    if not set(permu) & s2:
                        return True

        return False
