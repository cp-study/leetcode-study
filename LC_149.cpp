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
#define MOD 1048576
#define INF LLONG_MAX
#define INF_MIN LLONG_MIN
#define MAX 200000
using namespace std;

class Solution {
public:
    int maxPoints(vector<vector<int>>& points) {
        int ans = 1;
        map<pair<int, int>,int> prevMp;
        for (int i = 0; i < points.size(); i++) {
            map<pair<int, int>, int> mp;
            for (int j = i + 1; j < points.size(); j++) {
                int y = points[i][1] - points[j][1];
                int x = points[i][0] - points[j][0];
                if (x < 0) {
                    y = -y;
                    x = -x;
                }
                int gcdXY = gcd(x, y);
                y /= gcdXY;
                x /= gcdXY;
                if (prevMp.find({ y,x }) != prevMp.end())
                    continue;
                if (mp[{y, x}] == 0) {
                    mp[{y, x}] += 2;
                }
                else {
                    mp[{y, x}]++;
                }
                ans = max(ans, mp[{y, x}]);
            }
            prevMp = mp;
        }
        return ans;
    }

private:
    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }
};

int main()
{
    Solution sol;
    vector<vector<int>> points =
    { {0, 0},{2, 2},{-1, -1} };

    cout << sol.maxPoints(points) << endl;
}