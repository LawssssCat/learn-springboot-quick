package com.edut.springboot.test;

import org.junit.jupiter.api.Test;

public class BitTest {
	
	@Test
	public void test_bit() {
		int a = 0x10 ; // 16
		int c = 10 ; //10
		int b = 010 ; //8
		
		int d = 'c' ; // 99 ASCII 
		
		int bit = 0b10 ; // 2
		System.out.println(bit);
	}

}
