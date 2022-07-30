import copy
from typing import List


class Solution:
    def minFlips(self, mat: List[List[int]]) -> int:
        n = len(mat)
        m = len(mat[0])

        def to_tuple(matrix):
            return tuple(tuple(row) for row in matrix)

        def tuple_to_matrix(tuple_matrix):
            return [list(row) for row in tuple_matrix]

        queue = [(mat, 0)]
        visited = set()
        visited.add(to_tuple(mat))
        direction = [[0, 1], [1, 0], [0, -1], [-1, 0]]

        while queue:
            mat, step = queue.pop(0)

            if all(mat[i][j] == 0 for i in range(n) for j in range(m)):
                return step

            for i in range(n):
                for j in range(m):
                    copy_mat = tuple_to_matrix(mat)
                    copy_mat[i][j] = 1 - mat[i][j]
                    for d in direction:
                        x, y = i + d[0], j + d[1]
                        if 0 <= x < n and 0 <= y < m:
                            copy_mat[x][y] = 1 - mat[x][y]

                    tuple_matrix = to_tuple(copy_mat)
                    if tuple_matrix not in visited:
                        queue.append((tuple_matrix, step + 1))
                        visited.add(tuple_matrix)

        return -1
