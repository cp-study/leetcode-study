package leetcode;

import java.util.HashMap;

class LC_2338 {
    private static final int MOD = 1000000007;

    public int idealArrays(int n, int maxValue) {
        sieve(maxValue);
        long ret = 0;
        for(int i=1;i<=maxValue;i++) {

            HashMap<Integer,Integer> pf = getFactorization(i);
            long curr = 1;

            for(Integer primeCnt : pf.values()) {
                long v = n;
                // (stars and bars method)
                for(int j=1;j<primeCnt;j++) {
                    v *= (n+j);
                    v%=MOD;
                    v *= modInverse(j+1, MOD);
                    v%=MOD;
                }
                curr = (long)(curr*v) % MOD;
            }

            ret = (ret+curr)%MOD;
        }
        return (int)ret;
    }

    int spf[];
    void sieve(int max)
    {
        spf = new int[max+1];
        spf[1] = 1;
        for (int i=2; i<=max; i++)
            spf[i] = i;

        for (int i=4; i<=max; i+=2)
            spf[i] = 2;

        for (int i=3; i*i<=max; i++)
        {
            if (spf[i] == i)
            {
                for (int j=i*i; j<=max; j+=i)
                    if (spf[j]==j)
                        spf[j] = i;
            }
        }
    }

    HashMap<Integer,Integer> getFactorization(int x)
    {
        HashMap<Integer,Integer> ret = new HashMap<>();
        while (x != 1)
        {
            ret.put(spf[x], ret.getOrDefault(spf[x],0)+1);
            x = x / spf[x];
        }
        return ret;
    }

    // Function to find modular inverse of a
    // under modulo m Assumption: m is prime
    int modInverse(int a, int m)
    {
        int g = gcd(a, m);
        if (g != 1)
            return -1;
        else
        {
            return power(a,m-2,m);
        }
    }

    int power(int x, int y, int m)
    {
        if (y == 0)
            return 1;
        int p = power(x, y / 2, m) % m;
        p = (int)((p * (long)p) % m);
        if (y % 2 == 0)
            return p;
        else
            return (int)((x * (long)p) % m);
    }

    // Function to return gcd of a and b
    int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}