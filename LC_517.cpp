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
    int findMinMoves(vector<int>& machines) {
        int target = 0;
        ll sum = 0;
        for (int i = 0; i < machines.size(); i++) {
            sum += machines[i];
        }
        if (sum % machines.size() == 0) {
            target = sum / machines.size();
        }
        else {
            return -1;
        }
        ll maxim = 0;

        ll accum = 0;
        for (int i = 0; i < machines.size();i++) {
            ll diff = machines[i]- target;
            accum += diff;
            maxim = max(maxim, accum);
        }

        return maxim;
    }
};

int main() {

    Solution sol;
    vector<int> machines = { 0,3,0 };
    cout << sol.findMinMoves(machines);
}