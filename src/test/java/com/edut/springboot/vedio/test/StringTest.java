package com.edut.springboot.vedio.test;

import org.junit.jupiter.api.Test;

public class StringTest {

	@Test
	public void testString() {
		String s = "en_ " ; 
		String[] split = s.split("_");
		for (String string : split) {
			System.out.println("s="+string);
		}
	}

}
