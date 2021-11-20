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
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        int N = matrix.size();
        int M = matrix[0].size();
        vector<vector<ll>> dp(N  + 1, vector<ll>(M + 1));
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] == 0) {
                    dfs(1, i, j, N, M, matrix, dp);
                }
            }
        }
        ll ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = max(ans, dp[i][j]);
            }
        }
        return ans;
    }

private:
    int dy[4] = { 0,0,1,-1 };
    int dx[4] = { 1,-1,0,0 };

    ll dfs(ll cnt,int y,int x, int N, int M,vector<vector<int>>& matrix, vector<vector<ll>>& dp) {
        if (dp[y][x] != 0) return dp[y][x];

        ll maxDfsVal = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
 
            if (ny >= N || nx >= M || ny < 0 || nx < 0) continue;
            if (matrix[ny][nx] > matrix[y][x])
                maxDfsVal = max(maxDfsVal, dfs(cnt + 1, ny, nx, N, M, matrix, dp));
        }
        dp[y][x] = 1 + maxDfsVal;
        return dp[y][x];
    }
};

int main()
{
    Solution sol;
    vector<vector<int>> v = {{3, 4, 5}, { 3, 2, 6 }, { 2, 2, 1 }};
    cout << sol.longestIncreasingPath(v) << endl;
}