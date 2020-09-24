package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("factorial: " + fact(10));
        System.out.println("fact 2: " + fac(10));
        System.out.println("fibonacci: " + fib(8));
        System.out.println(sum(5));
        System.out.println(result(5));
        System.out.println(f(6, 8));

        System.out.println(reverse("cat is runing"));

        System.out.println(naturalSum(3));
    }

//    Compute the Factorial of a number N given this formula (𝑁 )! = 𝑁 ∗ (𝑁 − 1) ∗ (𝑁 − 2) ∗ ... ∗ 1. Write the code
//            example

    public static int fac(int n) {
        if(n == 1)
            return 1;
        else
            return n * fact(n - 1);
    }

    //    5. Compute the sum of natural numbers until 𝑁.

    public static int naturalSum(int n) {
        if (n <= 1)
            return 1;
        else
            return n + sum(n - 1);
    }

    public static String reverse(String s) {
        if (s.isEmpty()) {
            System.out.println("String is empty");
            return s;
        }
        String[] strings = s.split(" ");

        //System.out.println(s.substring(1) + s.charAt(0));
        //return reverse(s.substring(1));
//        return reverse(strings[0]);
        String g = reverse(strings, strings.length - 1);
        return g;
    }

    public static String reverse(String arr[], int pos) {
        if (pos < 0) {
            return "";
        }

        return arr[pos] + " " + reverse(arr, pos - 1);
    }

    public static int f(int k, int n) {
        if (n == k)
            return k;
        else
            if (n > k)
                return f(k, n - k);
            else
                return f(k - n, n);
    }

    public static int result(int n) {
        if (n == 1)
            return 2;
        else
            return 2 * result(n - 1);
    }

    public static int sum(int n) {
        if(n == 1)
            return 1;
        else
            return n + sum(n - 1);
    }

    public static int fib(int n) {
        if(n >= 3) {
            // recursive case - where it does call itself
            return fib(n - 1) + fib(n - 2);
        } else {
            // base case - where it doesn't call itself
            return 1;
        }
    }

    public static int fact(int n) {
        if(n >= 1) {
            return n * fact(n - 1);
        } else {
            return 1;
        }
    }
}
