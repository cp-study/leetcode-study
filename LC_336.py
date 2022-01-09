from typing import List


class Solution:
    def palindromePairs(self, words: List[str]) -> List[List[int]]:
        def is_palindrome(s):
            return s == s[::-1]

        wmap = {}
        for i in range(len(words)):
            wmap[words[i]] = i

        res = []
        for i in range(len(words)):
            if words[i] == "":
                for j in range(len(words)):
                    if i != j and is_palindrome(words[j]):
                        res.append([i, j])

            for j in range(len(words[i])):
                left = words[i][:j]
                right = words[i][j:]

                # left + right + left[::-1]
                if is_palindrome(right):
                    if left[::-1] in wmap and wmap[left[::-1]] != i:
                        res.append([i, wmap[left[::-1]]])

                # right[::-1] + left + right
                if is_palindrome(left):
                    if right[::-1] in wmap and wmap[right[::-1]] != i:
                        res.append([wmap[right[::-1]], i])

        return res
