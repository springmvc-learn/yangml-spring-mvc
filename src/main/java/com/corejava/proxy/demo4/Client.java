package com.corejava.proxy.demo4;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CglibProxy proxy = new CglibProxy();
		Train t = (Train)proxy.getProxy(Train.class);
		t.move("hello");
	}

}
