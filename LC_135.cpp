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
    int candy(vector<int>& ratings) {
        priority_queue<pair<int,int>, vector<pair<int, int>>,
            greater<pair<int, int>>> pq;
        for (int i = 0; i < ratings.size(); i++) {
            pq.push({ ratings[i],i });
        }

        vector<int> candyDistribution(ratings.size());
        while (!pq.empty()) {
            pair<int, int> p = pq.top();
            pq.pop();
            if (candyDistribution[p.second] != 0) continue;
            candyDistribution[p.second] = 1;

            // move left
            for (int i = p.second - 1; i >= 0; i--) {
                if (ratings[i] <= ratings[i + 1]) break;
                int nextCandyCnt = candyDistribution[i+1]+1;
                if (candyDistribution[i] == 0) {
                    candyDistribution[i] = nextCandyCnt;
                }
                else {
                    candyDistribution[i] = max(candyDistribution[i], nextCandyCnt);
                }
            }
            // move right
            for (int i = p.second + 1; i < candyDistribution.size(); i++) {
                if (ratings[i] <= ratings[i - 1]) break;
                int nextCandyCnt = candyDistribution[i-1] + 1;
                if (candyDistribution[i] == 0) {
                    candyDistribution[i] = nextCandyCnt;
                }
                else {
                    candyDistribution[i] = max(candyDistribution[i], nextCandyCnt);
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < candyDistribution.size(); i++) {
            ret += candyDistribution[i];
        }
        return ret;
    }
};

int main() {
    Solution sol;
    vector<int> v = {1,0,2};
    cout << sol.candy(v);
}