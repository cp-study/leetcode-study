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
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        deque<pair<int, int>> sortedCapital;
        for (int i = 0; i < capital.size(); i++) {
            sortedCapital.push_back({ capital[i],profits[i] });
        }
        sort(sortedCapital.begin(), sortedCapital.end());

        priority_queue<int> profitPq;
        for (int i = 0; i < k; i++) {
            int l = 0;
            int r = sortedCapital.size() - 1;
            int mid = 0;
            while (l <= r) {
                mid = (l + r) / 2;
                if (sortedCapital[mid].first > w) {
                    r = mid - 1;
                }
                else if (sortedCapital[mid].first <= w) {
                    l = mid + 1;
                }
            }
            while (l > 0) {
                profitPq.push(sortedCapital.front().second);
                sortedCapital.pop_front();
                l--;
            }
            if (!profitPq.empty()) {
                w += profitPq.top();
                profitPq.pop();
            }
            else {
                break;
            }
        }
        return w;
    }
};

int main() {
    int k = 3;
    int w = 0;
    vector<int> profits = { 1,2,3 };
    vector<int> capital = { 0,1,2 };

    Solution sol;
    cout << sol.findMaximizedCapital(k, w, profits, capital);
}