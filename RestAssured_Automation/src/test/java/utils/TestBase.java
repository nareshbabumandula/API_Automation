package utils;

import org.junit.BeforeClass;

public class TestBase {
	
	@BeforeClass
	public static void setup() {
		utilities.RestUtilities.setBaseURI();
		utilities.RestUtilities.setContentType();
	}

}
