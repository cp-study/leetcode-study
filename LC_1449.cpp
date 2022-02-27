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
	string dp[5001];
	string largestNumber(vector<int>& cost, int target) {
		if (target <= 0)
			return target == 0 ? "" : "0";
		if (dp[target].empty()) {
			dp[target] = "0";
			for (int n = 1; n <= 9; ++n) {
				auto res = largestNumber(cost, target - cost[n - 1]);
				if (res != "0" && res.size() + 1 >= dp[target].size()) {
					dp[target] = to_string(n) + res;
				}
			}
		}
		return dp[target];
	}
};

string dp[5001] = {};
string largestNumber(vector<int>& cost, int t) {
	if (t <= 0)
		return t == 0 ? "" : "0";
	if (dp[t].empty()) {
		dp[t] = "0";
		for (int n = 1; n <= 9; ++n) {
			auto res = largestNumber(cost, t - cost[n - 1]);
			if (res != "0" && res.size() + 1 >= dp[t].size())
				dp[t] = to_string(n) + res;
		}
	}
	return dp[t];
}

int main() {
	
}