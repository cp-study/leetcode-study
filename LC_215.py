class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        def quickSelect(arr, n):
            if len(arr) == 1:
                return arr[0]

            pivot = arr[random.randint(0, len(arr)-1)]
            left = [x for x in arr if x > pivot]
            mid = [x for x in arr if x == pivot]
            right = [x for x in arr if x < pivot]

            if n < len(left):
                return quickSelect(left, n)
            elif n < len(left) + len(mid):
                return pivot
            else:
                return quickSelect(right, n - len(left) - len(mid))

        return quickSelect(nums, k-1)
