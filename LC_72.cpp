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
	int minDistance(string word1, string word2) {
		vector<vector<ll>> dp(word1.size()+1, vector<ll>(word2.size()+1));
		for (int i = 0; i < dp.size(); i++) {
			dp[i][word2.size()] = word1.size()- i;
		}
		for (int i = 0; i < dp[0].size(); i++) {
			dp[word1.size()][i] = word2.size() - i;
		}
		
		for (int i = dp.size() - 2; i >= 0; i--) {
			for (int j = dp[i].size() - 2; j >= 0; j--) {
				if (word1[i] == word2[j]) {
					dp[i][j] = dp[i + 1][j + 1];
				}
				else {
					ll minim = LLONG_MAX;
					minim = min(minim, dp[i + 1][j + 1] + 1);
					minim = min(minim, dp[i][j + 1] + 1);
					minim = min(minim, dp[i + 1][j] + 1);
					dp[i][j] = minim;
				}
			}
		}
		return dp[0][0];
	}
};

int main() {
	Solution sol;
	cout << "min distance: " << sol.minDistance("horse", "ros") << endl;
}