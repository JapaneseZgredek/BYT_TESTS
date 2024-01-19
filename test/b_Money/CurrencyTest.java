package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Currency class.
 */
public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	/**
	 * Setup method executed before each test.
	 *
	 * @throws Exception If an exception occurs during setup.
	 */
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	/**
	 * Test for the getName method.
	 */
	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}

	/**
	 * Test for the getRate method.
	 */
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate());
		assertEquals(Double.valueOf(0.20), DKK.getRate());
		assertEquals(Double.valueOf(1.5), EUR.getRate());
	}

	/**
	 * Test for the setRate method.
	 */
	@Test
	public void testSetRate() {
		SEK.setRate(0.18);
		assertEquals(Double.valueOf(0.18), SEK.getRate());

		DKK.setRate(0.25);
		assertEquals(Double.valueOf(0.25), DKK.getRate());

		EUR.setRate(1.8);
		assertEquals(Double.valueOf(1.8), EUR.getRate());
	}

	/**
	 * Test for the universalValue method.
	 */
	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(1500), EUR.universalValue(1000));
		assertEquals(Integer.valueOf(300), DKK.universalValue(1500));
		assertEquals(Integer.valueOf(450), SEK.universalValue(3000));
	}

	/**
	 * Test for the valueInThisCurrency method.
	 */
	@Test
	public void testValueInThisCurrency() {
		assertEquals(Integer.valueOf(2000), SEK.valueInThisCurrency(1500, DKK));
		assertEquals(Integer.valueOf(150), EUR.valueInThisCurrency(1500, SEK));
		assertEquals(Integer.valueOf(225), DKK.valueInThisCurrency(300, SEK));
	}
}
