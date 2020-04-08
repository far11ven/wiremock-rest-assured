package com.auto1.qa.global.utils;

/**
 * This class provides a custom RuntimeException
*/
public class TestFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TestFailedException(String s)
	{
		// Call constructor of parent Exception
		super(s);
	}

}
