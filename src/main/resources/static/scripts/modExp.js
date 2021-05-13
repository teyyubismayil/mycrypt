function modExp(x, y, p) {
    x = BigInt(x)
    y = BigInt(y)
    p = BigInt(p)
    // Initialize result
    let res = 1n;
    // Update x if it is more
    // than or equal to p
    x = x % p;
    if (x === 0n)
        return 0;
    while (y > 0n) {
        // If y is odd, multiply
        // x with result
        if (y & 1n)
            res = (res * x) % p;
        // y must be even now
        // y = $y/2
        y = y >> 1n;
        x = (x * x) % p;
    }
    return Number(res);
}
