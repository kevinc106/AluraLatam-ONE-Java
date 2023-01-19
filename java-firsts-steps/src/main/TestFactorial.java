package main;

import java.util.Iterator;

public class TestFactorial {
	public static void main(String[] args) {
		int factorial = 1;
		int factorialDeNumero = 10;  	
		for (int i = 1; i <= factorialDeNumero; i++) {
			factorial*= i;
		}
		System.out.println(factorial);
	}
}
