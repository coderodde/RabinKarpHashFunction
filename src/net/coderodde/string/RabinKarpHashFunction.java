package net.coderodde.string;

public final class RabinKarpHashFunction {

    private final int[] map;
    private final int[] factors;
    private final int r;
    private final int q;

    /**
     * Builds this hash function data structure in time and space linear in the
     * length of the integer string.
     *
     * @param s the string in integer alphabet.
     * @param r the r.
     * @param q the q.
     */
    public RabinKarpHashFunction(int[] s, int r, int q) {
        this.r = r;
        this.q = q;
        int n = s.length + 1;
        this.map = new int[n];

        // Compute the Karp-Rabin hash functions for all the prefixes of
        // the string.
        int m = 1;

        for (int i = 1; i < map.length; ++i) {
            map[i] = computeEntry(s, i, m);
            m *= r;
        }

        this.factors = new int[n];

        for (int i = 0, factor = 1; i < n; ++i, factor *= r) {
            factors[i] = factor;
        }
    }

    public int getHashValue(int i, int j) {
        if (i == j) {
            // Hash of the empty string is always zero.
            return 0;
        }

        if (i < 0) {
            throw new IndexOutOfBoundsException(
                    "The starting index is negative: " + i);
        }

        if (j >= this.map.length) {
            throw new IndexOutOfBoundsException(
                    "The ending index is too large: " + j + ", should be "
                    + "at most " + this.map.length + ".");
        }

        if (i > j) {
            throw new IllegalArgumentException(
                    "The indices are ass-backwards: i = " + i + ", j = "
                    + j);
        }

        int len = j - i;
        int factor = factors[len];
        int a = map[j];
        int b = map[i];
        int ret = (a - b * factor) % q;
        return ret < 0 ? ret + q : ret;
    }
    
    private int computeEntry(int[] string, int patternLength, int m) {
        int hash = 0;

        for (int i = 0; i < patternLength; ++i) {
            hash += string[i] * m;
            m /= r;
        }

        return hash % q;
    }
}
