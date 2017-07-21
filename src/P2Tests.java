// Example of using unit tests for programming assignment. This is
// partially how your code will be graded. To run them on the command
// line, make sure that the junit .jar file is in the current or code directory.

// From the directory containing your code, A3Tests.java, and junit.jar:
// $> javac -cp .;junit.jar *.java
// $> java -cp .;junit.jar P1PersonTests

// Note: If your jar file isn't named junit.jar, make sure to change
// the command above appropriately

// Note: Linux and Mac users should separate library directories with : not ;

//assert functions
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

//junit
import org.junit.Test;

/**
 *  Tests for the person class.
 */
public class P2Tests {
	
	@Test(timeout=2000)
	public void companyTest01() {
		Company c = new Company("Test", null);
		assertEquals("Test", c.getName());
	}
	
	@Test(timeout=2000)
	public void companyTest02() {
		Company c = new Company("Test", "1");
		assertEquals(1, c.getIds().length);
	}
	
	@Test(timeout=2000)
	public void companyTest03() {
		Company c = new Company("Test", "1");
		c.addId("2");
		assertEquals(2, c.getIds().length);
	}
	
	@Test(timeout=2000)
	public void companyTest04() {
		Company c = new Company("Test", "1");
		assertArrayEquals(new String[]{"1"}, c.getIds());
	}
	
	@Test(timeout=2000)
	public void companyTest05() {
		Company c = new Company("Test", "1");
		c.addId("2");
		assertArrayEquals(new String[]{"1","2"}, c.getIds());
	}
	
	@Test(timeout=2000)
	public void companyTest06() {
		Company c = new Company("Test", "1");
		c.addId("3");
		c.addId("2");
		assertArrayEquals(new String[]{"1","3","2"}, c.getIds());
	}
	
	@Test(timeout=2000)
	public void companyTest07() {
		Company c1 = new Company("Test", "1");
		Company c2 = new Company("Test", "2");
		assertEquals(c1, c2);
	}
	
	@Test(timeout=2000)
	public void companyTest08() {
		Company c1 = new Company("Test", "1");
		Company c2 = new Company("Test", "2");
		assertEquals(c1.hashCode(), c2.hashCode());
	}
	
	@Test(timeout=2000)
	public void mapOATest01() {
		MapOpenAddressing<String, String> map = new MapOpenAddressing<>(10);
		assertNull(map.get("apple"));
	}
	
	@Test(timeout=2000)
	public void mapOATest02() {
		MapOpenAddressing<String, String> map = new MapOpenAddressing<>(10);
		map.put("apple", "1");
		assertEquals("1", map.get("apple"));
	}
	
	@Test(timeout=2000)
	public void mapOATest03() {
		MapOpenAddressing<String, String> map = new MapOpenAddressing<>(10);
		map.put("apple", "1");
		map.put("apple", "2");
		assertEquals("2", map.get("apple"));
	}
	
	@Test(timeout=2000)
	public void mapSCTest01() {
		MapSeparateChaining<String, String> map = new MapSeparateChaining<>(10);
		assertNull(map.get("apple"));
	}
	
	@Test(timeout=2000)
	public void mapSCTest02() {
		MapSeparateChaining<String, String> map = new MapSeparateChaining<>(10);
		map.put("apple", "1");
		assertEquals("1", map.get("apple"));
		
	}
	
	@Test(timeout=2000)
	public void mapSCTest03() {
		MapSeparateChaining<String, String> map = new MapSeparateChaining<>(10);
		map.put("apple", "1");
		map.put("apple", "2");
		assertEquals("2", map.get("apple"));
	}
	
	/**
	 *  Runs the tests.
	 *  
	 *  @param args unused
	 */
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("P2Tests");
	}
}
