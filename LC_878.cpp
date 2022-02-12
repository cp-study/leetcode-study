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
#define MOD 1000000007
#define INV2 499122177
#define INF 0x3f3f3f3f
#define INF_MIN LLONG_MIN
#define MAX 800000
using namespace std;

class Solution {
public:
    int nthMagicalNumber(int n, int a, int b) {
        ll l = 2;
        ll r = 40000000000000;
        ll mid = 0;

        ll lcm = ((ll)a * b) / gcd(min(a,b), max(a,b));

        while (l <= r) {
            mid = (l + r) / 2;
            
            ll val = mid / a + mid / b - mid / lcm;
            if (val >= n) {
                r = mid - 1;
            }
            else if (val < n) {
                l = mid + 1;
            }
        }
        return l%MOD;
    }

private:
    ll gcd(ll a, ll b) {
        if (a == 0) return b;
        else {
            return gcd(b % a, a);
        }
    }
};

int main() {
    Solution sol;
    cout << sol.nthMagicalNumber(4, 2, 3);
}