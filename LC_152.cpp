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
	int maxProduct(vector<int>& nums) {

		int firstNegativeIdx = 0;
		int lastNegativeIdx = 0;
		int negativeCnt = 0;
		int currCnt = 0;
		ll curr = 1;
		ll ans = LLONG_MIN;
		for (int i = 0; i < nums.size(); i++) {
			ans = max(ans, (ll)nums[i]);
		}

		int startIdx = 0;
		int endIdx = 0;
		nums.push_back(0);
		for (int i = 0; i < nums.size(); i++) {
			if (nums[i] == 0) {
				if (negativeCnt % 2 == 0) {
					if (currCnt == 0) curr = 0;
					ans = max(ans, curr);
				}
				else {
					ll tmpCurr = curr;
					if (currCnt > 1) {

						for (int j = lastNegativeIdx; j <= endIdx; j++) {
							tmpCurr /= nums[j];
						}
						ans = max(ans, tmpCurr);
					}
					if (currCnt > 1) {
						tmpCurr = curr;
						for (int j = startIdx; j <= firstNegativeIdx; j++) {
							tmpCurr /= nums[j];
						}
						ans = max(ans, tmpCurr);
					}
				}
				curr = 1;
				currCnt = 0;
				negativeCnt = 0;
				startIdx = i + 1;
				endIdx = i + 1;
			}
			else {
				if (nums[i] < 0) {
					if (negativeCnt == 0) {
						firstNegativeIdx = i;
					}
					lastNegativeIdx = i;
					negativeCnt++;
				}
				curr *= nums[i];
				endIdx = i;
				currCnt++;
			}
		}
		return ans;
	}
};

int main() {
	Solution sol;
	vector<int> v = { 2, 0, 3, -2 };
	cout << sol.maxProduct(v);
}