package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Money class.
 */
public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

	/**
	 * Setup method executed before each test.
	 *
	 * @throws Exception If an exception occurs during setup.
	 */
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	/**
	 * Test for the getAmount method.
	 */
	@Test
	public void testGetAmount() {
		assertEquals(Integer.valueOf(10000), SEK100.getAmount());
	}

	/**
	 * Test for the getCurrency method.
	 */
	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
	}

	/**
	 * Test for the toString method.
	 */
	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
		assertEquals("0.0 SEK", SEK0.toString());
		assertEquals("-100.0 SEK", SEKn100.toString());
	}

	/**
	 * Test for the universalValue method.
	 */
	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(1500), EUR10.universalValue());
	}

	/**
	 * Test for the equals method.
	 */
	@Test
	public void testEqualsMoney() {
		Money SEK100Copy = new Money(10000, SEK);
		Money EUR5 = new Money(500, EUR);

		assertTrue(SEK100.equals(SEK100Copy));
		assertFalse(SEK100.equals(EUR5));
	}

	/**
	 * Test for the add method.
	 */
	@Test
	public void testAdd() {
		Money result = SEK100.add(SEK100);
		assertEquals(new Money(20000, SEK).getAmount(), result.getAmount());
	}

	/**
	 * Test for the sub method.
	 */
	@Test
	public void testSub() {
		Money result = SEK200.sub(SEK100);
		assertEquals(new Money(10000, SEK).getAmount(), result.getAmount());
	}

	/**
	 * Test for the isZero method.
	 */
	@Test
	public void testIsZero() {
		assertTrue(EUR0.isZero());
		assertFalse(EUR10.isZero());
	}

	/**
	 * Test for the negate method.
	 */
	@Test
	public void testNegate() {
		Money negated = SEK100.negate();
		assertEquals(new Money(-10000, SEK).getAmount(), negated.getAmount());
	}

	/**
	 * Test for the compareTo method.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(SEK100.compareTo(SEK200) < 0);
		assertTrue(SEK100.compareTo(SEK100) == 0);
		assertTrue(SEK200.compareTo(SEK100) > 0);
	}
}
