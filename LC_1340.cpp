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
#define ll long long
#define ull unsigned long long
#define ld long double
#define MOD 1000000007
#define INF LLONG_MAX
#define INF_MIN LLONG_MIN
#define MAX 200000
using namespace std;

class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        vector<pair<int, int>> v(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            v[i].first = arr[i];
            v[i].second = i;
        }
        sort(v.begin(), v.end(), greater<pair<int, int>>());
        vector<int> dp(arr.size(),1);
        for (int i = 0; i < v.size(); i++) {
            int val = v[i].first;
            int idx = v[i].second;
            for (int j = idx - 1;
                j >= 0 && j>=idx-d; j--) {
                if (arr[j] >= val) break;
                else {
                    dp[j] = max(dp[j],dp[idx] + 1);
                }
            }
            for (int j = idx + 1;
                j < v.size() && j <= idx + d; j++) {
                if (arr[j] >= val) break;
                dp[j] = max(dp[j], dp[idx] + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < dp.size(); i++) {
            ans = max(ans, dp[i]);
        }
        return ans;
    }
};

int main()
{
    Solution sol;
    vector<int> arr = {6,4,14,6,8,13,9,7,10,6,12 }; 
    sol.maxJumps(arr, 2);
}