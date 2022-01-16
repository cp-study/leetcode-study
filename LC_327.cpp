#include <iostream>
#include <string>
#include <algorithm>
#include <math.h>
#include <vector>
#include <queue>
#include <stack>
#include <set>
#include <map>
#include <functional>
#include <cstdlib>
#include <list>
#include <iomanip>
#include <unordered_map>
#include <chrono>
#define ll long long
#define ull unsigned long long
#define ld long double
#define MOD 998244353
#define INF 0x3f3f3f3f
#define INF_MIN LLONG_MIN
#define MAX 200000
using namespace std;
class Solution {
public:
    int mergeSort(vector<long>& sum, int lower, int upper, int s, int e)
    {
        if (e - s <= 1) return 0;
        int mid = (s + e) / 2, m = mid, n = mid, count = 0;
        count = mergeSort(sum, lower, upper, s, mid) + mergeSort(sum, lower, upper, mid, e);
        // count prefix sums - START
        for (int i = s; i < mid; i++)
        {
            while (m < e && (sum[m] - sum[i]) < lower) m++;
            while (n < e && (sum[n] - sum[i]) <= upper) n++;
            count += n - m;
        }
        // count prefix sums - END
        inplace_merge(sum.begin() + s, sum.begin() + mid, sum.begin() + e);

        return count;
    }

    int countRangeSum(vector<int>& nums, int lower, int upper) {
        int len = nums.size();
        vector<long> sum(len + 1, 0);
        for (int i = 0; i < len; i++) sum[i + 1] = sum[i] + nums[i];
        int ans = mergeSort(sum, lower, upper, 0, len + 1);
        for (int i = 0; i < sum.size(); i++) {
            cout << sum[i] << " ";
        }cout << endl;
        return ans;
    }
};

int main()
{
    vector<int> nums = { -2,5,-1 };
    int lower = -2;
    int upper = 2;

    Solution sol;
    cout << sol.countRangeSum(nums, lower, upper);

}