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
#define MOD 998244353
#define INF LLONG_MAX
#define INF_MIN LLONG_MIN
#define MAX 200000
using namespace std;

class Solution {
public:
    ll dp[21][101][101];
    int minCost(vector<int>& houses, vector<vector<int>>& cost, int m, int n, int target) {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 101; j++) {
                for (int k = 0; k < 101; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        ll ans = dfs(0, 0, 0, houses, cost, m, n, target);
        if (ans >= LLONG_MAX / 2) ans = -1;
        return ans;
    }

private:
    ll dfs(int last, int currTarget, int idx, vector<int>& houses, vector<vector<int>>& cost, int m, int n, int target) {
        if (currTarget > target) return LLONG_MAX / 2;
        if (idx >= m) {
            if (currTarget != target) {
                return LLONG_MAX / 2;
            }
            else {
                return 0;
            }
        }

       // if (dp[last][currTarget][idx] != -1) return dp[last][currTarget][idx];

        ll ret = LLONG_MAX/2;
        if (houses[idx] != 0) {
            ret = dfs(houses[idx], houses[idx] == last ? currTarget : currTarget + 1, idx + 1, houses, cost, m, n, target);
        }
        else {
            for (int i = 1; i <= n; i++) {
                ret = min(ret, dfs(i, i == last ? currTarget : currTarget + 1, idx + 1, houses, cost, m, n, target) + cost[idx][i-1]);
            }
        }
        return dp[last][currTarget][idx] = ret;
    }
};

int main() {
    Solution sol;

    vector<int> houses = { 0,2,1,2,0 };
    vector<vector<int>> cost = { {1,10},{10,1},{10,1},{1,10},{5,1} };
    
    cout << sol.minCost(houses, cost, 5, 2, 3);
}