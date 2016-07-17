package com.corejava.reflect;

import com.springmvc.controller.SpringMVCController;

public class ClassDemo3 {
	public static void main(String[] args) {
		String s = "hello";
		SpringMVCController s1 = new SpringMVCController();
		ClassUtil.printClassMethodMessage(s);
		
	    Integer n1 = 1;
	    ClassUtil.printClassMethodMessage(n1);
	}
}
