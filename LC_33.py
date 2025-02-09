from typing import List
import bisect

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        l = 1
        r = len(nums)-1

        while l<r:
            mid = (l+r)//2
            print(mid)
            if nums[mid] > nums[0]:
                l = mid+1
            elif nums[mid] < nums[0]:
                r = mid
            else:
                break

        def binary_search(target, arr) -> bool:
            l = 0
            r = len(arr)-1

            while l<=r:
                mid = (l+r)//2
                if arr[mid] > target:
                    r = mid - 1
                elif arr[mid] < target:
                    l = mid + 1
                else:
                    return mid
            return -1

        print(nums[:l])
        print(nums[l:])
        ans1 = binary_search(target, nums[:l])
        if ans1 != -1:
            return ans1
        else:
            ans2 = binary_search(target, nums[l:])
            if ans2 != -1:
                return l + ans2
            else:
                return ans2

