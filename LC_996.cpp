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
    int numSquarefulPerms(vector<int>& nums) {

        if (squareSt.empty()) {
            ll i = 0;
            while (true) {
                if (i * i > 2000000000) break;
                squareSt.insert(i * i);
                i++;
            }
        }

        vector<bool> visited(nums.size());
        sort(nums.begin(), nums.end());
        int ans = 0;
        dfs(0, 0, nums, visited, squareSt, ans);
        return ans;
    }

private:
    set<ll> squareSt;
    void dfs(int cnt, ll bef, vector<int>& nums, vector<bool>& visited,
        set<ll>& squareSt, int& ans) {
        if (cnt >= nums.size()) {
            ans++;
            return;
        }
        for (int i = 0; i < nums.size(); i++) {
            if (visited[i]) continue;
            if (i!=0 && nums[i-1] == nums[i] && !visited[i-1]) continue;
            if (cnt != 0 && squareSt.find(bef + nums[i]) == squareSt.end()) continue;
            visited[i] = true;
            dfs(cnt + 1, nums[i], nums, visited, squareSt, ans);
            visited[i] = false;
        }
    }

};