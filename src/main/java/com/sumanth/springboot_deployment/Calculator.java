package com.sumanth.springboot_deployment;




import java.util.Arrays;

public class Calculator {

    // 1) add
    public int add(int a, int b) {
        return a + b;
    }

    // 2) subtract
    public int subtract(int a, int b) {
        return a - b;
    }

    // 3) multiply
    public int multiply(int a, int b) {
        return a * b;
    }

    // 4) divide (throws on zero to avoid undefined behavior)
    public int divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Divide by zero");
        return a / b;
    }

    // 5) modulo
    public int modulo(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Modulo by zero");
        return a % b;
    }

    // 6) absolute value
    public int abs(int a) {
        return a < 0 ? -a : a;
    }

    // 7) max
    public int max(int a, int b) {
        return a >= b ? a : b;
    }

    // 8) min
    public int min(int a, int b) {
        return a <= b ? a : b;
    }

    // 9) clamp value between low and high (inclusive)
    public int clamp(int value, int low, int high) {
        if (low > high) throw new IllegalArgumentException("low > high");
        if (value < low) return low;
        if (value > high) return high;
        return value;
    }

    // 10) square
    public int square(int a) {
        return a * a;
    }

    // 11) cube
    public int cube(int a) {
        return a * a * a;
    }

    // 12) power (non-negative exponent)
    public int power(int base, int exp) {
        if (exp < 0) throw new IllegalArgumentException("Negative exponent");
        int result = 1;
        for (int i = 0; i < exp; i++) result *= base;
        return result;
    }

    // 13) factorial (0! = 1), non-negative
    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative factorial");
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    // 14) fibonacci (0 -> 0, 1 -> 1)
    public int fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative fibonacci index");
        if (n == 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    // 15) gcd (non-negative)
    public int gcd(int a, int b) {
        a = abs(a);
        b = abs(b);
        if (a == 0 && b == 0) throw new IllegalArgumentException("gcd(0,0) undefined");
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // 16) lcm (non-negative)
    public int lcm(int a, int b) {
        a = abs(a);
        b = abs(b);
        if (a == 0 || b == 0) return 0;
        return (a / gcd(a, b)) * b;
    }

    // 17) isPrime (basic check)
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    // 18) sum of array
    public int sum(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("null array");
        int s = 0;
        for (int v : arr) s += v;
        return s;
    }

    // 19) average (integer average, floor) of array
    public int average(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("empty/null array");
        return sum(arr) / arr.length;
    }

    // 20) median (integer median: middle element after sort)
    public int median(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("empty/null array");
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        int mid = copy.length / 2;
        if (copy.length % 2 == 1) {
            return copy[mid];
        } else {
            // average of the two middle values (integer division)
            return (copy[mid - 1] + copy[mid]) / 2;
        }
    }
}
