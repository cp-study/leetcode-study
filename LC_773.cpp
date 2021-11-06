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
    int slidingPuzzle(vector<vector<int>>& board) {

        int swapCnt = 0;
        int y = 0;
        int x = 0;
        string str;
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board[i].size(); j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                }
                str += board[i][j] + '0';
            }
        }

        return bfs(swapCnt, y, x, str);
    }
private:
    int dy[4] = { 1,-1,0,0 };
    int dx[4] = { 0,0,1,-1 };

    int bfs(int swapCnt, int u, int w, string& board) {
        set<string> st;
        queue<pair<pair<int, int>, pair<string, int>>> q;

        q.push({ { u,w}, {board,swapCnt} });
        while (!q.empty()) {
            pair<pair<int, int>, pair<string, int>>
                p = q.front();
            q.pop();
            int y = p.first.first;
            int x = p.first.second;
            string bd = p.second.first;

            int thisSwapCnt = p.second.second;

            if (bd == "123450") {
                return thisSwapCnt;
            }
            if (st.find(bd) != st.end()) {
                continue;
            }
            st.insert(bd);

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny >= 2 || nx >= 3 || ny < 0 || nx < 0) continue;
                bd[y * 3 + x] = bd[ny * 3 + nx];
                bd[ny * 3 + nx] = '0';
                q.push({ {ny,nx},{bd,thisSwapCnt + 1 } });
                bd[ny * 3 + nx] = bd[y * 3 + x];
                bd[y * 3 + x] = '0';
            }
        }
        return -1;
    }
};