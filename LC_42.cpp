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
#define MOD 998244353
#define INF LLONG_MAX
#define INF_MIN LLONG_MIN
#define MAX 200000
using namespace std;

class Solution {
public:
    int trap(vector<int>& height) {
        vector<pair<int, int>> v;
        for (int i = 0; i < height.size(); i++) {
            v.push_back({ height[i],i });
        }
        sort(v.begin(), v.end());

        int l = v.back().second;
        int r = l;
        int ans = 0;
        int size = v.size();
        for (int j = size - 2; j >= 0; j--) {
            if (v[j].second >= l && v[j].second <= r) continue;
            if (v[j].second < l) {
                for (int i = v[j].second +1; i < l; i++) {
                     ans -= height[i];
                }
                ans += v[j].first * (l - (v[j].second + 1));
                l = v[j].second;
            }
            else {
                for (int i = r+1; i < v[j].second; i++) {
                    ans -= height[i];
                }
                ans += v[j].first *(v[j].second - (r + 1));
                r = v[j].second;
            }
        }
        return ans;
    }
};

int main()
{
    Solution sol;
    vector<int> height = { 4,2,0,3,2,5 };
    cout << sol.trap(height);
}