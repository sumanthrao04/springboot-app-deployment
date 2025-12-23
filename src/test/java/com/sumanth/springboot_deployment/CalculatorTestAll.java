
package com.sumanth.springboot_deployment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTestSuite {

    private Calculator c;

    @BeforeEach
    void setup() {
        c = new Calculator();
    }

    // 1
    @Test
    void add_twoNumbers_returnsSum() {
        assertEquals(5, c.add(2, 3));
        assertEquals(-1, c.add(2, -3));
    }

    // 2
    @Test
    void subtract_twoNumbers_returnsDifference() {
        assertEquals(1, c.subtract(3, 2));
        assertEquals(-5, c.subtract(-3, 2));
    }

    // 3
    @Test
    void multiply_twoNumbers_returnsProduct() {
        assertEquals(6, c.multiply(2, 3));
        assertEquals(0, c.multiply(0, 999));
    }

    // 4
    @Test
    void divide_validInputs_returnsQuotient() {
        assertEquals(2, c.divide(10, 5));
        assertEquals(-3, c.divide(-9, 3));
    }

    // 5
    @Test
    void divide_byZero_throws() {
        assertThrows(IllegalArgumentException.class, () -> c.divide(1, 0));
    }

    // 6
    @Test
    void modulo_basicCases() {
        assertEquals(1, c.modulo(7, 3));
        assertEquals(0, c.modulo(10, 5));
    }

    // 7
    @Test
    void modulo_byZero_throws() {
        assertThrows(IllegalArgumentException.class, () -> c.modulo(1, 0));
    }

    // 8
    @Test
    void abs_square_cube_power_work() {
        assertEquals(4, c.abs(-4));
        assertEquals(9, c.square(3));
        assertEquals(27, c.cube(3));
        assertEquals(16, c.power(2, 4));
    }

    // 9
    @Test
    void power_negativeExponent_throws() {
        assertThrows(IllegalArgumentException.class, () -> c.power(2, -1));
    }

    // 10
    @Test
    void factorial_nonNegative() {
        assertEquals(1, c.factorial(0));
        assertEquals(120, c.factorial(5));
    }

    // 11
    @Test
    void factorial_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> c.factorial(-3));
    }

    // 12
    @Test
    void fibonacci_basic() {
        assertEquals(0, c.fibonacci(0));
        assertEquals(1, c.fibonacci(1));
        assertEquals(8, c.fibonacci(6)); // 0,1,1,2,3,5,8
    }

    // 13
    @Test
    void fibonacci_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> c.fibonacci(-2));
    }

    // 14
    @Test
    void gcd_lcm_prime_checks() {
        assertEquals(6, c.gcd(54, 24));
        assertEquals(216, c.lcm(54, 24));
        assertTrue(c.isPrime(29));
        assertFalse(c.isPrime(1));
    }

    // 15
    @Test
    void arrays_sum_avg_median_and_clamp() {
        int[] arr = {1, 3, 5, 7};
        assertEquals(16, c.sum(arr));
    }

}