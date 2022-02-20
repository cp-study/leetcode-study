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
#define ld  double
#define MOD 998244353
#define INV2 499122177
#define INF 0x3f3f3f3f
#define INF_MIN LLONG_MIN
#define MAX 800000
using namespace std;

class Solution {
public:
    int minAbsDifference(vector<int>& nums, int goal) {
        int size = nums.size();
        vector<ll> firstHalf;
        vector<ll> secondHalf;
        for (int i = 0; i < size / 2; i++) {
            firstHalf.push_back(nums[i]);
        }
        for (int i = size /2; i < size; i++) {
            secondHalf.push_back(nums[i]);
        }

        vector<ll> firstHalfSums;
        vector<ll> secondHalfSums;
        dfs(0, 0, firstHalf, firstHalfSums);
        dfs(0, 0, secondHalf, secondHalfSums);

        sort(secondHalfSums.begin(), secondHalfSums.end());
        ll ans = abs(goal);
        for (int i = 0; i < firstHalfSums.size(); i++) {
            ll val = firstHalfSums[i];
            ll l = 0;
            ll r = secondHalfSums.size() - 1;
            ll mid = 0;
            while (l <= r) {
                mid = (l + r) / 2;
                ll sum = val + secondHalfSums[mid];
                
                if (sum > goal) {
                    r = mid - 1;
                }
                else if (sum < goal) {
                    l = mid + 1;
                }
                else if (sum == goal) {
                    l = mid;
                    break;
                }
            }
            ll l2 = 0;
            ll r2 = secondHalfSums.size() - 1;
            ll mid2 = 0;
            while (l2 <= r2) {
                mid2 = (l2 + r2) / 2;
                ll sum = val + secondHalfSums[mid2];

                if (sum > goal) {
                    r2 = mid2 - 1;
                }
                else if (sum < goal) {
                    l2 = mid2 + 1;
                }
                else if (sum == goal) {
                    r2 = mid2;
                    break;
                }
            }
            ll ansIdx = 0;
            if (l >= secondHalfSums.size()) {
                l = secondHalfSums.size() - 1;
            }
            if (r2 < 0) {
                r2 = 0;
            }
            if (abs((val + secondHalfSums[r2]) - goal) < ((val + secondHalfSums[l]) - goal)) {
                ansIdx = r2;
            }
            else {
                ansIdx = l;
            }
            if (abs(val + secondHalfSums[ansIdx] - goal) < ans) {
                ans = abs(val + secondHalfSums[ansIdx] - goal);
            }
        }
        return ans;
    }

private:
    void dfs(ll currSum, ll idx, vector<ll>& v, vector<ll>& halfSums) { 
        if (idx >= v.size()) {
            halfSums.push_back(currSum);
            return;
        }
        dfs(currSum + v[idx], idx + 1, v, halfSums);
        dfs(currSum, idx + 1, v, halfSums);
    }
};

int main() {
    Solution sol;
    vector<int> v = { 5,-7,3,5 };
    int goal = 6;
    cout << sol.minAbsDifference(v, goal);
}