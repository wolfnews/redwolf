package com.hoteam.wolf.utils;

import java.util.Random;

public class CodeTool {

	public static String randomCode(int count) {
		String sRand = "";
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			int wordType = random.nextInt(3);
			char retWord = 0;
			switch (wordType) {
			case 0:
				retWord = getSingleNumberChar();
				break;
			case 1:
				retWord = getSingleNumberChar();
				break;
			case 2:
				retWord = getSingleNumberChar();
				break;
			}
			sRand += String.valueOf(retWord);
		}
		return sRand;
	}

	private static char getSingleNumberChar() {
		Random random = new Random();
		int numberResult = random.nextInt(10);
		int ret = numberResult + 48;
		return (char) ret;
	}
	
	public static void main(String[] args) {
		System.out.println(CodeTool.randomCode(6));
	}
}
