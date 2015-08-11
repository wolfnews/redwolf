package com.hoteam.wolf.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws Exception {
		String format = "MM/dd/yyyy hh:mm a";
		DateFormat dateFormat = new SimpleDateFormat(format);
		String source = "07/15/2015 1:07 PM";
		Date date  = dateFormat.parse(source);
		System.out.println(date.toString());
	}
}
